package shop.payment;

<<<<<<< HEAD
public class EWalletPayment implements RefundablePayment{
    private final String provider;
    private long balance;

    public EWalletPayment(String provider, long balance){
        if (provider == null || provider.isBlank()){
            throw new IllegalArgumentException("Provider cannot be null");
        }
        if (balance < 0){
            throw new IllegalArgumentException("Balance cannot be negative");
        }   
        this.provider = provider;
        this.balance = balance;
    }
    @Override 
    public String getName(){
        return "E-Wallet"+ provider ;
    }
    public long getBalance() {
        return balance;
    }
    @Override
    public boolean pay(long amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }
=======
import shop.exception.InsufficientBalanceException;

public class EWalletPayment implements RefundablePayment {
    private final String provider;
    private long balance;

    public EWalletPayment(String provider, long balance) {
        if (provider == null || provider.isBlank()) {
            throw new IllegalArgumentException("Provider must not be blank");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Balance must not be negative");
        }
        this.provider = provider;
        this.balance = balance;
    }

    @Override
    public String getName() { return "E-Wallet " + provider; }

    public long getBalance() { return balance; }

    @Override
    public void pay(long amount) throws InsufficientBalanceException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (amount > balance) {
            throw new InsufficientBalanceException(amount, balance);
        }
        balance -= amount;
    }

>>>>>>> 44d8345 (Praktikum P07 - Exception 2511533023 M.Fajar Fadhilul Zikri)
    @Override
    public boolean refund(long amount) {
        if (amount <= 0) {
            return false;
        }
        balance += amount;
        return true;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 44d8345 (Praktikum P07 - Exception 2511533023 M.Fajar Fadhilul Zikri)
