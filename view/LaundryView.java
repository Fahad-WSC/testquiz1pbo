package laundrysimpel.view;

import java.util.List;
import java.util.Scanner;
import laundrysimpel.model.PaketLaundry;

public class LaundryView {
    private Scanner scanner;

    public LaundryView() {
        this.scanner = new Scanner(System.in);
    }

    public void tampilMenu() {
        System.out.println("\n=== LAUNDRY SIMPEL ===");
        System.out.println("1. Tambah Pesanan");
        System.out.println("2. Lihat Semua Pesanan");
        System.out.println("0. Keluar");
        System.out.print("Pilih: ");
    }

    public String mintaTeks(String label) {
        System.out.print(label + ": ");
        return scanner.nextLine().trim();
    }

    public double mintaAngkaDesimal(String label) {
        System.out.print(label + ": ");
        return Double.parseDouble(scanner.nextLine().trim());
    }

    public void tampilPesan(String pesan) {
        System.out.println(pesan);
    }

    public void tampilDaftar(List<? extends PaketLaundry> list) {
        System.out.println("\n--- DAFTAR PESANAN ---");
        if (list.isEmpty()) {
            System.out.println("Belum ada pesanan.");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).getInfo());
        }
    }
}