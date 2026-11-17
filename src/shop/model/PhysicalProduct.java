package shop.model;

import shop.shipping.Shippable;

public class PhysicalProduct extends Product implements Shippable {
    private final int weightGrams;

<<<<<<< HEAD
    public PhysicalProduct(String sku, String name, long price, int weightGrams){
        super(sku, name, price);
        if (weightGrams <= 0){
=======
    public PhysicalProduct(String sku, String name, long price, int weightGrams) {
        super(sku, name, price);
        if (weightGrams <= 0) {
>>>>>>> 44d8345 (Praktikum P07 - Exception 2511533023 M.Fajar Fadhilul Zikri)
            throw new IllegalArgumentException("Weight must be positive");
        }
        this.weightGrams = weightGrams;
    }
<<<<<<< HEAD
    @Override 
    public int getWeightGrams() {return weightGrams;}
    @Override 
    public String category() {return "Fisik";}

=======

    @Override
    public int getWeightGrams() { return weightGrams; }

    @Override
    public String category() { return "Fisik"; }
>>>>>>> 44d8345 (Praktikum P07 - Exception 2511533023 M.Fajar Fadhilul Zikri)
}
