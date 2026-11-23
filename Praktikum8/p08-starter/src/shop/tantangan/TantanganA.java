package shop.tantangan;

import shop.discount.NoDiscount;
import shop.model.Address;
import shop.model.DigitalProduct;
import shop.model.Order;
import shop.model.PhysicalProduct;
import shop.service.Bill;
import shop.service.OrderCalculator;
import shop.shipping.RegularRate;
import shop.shipping.SameDayRate;
import shop.util.Rupiah;
import tools.NimParams;

public class TantanganA {
    static final PhysicalProduct RENDANG = new PhysicalProduct("RDG", "Rendang Kemasan 500g", 120_000, 500);
    static final PhysicalProduct TAS = new PhysicalProduct("TAS", "Tas Anyaman Pandan", 150_000, 800);
    static final DigitalProduct EBOOK = new DigitalProduct("EBK", "E-book Resep Kopi", 45_000,
            "https://toko.example/unduh/ebk");

    public static void main(String[] args) {
        OrderCalculator regular = new OrderCalculator(new RegularRate(NimParams.ratePerKg()));
        OrderCalculator sameDay = new OrderCalculator(new SameDayRate());

        Order o1 = order("A1").add(TAS, 1).add(RENDANG, 1);   // 1.300 g -> 2 kg
        Order o2 = order("A2").add(TAS, 5);                    // 4.000 g -> 4 kg
        Order o3 = order("A3").add(EBOOK, 1);                  // 0 g

        show("A1 (2 kg)", regular.calculate(o1, new NoDiscount()), sameDay.calculate(o1, new NoDiscount()));
        show("A2 (4 kg)", regular.calculate(o2, new NoDiscount()), sameDay.calculate(o2, new NoDiscount()));
        show("A3 (digital)", regular.calculate(o3, new NoDiscount()), sameDay.calculate(o3, new NoDiscount()));
    }

    static Order order(String id) {
        return new Order(id, new Address("Sari", "Medan"));
    }

    static void show(String label, Bill reg, Bill same) {
        System.out.println(label + " -> Reguler: " + Rupiah.format(reg.shipping())
                + " | Same Day: " + Rupiah.format(same.shipping()));
    }
}