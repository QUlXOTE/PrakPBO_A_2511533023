package Pekan4;
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
			System.out.println("7. Simulasi Akhir Bulan (Khusus Tabungan)");
			System.out.println("0. Keluar");
			System.out.print("Pilih Menu: ");
			
			int pilihan = input.nextInt();
			input.nextLine(); 
			
			
			switch (pilihan) {
			case 1:
	            System.out.print("Masukkan Nomor Rekening : ");
	            String nomor = input.nextLine();
	            System.out.print("Masukkan Nama Pemilik   : ");
	            String nama = input.nextLine();
	            System.out.print("Masukkan Saldo Awal     : ");
	            double saldoAwal = input.nextDouble();
	            input.nextLine(); 
	            System.out.print("Masukkan PIN  : ");
	            String pin = input.nextLine();

	            // Pilihan jenis produk rekening
	            System.out.println("Pilih Produk:");
	            System.out.println("1. Tabungan Umum");
	            System.out.println("2. Giro Bisnis");
	            System.out.print("Pilihan (1/2): ");
	            int jenisProduk = input.nextInt();
	            input.nextLine(); 

	            Rekening akunBaru = null;

	            if (jenisProduk == 1) {
	                // Input Suku Bunga untuk Rekening Tabungan
	                System.out.print("Masukkan Suku Bunga (%) : ");
	                double sukuBunga = input.nextDouble();
	                input.nextLine(); 

	                // Instansiasi objek RekeningTabungan
	                akunBaru = new RekeningTabungan(nomor, nama, saldoAwal, pin, sukuBunga);
	                System.out.println("Rekening Tabungan berhasil dibuat!");

	            } else if (jenisProduk == 2) {
	                // Input Batas Overdraft untuk Rekening Giro
	                System.out.print("Masukkan Batas Overdraft : ");
	                double batasOverdraft = input.nextDouble();
	                input.nextLine(); 

	                // Instansiasi objek RekeningGiro
	                akunBaru = new RekeningGiro(nomor, nama, saldoAwal, pin, batasOverdraft);
	                System.out.println("Rekening Giro berhasil dibuat!");

	            } else {
	                System.out.println("Pilihan produk tidak valid! Pendaftaran dibatalkan.");
	                break;
	            }

	            // Upcasting: Menyimpan objek Subclass ke daftarRekening bertipe Rekening
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
	            System.out.println("\n--- Simulasi Akhir Bulan ---");
	            if (akunAktif == null) {
	                System.out.println("Gagal: Silakan pilih / login ke rekening terlebih dahulu!");
	                break;
	            }

	            // Pengecekan tipe objek menggunakan instanceof
	            if (akunAktif instanceof RekeningTabungan) {
	                // Downcasting eksplisit ke tipe RekeningTabungan
	                RekeningTabungan tabungan = (RekeningTabungan) akunAktif;

	                // Memanggil method spesifik milik RekeningTabungan
	                tabungan.tambahBungaAkhirBulan();
	            } else {
	                // Pesan penolakan jika akunAktif bukan RekeningTabungan
	                System.out.println("Gagal: Fitur bunga akhir bulan hanya berlaku untuk Rekening Tabungan.");
	            }
	            break;
				
			case 0:
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
