package Pekan1;
import java.util.ArrayList;

import Pekan2.Transaksi;
public class Rekening {
	String nomorRekening;
	String namaPemilik;
	double saldo;
	
	ArrayList<Transaksi>riwayatTransaksi;
	
	public Rekening(String nomor, String nama, double saldoAwal) {
		nomorRekening = nomor;
		namaPemilik = nama;
		saldo = saldoAwal;
		
		this.riwayatTransaksi = new ArrayList<>();
		
		System.out.println("Rekening atas nama " + namaPemilik + " Berhasil dibuat dengan saldo Rp" + saldo);
	}
	
	public void setorTunai(double nominal) {
		if (nominal > 0) {
			saldo += nominal;
			
			String idTrx = "TRX-S-" + System.currentTimeMillis();
			Transaksi trxBaru = new Transaksi(idTrx, "Kredit", nominal);
			riwayatTransaksi.add(trxBaru);
			
			System.out.println("Setor tunai Rp" + nominal + " Berhasil. Saldo saat ini: Rp" + saldo);
		} else {
			System.out.println("Gagal: Nominal setor harus lebih dari 0!");
		}
	}

	
	public void tarikTunai(double nominal) {
		if (nominal < 10000) {
			
			String idTrx = "TRX-T-" + System.currentTimeMillis();
			Transaksi trxBaru = new Transaksi(idTrx, "Debit", nominal);
			riwayatTransaksi.add(trxBaru);
			
			System.out.println("Transaksi Gagal: Minimal nominal penarikan 10.000");
		} else if (nominal > saldo) {
			System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: Rp" + saldo);
		} else {
			saldo -= nominal;
			System.out.println("Tarik tunai Rp" + nominal + " Berhasil. Saldo saat ini: Rp" + saldo);
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


	public String getNomorRekening() {
		return nomorRekening;
	}
}
