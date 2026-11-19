package belanjaleveltinggi.view;

import java.util.Scanner;

public class BelanjaView {
    private Scanner scanner;

    public BelanjaView() {
        this.scanner = new Scanner(System.in);
    }

    public void tampilkanMenu() {
        System.out.println("\n=== BELANJA & KUPON (TOPIK SIMPEL, LEVEL TINGGI) ===");
        System.out.println("1. Lihat Barang di Rak");
        System.out.println("2. Hitung Belanja + Kupon (Strategy + Custom Exception)");
        System.out.println("3. Kirim Info ke Kasir Lain (Mediator)");
        System.out.println("4. Lihat Log Kasir");
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