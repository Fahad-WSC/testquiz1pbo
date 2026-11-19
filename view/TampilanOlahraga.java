package reservasiolahraga.view;

import java.util.Scanner;

public class TampilanOlahraga {
    private Scanner scanner;

    public TampilanOlahraga() {
        this.scanner = new Scanner(System.in);
    }

    public void tampilkanMenuUtama() {
        System.out.println("\n=== SISTEM RESERVASI LAPANGAN OLAHRAGA ===");
        System.out.println("1. Lihat Daftar Lapangan");
        System.out.println("2. Sewa Lapangan (Exception + Strategy + Anonymous Class)");
        System.out.println("3. Kirim Info Mabar (Mediator)");
        System.out.println("4. Lihat Log Pengumuman");
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