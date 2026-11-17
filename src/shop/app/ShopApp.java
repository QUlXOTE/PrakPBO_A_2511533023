package shop.app;

<<<<<<< HEAD
import java.util.List;

import shop.model.Address;
import shop.model.DigitalProduct;
import shop.model.Order;
import shop.model.PhysicalProduct;
import shop.model.Product;
import shop.payment.BankTransferPayment;
import shop.payment.CreditCardPayment;
import shop.payment.EWalletPayment;
import shop.payment.PaymentMethod;
import shop.service.CheckoutService;
import shop.shipping.ExpressShipping;
=======
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
>>>>>>> 44d8345 (Praktikum P07 - Exception 2511533023 M.Fajar Fadhilul Zikri)
import shop.shipping.RegularShipping;
import shop.shipping.ShippingService;

public class ShopApp {
<<<<<<< HEAD
    public static void main(String[] args) {
        // L1: abstract class and abstract method
        Product coffee = new PhysicalProduct("KOPI-001", "Kopi Arabika Gayo 250g", 85_000, 300);
        Product rendang = new PhysicalProduct("RDG-002", "Rendang Kemasan 250g", 120_000, 400);
        Product ebook = new DigitalProduct("EBK-003", "E-book Resep Kue Nusantara", 50_000,
                "https://toko-umkm.example/unduh/ebk-003");
        List<Product> cart = List.of(coffee, rendang, ebook);

        System.out.println("== Katalog ==");
        for (Product product : cart) {
            System.out.println(product.getInfo());
        }

        // L2: interface and program to an interface
        CheckoutService checkout = new CheckoutService();
        ShippingService regular = new RegularShipping();
        ShippingService express = new ExpressShipping();

        System.out.println("\n== Ringkasan ==");
        System.out.println("Subtotal      : Rp" + checkout.subtotal(cart));
        System.out.println("Berat kiriman : " + checkout.totalWeight(cart) + " g");
        System.out.println("Total Reguler : Rp" + checkout.total(cart, regular));
        System.out.println("Total Ekspres : Rp" + checkout.total(cart, express));

        EWalletPayment wallet = new EWalletPayment("DompetKu", 300_000);
        PaymentMethod transfer = new BankTransferPayment("Bank Nagari");

        System.out.println("\n== Pembayaran ==");
        System.out.println(wallet.getName() + " berhasil? "
                + checkout.checkout(cart, regular, wallet));
        System.out.println("Sisa saldo: Rp" + wallet.getBalance());
        System.out.println(transfer.getName() + " berhasil? "
                + checkout.checkout(cart, express, transfer));

        // L3: interface inheritance, multiple interfaces, default method
        CreditCardPayment card = new CreditCardPayment("Siti Rahma", 1_000_000);
        System.out.println(card.getName() + " berhasil? "
                + checkout.checkout(cart, express, card));
        System.out.println("Cicilan 3 bulan: Rp"
                + card.monthlyInstallment(checkout.total(cart, express), 3));

        System.out.println("\n== Refund e-book ==");
        System.out.println("Refund e-wallet berhasil? "
                + checkout.refund(wallet, ebook.getFinalPrice()));
        System.out.println("Saldo setelah refund: Rp" + wallet.getBalance());
        //checkout.refund(transfer, 1_000); // compile error: BankTransferPayment is not RefundablePayment

        // L5: static nested, inner, and anonymous class
        Address address = new Address("Jl. Moh Hatta No. 10", "Padang", "25121");
        Order order = new Order("ORD-0001", address);
        order.addLine(coffee, 2);
        order.addLine(ebook, 1);

        Order.Receipt receipt = order.new Receipt(); // inner object needs an outer object
        System.out.println(receipt.toText());

        PaymentMethod voucher = new PaymentMethod() { // anonymous class
            private long remaining = 50_000;

            @Override
            public String getName() {
                return "Voucher Promo Hari Ini";
            }

            @Override
            public boolean pay(long amount) {
                if (amount <= 0 || amount > remaining) {
                    return false;
                }
                remaining -= amount;
                return true;
            }
        };
        System.out.println("\n" + voucher.getName() + " untuk e-book berhasil? "
                + checkout.checkout(List.of(ebook), regular, voucher));
=======

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
>>>>>>> 44d8345 (Praktikum P07 - Exception 2511533023 M.Fajar Fadhilul Zikri)
    }
}