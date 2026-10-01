package Pekan4;

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
	
	// getter (Read-Only)
	public String getIdTransaksi() {return idTransaksi; }
	public String getJenis() { return jenis; }
	public double getNominal() { return nominal; }
	
	public void cetakDetail() {
        System.out.println("ID: " + idTransaksi + " | Jenis: " + jenis + " | Nominal: Rp" + nominal);
    }

}
