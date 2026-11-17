package shop.payment;

<<<<<<< HEAD
import shop.StudentInfo;
=======
import shop.exception.InsufficientBalanceException;
>>>>>>> 44d8345 (Praktikum P07 - Exception 2511533023 M.Fajar Fadhilul Zikri)

public class CreditCardPayment implements RefundablePayment, InstallmentPayment {
    private final String cardHolder;
    private final long creditLimit;
    private long usedCredit;

    public CreditCardPayment(String cardHolder, long creditLimit) {
        if (cardHolder == null || cardHolder.isBlank()) {
            throw new IllegalArgumentException("Card holder must not be blank");
        }
        if (creditLimit <= 0) {
            throw new IllegalArgumentException("Credit limit must be positive");
        }
        this.cardHolder = cardHolder;
        this.creditLimit = creditLimit;
<<<<<<< HEAD
        this.usedCredit = 0;
    }

    @Override
    public String getName() {
        return "Kartu Kredit (" + cardHolder + ")";
    }

    public long getUsedCredit() {
        return usedCredit;
    }

    @Override
    public boolean pay(long amount) {
        if (amount <= 0 || (usedCredit + amount) > creditLimit) {
            return false;
        }
        usedCredit += amount;
        return true;
=======
    }

    @Override
    public String getName() { return "Kartu Kredit (" + cardHolder + ")"; }

    public long getUsedCredit() { return usedCredit; }

    @Override
    public void pay(long amount) throws InsufficientBalanceException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        long availableLimit = creditLimit - usedCredit;
        if (amount > availableLimit) {
            throw new InsufficientBalanceException(amount, availableLimit);
        }
        usedCredit += amount;
>>>>>>> 44d8345 (Praktikum P07 - Exception 2511533023 M.Fajar Fadhilul Zikri)
    }

    @Override
    public boolean refund(long amount) {
        if (amount <= 0 || amount > usedCredit) {
            return false;
        }
        usedCredit -= amount;
        return true;
    }

    @Override
<<<<<<< HEAD
    public int maxInstallmentMonths() {
        return 12;
    }

    @Override
    public long adminFee(long amount) {
        return amount * StudentInfo.CARD_ADMIN_PERCENT / 100;
    }

    @Override
    public String policy() {
        return RefundablePayment.super.policy() + "; " + InstallmentPayment.super.policy();
    }
}
=======
    public int maxInstallmentMonths() { return 12; }
}
>>>>>>> 44d8345 (Praktikum P07 - Exception 2511533023 M.Fajar Fadhilul Zikri)
