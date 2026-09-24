package Pekan2;

public class Transaksi {
	private String idTransaksi;
	private String jenis;
	private double nominal;
	
	// construktor
	public Transaksi(String id, String jenis, double nominal) {
		this.idTransaksi = id;
		this.jenis = jenis;
		this.nominal = nominal;
	}
	
	public void cetakDetail() {
        System.out.println("ID: " + idTransaksi + " | Jenis: " + jenis + " | Nominal: Rp" + nominal);
    }

}
