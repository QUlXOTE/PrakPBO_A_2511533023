package shop;

<<<<<<< HEAD
/**
 * Identity and personal parameters for the take-home assignment (Tugas).
 * 1. Write your NIM and name.
 * 2. Run: java -cp out tools.NimParams <your NIM>
 * 3. Copy the three printed values below.
 */
public final class StudentInfo {
    public static final String NIM = "2511533023";   // TODO: e.g. "2401010057"
    public static final String NAME = "M.Fajar Fadhilul Zikri";  // TODO: your full name

    public static final int CARD_ADMIN_PERCENT = 1;   // TODO: from tools.NimParams
    public static final long TRANSFER_ADMIN_FEE = 4000;  // TODO: from tools.NimParams
    public static final int SMS_MAX_LENGTH = 123;       // TODO: from tools.NimParams

    private StudentInfo() {
        // constants only; no objects
=======
/** Your identity. Fill in NIM and NAME before running SelfCheck (L0). */
public final class StudentInfo {
    public static final String NIM = "2511533023"; // TODO L0: your NIM
    public static final String NAME = "M.Fajar Fadhilul Zikri"; // TODO L0: your name

    private StudentInfo() {
    }

    /** Starting e-wallet balance: Rp150,000 to Rp300,000, depends on the NIM. */
    public static long walletBalance() {
        return walletBalance(NIM);
    }

    /** Starting stock of KOPI-001: 2 to 4. */
    public static int coffeeStock() {
        return coffeeStock(NIM);
    }

    /** Starting stock of RDG-002: 1 or 2. */
    public static int rendangStock() {
        return rendangStock(NIM);
    }

    /** Starting stock for any SKU in the shop (5 for other products). */
    public static int stockFor(String sku) {
        return switch (sku) {
            case "KOPI-001" -> coffeeStock();
            case "RDG-002" -> rendangStock();
            default -> 5;
        };
    }

    public static long walletBalance(String nim) {
        return 150_000 + (lastTwoDigits(nim) % 4) * 50_000L;
    }

    public static int coffeeStock(String nim) {
        return 2 + (lastTwoDigits(nim) % 10) % 3;
    }

    public static int rendangStock(String nim) {
        return 1 + (lastTwoDigits(nim) / 10) % 2;
    }

    private static int lastTwoDigits(String nim) {
        String digits = nim.replaceAll("\\D", "");
        if (digits.length() < 2) {
            return 0;
        }
        return Integer.parseInt(digits.substring(digits.length() - 2));
>>>>>>> 44d8345 (Praktikum P07 - Exception 2511533023 M.Fajar Fadhilul Zikri)
    }
}
