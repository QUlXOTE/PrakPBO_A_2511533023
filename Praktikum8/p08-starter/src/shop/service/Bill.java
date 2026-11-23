package shop.service;

/** rincian tagihan satu pesanan  */
public record Bill(long subtotal, long discount, long shipping, long total) {}
