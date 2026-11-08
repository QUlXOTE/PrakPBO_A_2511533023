package review.fixed;

import java.util.ArrayList;
import java.util.List;

/*
 * T3 - CODE REVIEW REFACTORING
 * Perbaikan minimal 3 masalah desain:
 * 1. [Address] Dibuat immutable (setter dihapus, field final) untuk mencegah shared-state mutation.
 * 2. [Courier] Menggunakan polimorfisme murni alih-alih string-based switch yang rentan typo.
 * 3. [Discount] Menghapus 'instanceof' anti-pattern dan menggantinya dengan method overriding.
 */

// 1. PERBAIKAN MUTABILITAS: Address dijadikan immutable class (setter dihapus)
final class Address {
    private final String street;
    private final String city;

    Address(String street, String city) {
        this.street = street;
        this.city = city;
    }

    // SETTER DIHAPUS: Mencegah perubahan data secara tidak sengaja pada objek yang sama.

    @Override
    public String toString() {
        return street + ", " + city;
    }
}

interface StoreOperations {
    boolean pay(long amount);
    boolean refund(long amount);
    long ship(int weightGrams);
    String printInvoice();
}

class GiftVoucher implements StoreOperations {
    private long balance = 100_000L;

    @Override
    public boolean pay(long amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

    @Override
    public boolean refund(long amount) {
        return false; // Diperbaiki agar tidak melempar UnsupportedOperationException
    }

    @Override
    public long ship(int weightGrams) {
        return 0; // Voucher tidak dikirim
    }

    @Override
    public String printInvoice() {
        // Disesuaikan formatnya agar menghasilkan "sisa Rp70000"
        return "sisa Rp" + balance;
    }
}

// 2. PERBAIKAN POLIMORFISME KURIR: Menghapus string-based switch dengan abstract class/subclass
abstract class Courier {
    public abstract long cost(int kilograms);
}

class ExpressCourier extends Courier {
    @Override
    public long cost(int kilograms) {
        return kilograms * 18_000L; // 2 kg * 18.000 = 36.000
    }
}

class Cart {
    private final List<Item> items = new ArrayList<>();

    class Item {
        private final String name;
        private final long price;
        private final int quantity;

        Item(String name, long price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        long subtotal() {
            return price * quantity;
        }
    }

    void add(Item item) {
        items.add(item);
    }

    long total() {
        long total = 0;
        for (Item item : items) {
            total += item.subtotal();
        }
        return total;
    }
}

class SimpleOrder {
    private final String id;
    private final Address address;

    SimpleOrder(String id, Address address) {
        this.id = id;
        this.address = address;
    }

    @Override
    public String toString() {
        return id + " -> " + address;
    }
}

// 3. PERBAIKAN DISCOUNT: Menghapus 'instanceof' dengan menerapkan interface method polimorfik
interface Discount {
    long apply(long price);
}

class MemberDiscount implements Discount {
    @Override
    public long apply(long price) {
        return price - (price * 5 / 100);
    }
}

class FlashSaleDiscount implements Discount {
    @Override
    public long apply(long price) {
        return price - (price * 30 / 100); // Diskon 30% dari 100.000 = 70.000
    }
}

public class BadShopRefactored {
    public static void main(String[] args) {
        Cart cart = new Cart();
        cart.add(cart.new Item("Kopi Gayo", 85_000, 2));
        System.out.println("Total cart: " + cart.total());

        // Menggunakan kurir polimorfik
        Courier courier = new ExpressCourier();
        System.out.println("Ongkir Ekspres 2 kg: " + courier.cost(2));

        // Memperbaiki masalah shared reference dengan membuat instance Address terpisah untuk ORD-2
        Address home1 = new Address("Jl. Sudirman 10", "Padang");
        Address home2 = new Address("Jl. Sudirman 10", "Bukittinggi");
        SimpleOrder first = new SimpleOrder("ORD-1", home1);
        SimpleOrder second = new SimpleOrder("ORD-2", home2);
        
        System.out.println(first);
        System.out.println(second);

        // Menggunakan implementasi diskon polimorfik
        Discount flashSale = new FlashSaleDiscount();
        System.out.println("Harga flash sale: " + flashSale.apply(100_000));

        StoreOperations voucher = new GiftVoucher();
        System.out.println(voucher.pay(30_000) + " | " + voucher.printInvoice());
    }
}
