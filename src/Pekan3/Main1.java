package Pekan3;
import java.util.ArrayList;
import java.util.Scanner;

public class Main1 {
	private static Rekening akunAktif;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		ArrayList<Rekening> daftarRekening = new ArrayList<>();
		akunAktif = null;
		boolean isRunning = true;
		
		System.out.println("=== SISTEM PERBANKAN MINI ===");
		
		while (isRunning) {
			System.out.println("\n-----------------------------");
			if (akunAktif != null) {
				
				System.out.println("Akun Aktif: " + akunAktif.getNamaPemilik() + " (" + akunAktif.getNomorRekening() + ")");
			} else {
				System.out.println("Akun Aktif: Belum Ada");
			}
			System.out.println("-----------------------------");
			System.out.println("Menu Utama:");
			System.out.println("1. Buka Rekening Baru");
			System.out.println("2. Setor Tunai");
			System.out.println("3. Tarik Tunai");
			System.out.println("4. Cek Informasi Rekening");
			System.out.println("5. Ganti Akun");
			System.out.println("6. Cetak Mutasi (Riwayat)");
			System.out.println("7. Keluar");
			System.out.print("Pilih Menu: ");
			
			int pilihan = input.nextInt();
			input.nextLine(); 
			
			switch (pilihan) {
			case 1: 
				System.out.print("Masukkan No rekening: ");
				String no = input.nextLine();
				System.out.print("Masukkan Nama Pemilik: ");
				String nama = input.nextLine();
				System.out.print("Masukkan Saldo Awal: ");
				double saldo = input.nextDouble();
				input.nextLine(); 
				
				//  Meminta input PIN 6 digit
				System.out.print("Masukkan PIN (6 digit): ");
				String pin = input.nextLine();
				
				
				Rekening akunBaru = new Rekening(no, nama, saldo, pin);
				daftarRekening.add(akunBaru);
				
				akunAktif = akunBaru;
				break;
				
			case 2:
				if (akunAktif == null) {
					System.out.println("Error: Mohon maaf, anda belum memilih/memiliki rekening!");
				} else {
					System.out.print("Masukkan nominal setor: ");
					double setor = input.nextDouble();
					akunAktif.setorTunai(setor);
				}
				break;
				
			case 3:
				if (akunAktif == null) {
					System.out.println("Error: Mohon maaf, anda belum memilih/memiliki rekening!");
				} else {
					// Minta PIN sebelum Tarik Tunai
					System.out.print("Masukkan PIN Anda: ");
					String inputPinTarik = input.nextLine();
					
					if (akunAktif.otentikasi(inputPinTarik)) {
						System.out.print("Masukkan nominal tarik: ");
						double tarik = input.nextDouble();
						akunAktif.tarikTunai(tarik);
					} else {
						System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
					}
				}
				break;
				
			case 4: 
				if (akunAktif == null) {
					System.out.println("Error: Anda belum memilih/membuka rekening!");
				} else {
					akunAktif.cekInformasi();
				}
				break;
				
			case 5:
				if (daftarRekening.isEmpty()) {
					System.out.println("Belum ada rekening yang terdaftar di dalam sistem.");
				} else {
					System.out.print("Masukkan No Rekening yang dicari: ");
					String noCari = input.nextLine();
					boolean ditemukan = false;
					
					for (Rekening rek : daftarRekening) {
						if (rek.getNomorRekening().equalsIgnoreCase(noCari)) {
							akunAktif = rek;
							ditemukan = true;
							// Menggunakan getter getNamaPemilik()
							System.out.println("Berhasil beralih ke rekening atas nama: " + akunAktif.getNamaPemilik());
							break;
						}
					}
					
					if (!ditemukan) {
						System.out.println("Error: Nomor rekening tidak ditemukan!");
					}
				}
				break;
			
			case 6:
				if (akunAktif == null) {
					System.out.println("Error: Anda belum memilih/membuka rekening!");
				} else {
					// Minta PIN sebelum Cetak Mutasi
					System.out.print("Masukkan PIN Anda: ");
					String inputPinMutasi = input.nextLine();
					
					if (akunAktif.otentikasi(inputPinMutasi)) {
						akunAktif.cetakMutasi();
					} else {
						System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
					}
				}
				break;
				
			case 7:
				isRunning = false;
				System.out.println("Sistem ditutup. Terima kasih!");
				break;
				
			default:
				System.out.println("Pilihan tidak valid!");
			}
		}
		input.close();
	}
}
