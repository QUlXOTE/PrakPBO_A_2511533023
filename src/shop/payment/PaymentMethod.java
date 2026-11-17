package shop.payment;

<<<<<<< HEAD
public interface PaymentMethod {
    String getName();
    boolean pay(long amount);
    default long adminFee(long amount) {
        return 0;
    }
}
=======
import shop.exception.PaymentFailedException; 
 
/** Contract for every payment method. */ 
public interface PaymentMethod { 
    String getName(); 
 
    /** Completes normally on success; throws when the payment fails. */ 
    void pay(long amount) throws PaymentFailedException; 
} 
>>>>>>> 44d8345 (Praktikum P07 - Exception 2511533023 M.Fajar Fadhilul Zikri)
