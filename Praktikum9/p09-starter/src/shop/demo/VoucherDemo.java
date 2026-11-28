package shop.demo;

import shop.model.Voucher;

/** Demo pembuatan voucher sah dan voucher tidak sah menggunakan Voucher.Builder. */
public class VoucherDemo {

    public static void main(String[] args) {
        // 1. Voucher sah (percent berada di rentang 1..50)
        Voucher voucherSah = new Voucher.Builder("KOPI20", 20)
                .minimumPurchase(50_000)
                .description("Diskon 20% untuk minimal pembelian Rp50.000")
                .build();
        System.out.println("Voucher sah berhasil dibuat: " + voucherSah.getCode()
                + " (Diskon " + voucherSah.getPercent() + "%, Min. belanja Rp"
                + voucherSah.getMinimumPurchase() + ")");

        // 2. Voucher tidak sah (percent > 50, ditolak saat build())
        try {
            Voucher voucherTidakSah = new Voucher.Builder("DISKON75", 75)
                    .description("Diskon melebihi batas")
                    .build();
            System.out.println("Voucher tidak sah DITERIMA: " + voucherTidakSah.getCode());
        } catch (IllegalStateException e) {
            System.out.println("Voucher tidak sah ditolak -> " + e.getMessage());
        }
    }
}