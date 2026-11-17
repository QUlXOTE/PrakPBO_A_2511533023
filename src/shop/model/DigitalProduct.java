package shop.model;

import shop.discount.Discountable;

public class DigitalProduct extends Product implements Discountable {
<<<<<<< HEAD
    private final String downloadUrl;
    private static final int DIGITAL_DISCOUNT_PERCENT = 10;

    public DigitalProduct(String sku, String name, long price, String downloadUrl){
        super(sku, name, price);
        if (downloadUrl == null || downloadUrl.isBlank()){
            throw new IllegalArgumentException("Download URL cannot be blank");
=======
    private static final int DIGITAL_DISCOUNT_PERCENT = 10;

    private final String downloadUrl;

    public DigitalProduct(String sku, String name, long price, String downloadUrl) {
        super(sku, name, price);
        if (downloadUrl == null || downloadUrl.isBlank()) {
            throw new IllegalArgumentException("Download URL must not be blank");
>>>>>>> 44d8345 (Praktikum P07 - Exception 2511533023 M.Fajar Fadhilul Zikri)
        }
        this.downloadUrl = downloadUrl;
    }

<<<<<<< HEAD
    public String getDownloadUrl() {
        return downloadUrl;
    }
    @Override 
    public String category(){
        return "Digital";
    }
    @Override
    public int discountPercent() {
        return DIGITAL_DISCOUNT_PERCENT;
    }

    @Override
    public long getFinalPrice() {
        return applyDiscount(getPrice());
    }

}
=======
    public String getDownloadUrl() { return downloadUrl; }

    @Override
    public String category() { return "Digital"; }

    @Override
    public int discountPercent() { return DIGITAL_DISCOUNT_PERCENT; }

    @Override
    public long getFinalPrice() { return applyDiscount(getPrice()); }
}
>>>>>>> 44d8345 (Praktikum P07 - Exception 2511533023 M.Fajar Fadhilul Zikri)
