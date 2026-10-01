package Pekan4;

public class RekeningGiro extends Rekening {
    // Atribut spesifik yang hanya dimiliki oleh Giro
    private double batasOverdraft;

    // Constructor Subclass
    public RekeningGiro(String nomor, String nama, double saldoAwal, String pinAwal, double batasOverdraft) {
        // Memanggil constructor kelas induk (Rekening)
        super(nomor, nama, saldoAwal, pinAwal);
        this.batasOverdraft = batasOverdraft;
    }

    // Getter untuk batas overdraft
    public double getBatasOverdraft() {
        return batasOverdraft;
    }
}
