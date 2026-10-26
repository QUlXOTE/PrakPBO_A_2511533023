package Pekan5;
import java.util.ArrayList;

public class Rekening {
    // 1. Mengunci atribut menjadi private
    private String nomorRekening;
    private String namaPemilik;
    protected double saldo;
    private String pin;  // Data sensitif
    protected ArrayList<Transaksi> riwayatTransaksi;

    // 2. Modifikasi Constructor untuk menerima PIN
    public Rekening(String nomor, String nama, double saldoAwal, String pinAwal) {
        this.nomorRekening = nomor;
        this.namaPemilik = nama;
        this.saldo = saldoAwal;

        // Validasi PIN saat Instansiasi / Constructor
        if (pinAwal != null && pinAwal.length() == 6) {
            this.pin = pinAwal;
        } else {
            System.out.println("Peringatan: PIN harus 6 digit! Menggunakan PIN default 123456");
            this.pin = "123456";
        }

        this.riwayatTransaksi = new ArrayList<>();
        System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat dengan saldo Rp" + saldo);
    }

    // 3. Getter untuk atribut yang diizinkan dibaca publik
    public String getNomorRekening() {
        return nomorRekening;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    // 4. Method Otentikasi Internal PIN 
    public boolean otentikasi(String inputPin) {
        return this.pin != null && this.pin.equals(inputPin);
    }

    
    public void setorTunai(double nominal) {
        if (nominal > 0) {
            this.saldo += nominal;

            String idTrx = "TRX-S-" + System.currentTimeMillis();
            Transaksi trxBaru = new Transaksi(idTrx, "Kredit", nominal);
            riwayatTransaksi.add(trxBaru);

            System.out.println("Setor tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
        } else {
            System.out.println("Gagal: Nominal setor harus lebih dari 0!");
        }
    }

    public boolean tarikTunai(double nominal) { // diubah dari void ke boolean
        if (nominal <= 0) {
            System.out.println("Gagal: Nominal tarik harus lebih dari 0!");
            return false;
        } else if (nominal > saldo) {
            System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: Rp" + saldo);
            return false;
        } else {
            saldo -= nominal;

            String idTrx = "TRX-T-" + System.currentTimeMillis();
            riwayatTransaksi.add(new Transaksi(idTrx, "Debit", nominal));

            System.out.println("Tarik tunai Rp" + nominal + " berhasil. Sisa saldo: Rp" + saldo);
            return true; // Transaksi sukses
        }
    }
    
     // method transfer dengan parameter polimorfik
    public void transfer(Rekening tujuan, double nominal, String pinInput) {
        // 1. Otentikasi
        if (!otentikasi(pinInput)) {
            System.out.println("Transfer dibatalkan: PIN salah.");
            return;
        }

        // Validasi tambahan: tidak boleh ke rekening sendiri
        if (tujuan == this) {
            System.out.println("Transfer dibatalkan: Rekening tujuan tidak boleh sama dengan rekening asal.");
            return;
        }

        // 2. Tarik tunai (polimorfik: jika this adalah Giro, aturan overdraft dipakai)
        boolean berhasil = this.tarikTunai(nominal);

        // 3. Jika penarikan sukses, setor ke rekening tujuan
        if (berhasil) {
            tujuan.setorTunai(nominal);
            System.out.println("Transfer Rp" + nominal + " ke rekening "
                    + tujuan.getNomorRekening() + " berhasil.");
        } else {
            System.out.println("Transfer gagal.");
        }
    }

    public void cetakMutasi() {
        System.out.println("\n=== MUTASI REKENING ===");
        System.out.println("NO. Rekening : " + nomorRekening);
        System.out.println("Nama Pemilik : " + namaPemilik);
        System.out.println("--------------------------------");

        if (riwayatTransaksi.isEmpty()) {
            System.out.println("Belum ada transaksi pada rekening ini");
        } else {
            for (Transaksi trx : riwayatTransaksi) {
                trx.cetakDetail();
            }
        }
    }

    public void cekInformasi() {
        System.out.println("--- INFO REKENING ---");
        System.out.println("NO. Rekening : " + nomorRekening);
        System.out.println("Nama Pemilik : " + namaPemilik);
        System.out.println("Saldo Akhir  : Rp" + saldo);
        System.out.println("--------------------");
    }
}
