package pbo.views;

import pbo.controllers.MarketController;
import pbo.models.Product;
import pbo.models.MarketException;
import pbo.models.MarketContainer;
import pbo.util.InputUtil;
import java.util.List;

public class MarketView {
    private MarketController controller;

    public MarketView(MarketController controller) {
        this.controller = controller;
    }

    public void showMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== MENU UTAMA MINI MARKET (MVC + Exception + Generics) ===");
            System.out.println("1. Tambah Produk");
            System.out.println("2. Print Semua Produk");
            System.out.println("3. Keluar");
            int choice = InputUtil.inputInt("Pilih menu (1-3): ");

            switch (choice) {
                case 1:
                    renderAddProductMenu();
                    break;
                case 2:
                    renderProductList(controller.getAllProducts());
                    break;
                case 3:
                    running = false;
                    System.out.println("Terima kasih telah menggunakan program Mini Market.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private void renderAddProductMenu() {
        System.out.println("\nPilih Kategori Produk:");
        System.out.println("1. Mie");
        System.out.println("2. Buah-buahan");
        System.out.println("3. Frozen Food");
        int catChoice = InputUtil.inputInt("Pilih kategori (1-3): ");

        String id = InputUtil.inputString("Masukkan ID: ");
        String nama = InputUtil.inputString("Masukkan Nama: ");
        double harga = InputUtil.inputDouble("Masukkan Harga (Rp): ");
        int stok = InputUtil.inputInt("Masukkan Stok: ");

        try {
            switch (catChoice) {
                case 1:
                    String rasa = InputUtil.inputString("Masukkan Rasa: ");
                    double beratMie = InputUtil.inputDouble("Masukkan Berat (gram): ");
                    controller.addMie(id, nama, harga, stok, rasa, beratMie);
                    System.out.println("-> Produk Mie berhasil ditambahkan!");
                    break;
                case 2:
                    String jenis = InputUtil.inputString("Masukkan Jenis (Lokal / Import): ");
                    double beratBuah = InputUtil.inputDouble("Masukkan Berat (kg): ");
                    String kadaluarsaBuah = InputUtil.inputString("Masukkan Tanggal Kadaluarsa (YYYY-MM-DD): ");
                    controller.addBuahBuahan(id, nama, harga, stok, jenis, beratBuah, kadaluarsaBuah);
                    System.out.println("-> Produk Buah-buahan berhasil ditambahkan!");
                    break;
                case 3:
                    double suhu = InputUtil.inputDouble("Masukkan Suhu Penyimpanan (°C): ");
                    String kadaluarsaFrozen = InputUtil.inputString("Masukkan Tanggal Kadaluarsa (YYYY-MM-DD): ");
                    controller.addFrozenFood(id, nama, harga, stok, suhu, kadaluarsaFrozen);
                    System.out.println("-> Produk Frozen Food berhasil ditambahkan!");
                    break;
                default:
                    System.out.println("Kategori tidak valid!");
            }
        } catch (MarketException e) {
            System.out.println("[ERROR] Terjadi kesalahan validasi: " + e.getMessage());
        }
    }

    public void renderProductList(List<Product> products) {
        System.out.println("\n=== DAFTAR SEMUA PRODUK ===");
        if (products.isEmpty()) {
            System.out.println("Belum ada produk yang terdaftar.");
        } else {
            // Menggunakan Generic Wildcard untuk menampilkan detail produk
            for (Product p : products) {
                System.out.println("• " + p.getProductDetail());
            }
        }
    }
}