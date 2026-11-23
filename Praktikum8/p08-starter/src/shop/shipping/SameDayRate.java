package shop.shipping;

public class SameDayRate implements ShippingRate {
    @Override
    public long costFor(int grams) {
        if (grams == 0) {
            return 0;
        }
        int kg = (grams + 999) / 1000;
        return kg <= 3 ? 25_000 : 25_000 + (kg - 3) * 10_000L;
    }
}