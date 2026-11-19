package manajemenproyek.view;

import java.util.Scanner;

public class TampilanSprint {
    private Scanner scanner;

    public TampilanSprint() {
        this.scanner = new Scanner(System.in);
    }

    public void tampilkanMenu() {
        System.out.println("\n=== MANAJEMEN SPRINT TIM (LEVEL LANJUT) ===");
        System.out.println("1. Tambah Tugas Pengembangan");
        System.out.println("2. Lihat Semua Tugas di Sprint");
        System.out.println("3. Hitung Skor Tugas (Strategy Pattern)");
        System.out.println("4. Selesaikan & Cetak Laporan Tugas (Anonymous Inner Class)");
        System.out.println("5. Kirim Update ke Kanal Tim (Mediator + Filter)");
        System.out.println("6. Lihat Log Aktivitas Tim");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu: ");
    }

    public String mintaTeks(String label) {
        System.out.print(label + ": ");
        return scanner.nextLine().trim();
    }

    public int mintaAngka(String label) {
        System.out.print(label + ": ");
        return Integer.parseInt(scanner.nextLine().trim());
    }

    public void tampilkanPesan(String pesan) {
        System.out.println(pesan);
    }
}