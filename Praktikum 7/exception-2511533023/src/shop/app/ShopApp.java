package shop.app;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import shop.StudentInfo;
import shop.exception.CatalogLoadException;
import shop.exception.InsufficientBalanceException;
import shop.exception.OutOfStockException;
import shop.exception.PaymentFailedException;
import shop.io.CatalogReader;
import shop.model.Product;
import shop.payment.EWalletPayment;
import shop.payment.PaymentMethod;
import shop.service.CheckoutService;
import shop.service.Inventory;
import shop.shipping.RegularShipping;
import shop.shipping.ShippingService;

public class ShopApp {

    public static void main(String[] args) {
        // L4.c: Memuat katalog menggunakan CatalogReader dari file "catalog.txt"
        List<Product> catalog;
        CatalogReader reader = new CatalogReader();
        try {
            catalog = reader.load(Path.of("katalog.csv"));
            if (catalog.isEmpty()) {
                System.out.println("Katalog kosong.");
                return;
            }
        } catch (CatalogLoadException e) {
            System.err.println("[GAGAL] " + e.getMessage());
            if (e.getCause() != null) {
                System.err.println("Sebab: " + e.getCause().getMessage());
            }
            return;
        }

        // Inisialisasi Inventory dan stok awal berdasarkan NIM mahasiswa 
        Inventory inventory = new Inventory();
        for (Product product : catalog) {
            inventory.addStock(product.getSku(), StudentInfo.stockFor(product.getSku()));
        }

        System.out.println("== Toko Online UMKM ==");
        for (int i = 0; i < catalog.size(); i++) {
            Product product = catalog.get(i);
            System.out.println((i + 1) + ". " + product.getInfo() + " | stok " + inventory.available(product.getSku()));
        }
        if (reader.getSkippedCount() > 0) {
            System.out.println("(Catatan: " + reader.getSkippedCount() + " baris katalog dilewati karena format tidak valid)");
        }

        List<Product> cart = new ArrayList<>();
        try (Scanner in = new Scanner(System.in)) { 
            while (true) {
                System.out.print("Nomor produk (0 = selesai): ");
                int choice;
                try {
                    choice = Integer.parseInt(in.nextLine().trim());
                } catch (NumberFormatException _) {
                    System.out.println("  Input harus berupa angka.");
                    continue;
                }
                if (choice == 0) {
                    break;
                }
                if (choice < 1 || choice > catalog.size()) {
                    System.out.println("  Nomor produk tidak ada.");
                    continue;
                }
                Product product = catalog.get(choice - 1);

                System.out.print("Jumlah: ");
                int quantity;
                try {
                    quantity = Integer.parseInt(in.nextLine().trim());
                } catch (NumberFormatException _) {
                    System.out.println("  Input harus berupa angka.");
                    continue;
                }
                if (quantity <= 0) {
                    System.out.println("  Jumlah harus lebih dari 0.");
                    continue;
                }
                
                for (int q = 0; q < quantity; q++) {
                    cart.add(product);
                }

                System.out.println("  + " + quantity + " x " + product.getName());
            }
        }

        if (cart.isEmpty()) {
            System.out.println("\nKeranjang kosong. Sampai jumpa!");
            return;
        }

        CheckoutService checkout = new CheckoutService(inventory);
        ShippingService regular = new RegularShipping();
        EWalletPayment wallet = new EWalletPayment("DompetKu", StudentInfo.walletBalance());
        
        System.out.println("\nKeranjang: " + cart.size() + " barang, total Reguler Rp"
                + checkout.total(cart, regular));
        
        // Memanggil method buy untuk menangani exception dan mencetak status pesan 
        System.out.println(buy(checkout, cart, regular, wallet));
        System.out.println("Sisa saldo: Rp" + wallet.getBalance());
    }

    /**
     * Method buy untuk menangkap exception toko dan menerjemahkannya ke pesan terstruktur 
     */
    public static String buy(CheckoutService checkout, List<Product> cart,
                             ShippingService shipping, PaymentMethod payment) {
        try {
            long amount = checkout.checkout(cart, shipping, payment);
            return "[OK] " + payment.getName() + " membayar Rp" + amount;
        } catch (InsufficientBalanceException e) {
            return "[SALDO] " + e.getMessage() + " (kurang Rp" + e.getShortfall() + ")";
        } catch (PaymentFailedException e) {
            return "[BAYAR] " + e.getMessage();
        } catch (OutOfStockException e) {
            return "[STOK] " + e.getMessage();
        }
    }
}