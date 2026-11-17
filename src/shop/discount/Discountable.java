package shop.discount;

public interface Discountable {
<<<<<<< HEAD
=======

>>>>>>> 44d8345 (Praktikum P07 - Exception 2511533023 M.Fajar Fadhilul Zikri)
    int discountPercent(); // abstract: each class decides

    default long applyDiscount(long price) {
        return price - percentOf(price, safePercent());
    }

    static long percentOf(long amount, int percent) {
        return amount * percent / 100;
    }

    private int safePercent() {
        int percent = discountPercent();
        if (percent < 0) {
            return 0;
        }
        return Math.min(percent, 100);
    }
}
