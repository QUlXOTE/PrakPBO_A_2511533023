package shop.model;

public abstract class Product {
    private final String sku;
    private final String name;
<<<<<<< HEAD
    private final long price;

    protected Product(String sku, String name, long price){
       // Product p = new Product("X", "X", 1000);
        if (sku == null || sku.isBlank()){
            throw new IllegalArgumentException("SKU must be blank");
        }
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("Name must be blank");
        }
        if (price < 0){
            throw new IllegalArgumentException("Price must be non-negative");
=======
    private final long price; // in rupiah

    protected Product(String sku, String name, long price) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("SKU must not be blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be blank");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price must not be negative");
>>>>>>> 44d8345 (Praktikum P07 - Exception 2511533023 M.Fajar Fadhilul Zikri)
        }
        this.sku = sku;
        this.name = name;
        this.price = price;
    }
<<<<<<< HEAD
    public String getsku() {return sku;}
    public String getName() {return name;}
    public long getPrice() {return price;}

    /** Price the customer actually pays. Subclasses may override it. */
    public long getFinalPrice() { return price; }
 /** Every concrete product must state its category. */
    public abstract String category();
 /** A concrete method that relies on the abstract method category(). */
    public String getInfo() {
       return "[" + category() + "] " + name + " - Rp" + getFinalPrice();
=======

    public String getSku() { return sku; }
    public String getName() { return name; }
    public long getPrice() { return price; }

    /** Price the customer actually pays. Subclasses may override it. */
    public long getFinalPrice() { return price; }

    /** Every concrete product must state its category. */
    public abstract String category();

    /** A concrete method that relies on the abstract method category(). */
    public String getInfo() {
        return "[" + category() + "] " + name + " - Rp" + getFinalPrice();
>>>>>>> 44d8345 (Praktikum P07 - Exception 2511533023 M.Fajar Fadhilul Zikri)
    }
}
