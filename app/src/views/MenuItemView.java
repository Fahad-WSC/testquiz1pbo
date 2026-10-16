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
                    renderMenuItemList(controller.getAllMenuItems());
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
                    String rasa = InputUtil.inputString("Masukkan Rasa: ");
                    double beratMie = InputUtil.inputDouble("Masukkan Berat (gram): ");

                    controller.addKopi(id, nama, hargaBase, stok, rum, es, gula, karamel, ukuran);
                    System.out.println("Menu Kopi berhasil ditambahkan!");
                    break;
                case 2:
                    String jenis = InputUtil.inputString("Masukkan Jenis (Lokal / Import): ");
                    double beratBuah = InputUtil.inputDouble("Masukkan Berat (kg): ");
                    String kadaluarsaBuah = InputUtil.inputString("Masukkan Tanggal Kadaluarsa (YYYY-MM-DD): ");

                    controller.addTeh(id, nama, hargaBase, stok, jenis, beratBuah, kadaluarsaBuah);
                    System.out.println("Produk Teh berhasil ditambahkan!");
                    break;
                case 3:
                    double suhu = InputUtil.inputDouble("Masukkan Suhu Penyimpanan (°C): ");
                    String kadaluarsaFrozen = InputUtil.inputString("Masukkan Tanggal Kadaluarsa (YYYY-MM-DD): ");

                    controller.addRoti(id, nama, hargaBase, stok, suhu, kadaluarsaFrozen);
                    System.out.println("Produk Roti berhasil ditambahkan!");
                    break;
                default:
                    System.out.println("Kategori tidak valid!");
            }
        } catch (MarketException e) {
            System.out.println("[ERROR] Terjadi kesalahan validasi: " + e.getMessage());
        }
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