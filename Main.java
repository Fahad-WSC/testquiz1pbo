import java.util.Scanner;
import view.DataView;
import view.MahasiswaDataView;
import view.MataKuliahDataView;
import view.RuanganDataView;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
//tahap 4 dan 5
    public static void main(String[] args) {
        MahasiswaDataView mahasiswaView = new MahasiswaDataView();
        MataKuliahDataView mataKuliahView = new MataKuliahDataView();
        RuanganDataView ruanganView = new RuanganDataView();

        boolean running = true;
        while (running) {
            System.out.println("\n=== PILIH MENU ===");
            System.out.println("1. Mahasiswa");
            System.out.println("2. Mata Kuliah");
            System.out.println("3. Ruangan");
            System.out.println("0. Keluar");
            System.out.print("Pilihan: ");

            String menuInput = scanner.nextLine().trim();
            if (menuInput.equals("1")) {
                handleSubMenu(mahasiswaView, "Mahasiswa");
            } else if (menuInput.equals("2")) {
                handleSubMenu(mataKuliahView, "Mata Kuliah");
            } else if (menuInput.equals("3")) {
                handleSubMenu(ruanganView, "Ruangan");
            } else if (menuInput.equals("0")) {
                running = false;
                System.out.println("Program selesai.");
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static <T> void handleSubMenu(DataView<T> view, String entityName) {
        boolean backToMain = false;
        while (!backToMain) {
            System.out.println("\n=== PILIH AKSI (" + entityName + ") ===");
            System.out.println("1. Tampilkan semua data");
            System.out.println("2. Cari satu data");
            System.out.println("3. Tambah data");
            System.out.println("4. Ubah data");
            System.out.println("5. Delete data");
            System.out.println("0. Kembali ke Menu Utama");
            System.out.print("Pilihan: ");

            String actionInput = scanner.nextLine().trim();
            if (actionInput.equals("1")) {
                view.printAll();
            } else if (actionInput.equals("2")) {
                System.out.print("Masukkan primary key yang dicari: ");
                String pk = scanner.nextLine().trim();
                view.printByPk(pk);
            } else if (actionInput.equals("3")) {
                view.showInsertForm();
            } else if (actionInput.equals("4")) {
                view.showUpdateForm();
            } else if (actionInput.equals("5")) {
                view.promptDelete();
            } else if (actionInput.equals("0")) {
                backToMain = true;
            } else {
                System.out.println("Pilihan aksi tidak valid.");
            }
        }
    }
}