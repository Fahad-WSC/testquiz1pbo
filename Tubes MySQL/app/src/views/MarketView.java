package pbo.views;

import pbo.controllers.MarketController;
import pbo.models.Product;
import pbo.models.MarketException;
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
            System.out.println("\n=== MENU UTAMA MINI MARKET (SOLID + Factory Pattern) ===");
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

        String param1 = "";
        double param2 = 0.0;
        String kadaluarsa = "";

        try {
            switch (catChoice) {
                case 1:
                    param1 = InputUtil.inputString("Masukkan Rasa: ");
                    param2 = InputUtil.inputDouble("Masukkan Berat (gram): ");
                    break;
                case 2:
                    param1 = InputUtil.inputString("Masukkan Jenis (Lokal / Import): ");
                    param2 = InputUtil.inputDouble("Masukkan Berat (kg): ");
                    kadaluarsa = InputUtil.inputString("Masukkan Tanggal Kadaluarsa (YYYY-MM-DD): ");
                    break;
                case 3:
                    param2 = InputUtil.inputDouble("Masukkan Suhu Penyimpanan (°C): ");
                    kadaluarsa = InputUtil.inputString("Masukkan Tanggal Kadaluarsa (YYYY-MM-DD): ");
                    break;
                default:
                    System.out.println("Kategori tidak valid!");
                    return;
            }

            // Memanggil controller yang dibantu oleh Factory Pattern
            controller.addProduct(catChoice, id, nama, harga, stok, param1, param2, kadaluarsa);
            System.out.println("-> Produk berhasil ditambahkan via ProductFactory!");

        } catch (MarketException e) {
            System.out.println("[ERROR] Terjadi kesalahan validasi: " + e.getMessage());
        }
    }

    public void renderProductList(List<Product> products) {
        System.out.println("\n=== DAFTAR SEMUA PRODUK ===");
        if (products.isEmpty()) {
            System.out.println("Belum ada produk yang terdaftar.");
        } else {
            for (Product p : products) {
                System.out.println("• " + p.getProductDetail());
            }
        }
    }
}