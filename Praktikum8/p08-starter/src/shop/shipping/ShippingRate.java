package shop.shipping;

/** Aturan tarif ongkir yang bisa ditukar. */
public interface ShippingRate {
    long costFor(int grams);
}