package latihandosen.view;

import java.util.Scanner;

public class TampilanKafeChat {
    private Scanner scanner;

    public TampilanKafeChat() {
        this.scanner = new Scanner(System.in);
    }

    public void tampilkanMenu() {
        System.out.println("\n=== LATIHAN GABUNGAN GAYA SOAL DOSEN ===");
        System.out.println("1. Pesan Kopi & Bayar (Strategy + Custom Exception)");
        System.out.println("2. Kirim Pesan di Ruang Obrolan (Mediator + Filter + Mute)");
        System.out.println("3. Lihat Riwayat Chat");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu: ");
    }

    public String bacaTeks(String label) {
        System.out.print(label + ": ");
        return scanner.nextLine().trim();
    }

    public int bacaAngka(String label) {
        System.out.print(label + ": ");
        return Integer.parseInt(scanner.nextLine().trim());
    }

    public void tampilkanPesan(String pesan) {
        System.out.println(pesan);
    }
}