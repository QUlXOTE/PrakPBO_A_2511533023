package shop.payment;

<<<<<<< HEAD
import shop.StudentInfo;

public class BankTransferPayment implements PaymentMethod {
    private final String bankName;
=======
import shop.exception.PaymentFailedException;

import shop.exception.PaymentFailedException;

public class BankTransferPayment implements PaymentMethod {
    private final String bankName;
    private boolean online = true; // simulation switch
>>>>>>> 44d8345 (Praktikum P07 - Exception 2511533023 M.Fajar Fadhilul Zikri)

    public BankTransferPayment(String bankName) {
        if (bankName == null || bankName.isBlank()) {
            throw new IllegalArgumentException("Bank name must not be blank");
        }
        this.bankName = bankName;
    }

    @Override
<<<<<<< HEAD
    public String getName() {
        return "Transfer " + bankName;
    }

    @Override
    public boolean pay(long amount) {
        return amount > 0;
    }

    @Override
    public long adminFee(long amount) {
        return StudentInfo.TRANSFER_ADMIN_FEE;
    }
=======
    public String getName() { return "Transfer " + bankName; }

    /** Simulates a bank outage when set to false. */
    public void setOnline(boolean online) { this.online = online; }

    @Override 
    public void pay(long amount) throws PaymentFailedException { 
        if (amount <= 0) { 
            throw new IllegalArgumentException("Amount must be positive"); 
        } 
        if (!online) { 
            throw new PaymentFailedException(bankName + " sedang gangguan, coba lagi nanti"); 
        } 
    } 
>>>>>>> 44d8345 (Praktikum P07 - Exception 2511533023 M.Fajar Fadhilul Zikri)
}
