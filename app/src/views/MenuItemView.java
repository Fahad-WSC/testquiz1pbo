package pbo.views;

import pbo.controllers.MenuItemController;
import pbo.models.MenuItem;
import pbo.exceptions.*;
import pbo.util.InputUtil;
import java.util.List;

public class MenuItemView {
    private MenuItemController controller;

    public MenuItemView(MenuItemController controller) {
        this.controller = controller;
    }

    public void showMenu() {
        boolean running = true;
        while (running) {
            System.out.println("1. Tambah Pesanan");
            System.out.println("2. Kustomisasi Pesanan");
            System.out.println("3. Lihat Daftar Pesanan");
            System.out.println("4. Keluar");
            int choice = InputUtil.inputInt("Pilih menu (1-4): ");

            switch (choice) {
                case 1:
                    renderAddMenuItem();
                    break;
                case 2:
                    renderKustomisasiMenuItem(controller.getAllMenuItems());
                    break;
                case 3:
                    renderMenuItemList(controller.getAllMenuItems());
                    break;
                case 4:
                    running = false;
                    System.out.println("Keluar dari menu");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private void renderAddMenuItem() {
        System.out.println("Pilih Kategori Menu:");
        System.out.println("1. Kopi");
        System.out.println("2. Teh");
        System.out.println("3. Roti");
        int catChoice = InputUtil.inputInt("Pilih kategori (1-3): ");

        String id = InputUtil.inputString("Masukkan ID: ");
        String nama = InputUtil.inputString("Masukkan Nama: ");
        double hargaBase = InputUtil.inputDouble("Masukkan Harga (Rp): ");
        int stok = InputUtil.inputInt("Masukkan Stok: ");

        try {
            switch (catChoice) {
                case 1:
                    int rum = InputUtil.inputInt("Masukkan Rasa: ");
                    int es = InputUtil.inputInt("Masukkan Jumlah Es: ");
                    int gula = InputUtil.inputInt("Masukkan Jumlah Gula: ");
                    int karamel = InputUtil.inputInt("Masukkan Jumlah Karamel: ");

                    int jumlah = InputUtil.inputInt("Masukkan Jumlah: ");
                    Ukuran ukuran = InputUtil.inputUkuran("Masukkan Ukuran: ");

                    controller.addKopi(id, nama, hargaBase, stok, rum, es, gula, karamel, jumlah, ukuran);
                    System.out.println("Menu Kopi berhasil ditambahkan!");
                    break;
                case 2:
                    int boba = InputUtil.inputInt("Masukkan Jumlah Boba: ");
                    int es = InputUtil.inputInt("Masukkan Jumlah Es: ");
                    int gula = InputUtil.inputInt("Masukkan Jumlah Gula: ");
                    int karamel = InputUtil.inputInt("Masukkan Jumlah Karamel: ");

                    int jumlah = InputUtil.inputInt("Masukkan Jumlah: ");
                    Ukuran ukuran = InputUtil.inputUkuran("Masukkan Ukuran: ");

                    controller.addTeh(id, nama, hargaBase, stok, boba, es, gula, karamel, jumlah, ukuran);
                    System.out.println("Teh berhasil ditambahkan!");
                    break;
                case 3:
                    int gula = InputUtil.inputInt("Masukkan Jumlah Gula: ");
                    int meses = InputUtil.inputInt("Masukkan Jumlah Meses: ");
                    int karamel = InputUtil.inputInt("Masukkan Jumlah Karamel: ");

                    int jumlah = InputUtil.inputInt("Masukkan Jumlah: ");
                    Ukuran ukuran = InputUtil.inputUkuran("Masukkan Ukuran: ");

                    controller.addRoti(id, nama, hargaBase, stok, gula, meses, karamel, jumlah, ukuran);
                    System.out.println("Roti berhasil ditambahkan");
                    break;
                default:
                    System.out.println("Kategori tidak valid");
            }
        } catch (MenuTidakDitemukanException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    private void renderKustomisasiMenuItem(List<MenuItem> menuItems) {
        int choice = InputUtil.inputInt("Pilih pesanan untuk dikustomisasi: ");

        if (menuItems.isEmpty()) {
            System.out.println("Belum ada menu yang terdaftar");
            return;
        }

        if (choice < 1 || choice > menuItems.size()) {
            System.out.println("Pilihan tidak valid!");
            return;
        }


        MenuItem selectedItem = menuItems.get(choice - 1);
        System.out.println("Kustomisasi untuk: " + selectedItem);

    }

    public void renderMenuItemList(List<MenuItem> menuItems) {
        if (menuItems.isEmpty()) {
            System.out.println("Belum ada menu yang terdaftar.");
        } else {
            for (MenuItem item : menuItems) {
                System.out.println("• " + item.getOrderDetail());
            }
        }
    }
}