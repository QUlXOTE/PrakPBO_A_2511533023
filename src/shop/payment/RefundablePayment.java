package shop.payment;

public interface RefundablePayment extends PaymentMethod {
    boolean refund(long amount);
<<<<<<< HEAD
    default String policy() {
        return "Refund maksimal 7 hari";
    }
=======
>>>>>>> 44d8345 (Praktikum P07 - Exception 2511533023 M.Fajar Fadhilul Zikri)
}
