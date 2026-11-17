package shop.service;

<<<<<<< HEAD
import java.util.List;
import shop.model.Product;
import shop.payment.PaymentMethod;
import shop.payment.RefundablePayment;
import shop.shipping.Shippable;
import shop.shipping.ShippingService;

public class CheckoutService {
=======
import java.util.ArrayList;
import java.util.List;
import shop.exception.OutOfStockException;
import shop.exception.PaymentFailedException;
import shop.model.Product;
import shop.payment.PaymentMethod;
import shop.payment.RefundablePayment;
import shop.shipping.ShippingService;
import shop.shipping.Shippable;

public class CheckoutService {
    private final Inventory inventory;

    // Konstruktor baru dengan parameter Inventory (L3.d)
    public CheckoutService(Inventory inventory) {
        if (inventory == null) {
            throw new IllegalArgumentException("Inventory is required");
        }
        this.inventory = inventory;
    }

>>>>>>> 44d8345 (Praktikum P07 - Exception 2511533023 M.Fajar Fadhilul Zikri)
    public long subtotal(List<Product> items) {
        long total = 0;
        for (Product product : items) {
            total += product.getFinalPrice();
<<<<<<< HEAD
    }
    return total;
=======
        }
        return total;
>>>>>>> 44d8345 (Praktikum P07 - Exception 2511533023 M.Fajar Fadhilul Zikri)
    }

    public int totalWeight(List<Product> items) {
        int grams = 0;
        for (Product product : items) {
<<<<<<< HEAD
         if (product instanceof Shippable shippable) { // a capability, not a class
            grams += shippable.getWeightGrams();
        }
    }
    return grams;
    }
=======
            if (product instanceof Shippable shippable) { // a capability, not a class
                grams += shippable.getWeightGrams();
            }
        }
        return grams;
    }

>>>>>>> 44d8345 (Praktikum P07 - Exception 2511533023 M.Fajar Fadhilul Zikri)
    public long total(List<Product> items, ShippingService shipping) {
        return subtotal(items) + shipping.calculateCost(totalWeight(items));
    }

<<<<<<< HEAD
    public long grandTotal(List<Product> items, ShippingService shipping, PaymentMethod payment) {
        long totalCost = total(items, shipping);
            return totalCost + payment.adminFee(totalCost);
    }

    public boolean checkout(List<Product> items, ShippingService shipping, PaymentMethod payment) {
        if (items == null || items.isEmpty()) {
            return false;
        }
        long finalAmount = grandTotal(items, shipping, payment);
        return payment.pay(finalAmount);
    }
    public boolean refund(RefundablePayment payment, long amount){
        if (payment == null){
            return false;
        }
        return payment.refund(amount);
    }
}
=======
    /** Reserves stock, then pays. Returns the amount paid. (L3.d) */
    public long checkout(List<Product> items, ShippingService shipping, PaymentMethod payment)
            throws OutOfStockException, PaymentFailedException {
        if (items.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }
        List<Product> reserved = new ArrayList<>();
        try {
            for (Product product : items) {
                inventory.reserve(product.getSku(), 1);
                reserved.add(product);
            }
            long amount = total(items, shipping);
            payment.pay(amount);
            return amount;
        } catch (OutOfStockException | PaymentFailedException e) {
            for (Product product : reserved) {
                inventory.release(product.getSku(), 1); // undo the reservation
            }
            throw e; // rethrow: the caller decides what to tell the customer
        }
    }

    public boolean refund(RefundablePayment payment, long amount) {
        return payment.refund(amount);
    }
}
>>>>>>> 44d8345 (Praktikum P07 - Exception 2511533023 M.Fajar Fadhilul Zikri)
