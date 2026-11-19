package aplikasiakademik.view;

import aplikasiakademik.model.ItemAkademik;
import java.util.List;
import java.util.Scanner;
//(View pada MVC + Scanner + Readability)
public class TerminalView {
    private Scanner scanner;

    public TerminalView() {
        this.scanner = new Scanner(System.in);
    }

    public void tampilkanMenuUtama() {
        System.out.println("\n=== SISTEM MANAJEMEN ITEM AKADEMIK ===");
        System.out.println("1. Tambah Buku");
        System.out.println("2. Tambah Modul Praktikum");
        System.out.println("3. Lihat Semua Item");
        System.out.println("4. Pinjam Item");
        System.out.println("5. Ubah Nama Kampus (Static)");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu: ");
    }

    public String mintaInputTeks(String label) {
        System.out.print(label + ": ");
        return scanner.nextLine().trim();
    }

    public int mintaInputAngka(String label) {
        System.out.print(label + ": ");
        String teks = scanner.nextLine().trim();
        return Integer.parseInt(teks);
    }

    public void tampilkanPesan(String pesan) {
        System.out.println(pesan);
    }

    public void tampilkanDaftarItem(List<? extends ItemAkademik> daftar) {
        System.out.println("\n--- DAFTAR ITEM AKADEMIK ---");
        if (daftar.isEmpty()) {
            System.out.println("Data masih kosong.");
            return;
        }
        for (int i = 0; i < daftar.size(); i++) {
            ItemAkademik item = daftar.get(i);
            System.out.println((i + 1) + ". " + item.getRingkasanDetail());
        }
    }
}