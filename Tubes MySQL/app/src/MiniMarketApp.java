import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 1. Interface Discountable
interface Discountable {
    double calculateDiscount(int amount);
}

// 2. Interface Expireable
interface Expireable {
    String getExpiry();
}

// 3. Enum KategoriProduk
enum KategoriProduk {
    MIE,
    BUAH_BUAHAN,
    FROZEN_FOOD
}

// 4. Abstract Class Product
abstract class Product {
    private String id;
    private String nama;
    private double harga;
    private int stok;

    public Product(String id, String nama, double harga, int stok) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    // Encapsulation: Getter & Setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }
    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }

    // Abstract Methods
    public abstract String getProductDetail();
    public abstract double calculateSubTotal(int amount);
}

// 5. Class Mie
class Mie extends Product implements Discountable {
    private String rasa;
    private double berat;

    public Mie(String id, String nama, double harga, int stok, String rasa, double berat) {
        super(id, nama, harga, stok);
        this.rasa = rasa;
        this.berat = berat;
    }

    public String getRasa() { return rasa; }
    public void setRasa(String rasa) { this.rasa = rasa; }
    public double getBerat() { return berat; }
    public void setBerat(double berat) { this.berat = berat; }

    @Override
    public double calculateDiscount(int amount) {
        double discountPercent = 0.0;
        if (amount >= 20) {
            discountPercent = 0.10;
        } else if (amount >= 10) {
            discountPercent = 0.05;
        } else {
            discountPercent = 0.0;
        }
        return getHarga() * amount * discountPercent;
    }

    @Override
    public double calculateSubTotal(int amount) {
        return (getHarga() * amount) - calculateDiscount(amount);
    }

    @Override
    public String getProductDetail() {
        return String.format("Mie [%s] (ID: %s) - Harga: Rp%.0f | Stok: %d | Rasa: %s | Berat: %.1fg",
                getNama(), getId(), getHarga(), getStok(), rasa, berat);
    }
}

// 6. Class BuahBuahan
class BuahBuahan extends Product implements Discountable, Expireable {
    private String jenis;
    private double berat;
    private String tanggalKadaluarsa;

    public BuahBuahan(String id, String nama, double harga, int stok, String jenis, double berat, String tanggalKadaluarsa) {
        super(id, nama, harga, stok);
        this.jenis = jenis;
        this.berat = berat;
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }

    public String getJenis() { return jenis; }
    public void setJenis(String jenis) { this.jenis = jenis; }
    public double getBerat() { return berat; }
    public void setBerat(double berat) { this.berat = berat; }
    public String getTanggalKadaluarsa() { return tanggalKadaluarsa; }
    public void setTanggalKadaluarsa(String tanggalKadaluarsa) { this.tanggalKadaluarsa = tanggalKadaluarsa; }

    @Override
    public String getExpiry() {
        return tanggalKadaluarsa;
    }

    @Override
    public double calculateDiscount(int amount) {
        double discountPercent = 0.0;
        if (amount >= 15) {
            discountPercent = 0.05;
        } else if (amount >= 10) {
            discountPercent = 0.03;
        } else if (amount >= 5) {
            discountPercent = 0.02;
        } else {
            discountPercent = 0.0;
        }
        return getHarga() * amount * discountPercent;
    }

    @Override
    public double calculateSubTotal(int amount) {
        return (getHarga() * amount) - calculateDiscount(amount);
    }

    @Override
    public String getProductDetail() {
        return String.format("Buah-buahan [%s] (ID: %s) - Harga: Rp%.0f | Stok: %d | Jenis: %s | Berat: %.1fkg | Kadaluarsa: %s",
                getNama(), getId(), getHarga(), getStok(), jenis, berat, tanggalKadaluarsa);
    }
}

// 7. Class FrozenFood
class FrozenFood extends Product implements Discountable, Expireable {
    private double suhuPenyimpanan;
    private String tanggalKadaluarsa;

    public FrozenFood(String id, String nama, double harga, int stok, double suhuPenyimpanan, String tanggalKadaluarsa) {
        super(id, nama, harga, stok);
        this.suhuPenyimpanan = suhuPenyimpanan;
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }

    public double getSuhuPenyimpanan() { return suhuPenyimpanan; }
    public void setSuhuPenyimpanan(double suhuPenyimpanan) { this.suhuPenyimpanan = suhuPenyimpanan; }
    public String getTanggalKadaluarsa() { return tanggalKadaluarsa; }
    public void setTanggalKadaluarsa(String tanggalKadaluarsa) { this.tanggalKadaluarsa = tanggalKadaluarsa; }

    @Override
    public String getExpiry() {
        return tanggalKadaluarsa;
    }

    @Override
    public double calculateDiscount(int amount) {
        double discountPercent = 0.0;
        if (amount >= 8) {
            discountPercent = 0.08;
        } else if (amount >= 4) {
            discountPercent = 0.04;
        } else {
            discountPercent = 0.0;
        }
        return getHarga() * amount * discountPercent;
    }

    @Override
    public double calculateSubTotal(int amount) {
        return (getHarga() * amount) - calculateDiscount(amount);
    }

    @Override
    public String getProductDetail() {
        return String.format("Frozen Food [%s] (ID: %s) - Harga: Rp%.0f | Stok: %d | Suhu: %.1f°C | Kadaluarsa: %s",
                getNama(), getId(), getHarga(), getStok(), suhuPenyimpanan, tanggalKadaluarsa);
    }
}

// 8. Static Util Class InputUtil
class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public static int inputInt(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("Input tidak valid. Masukkan angka bulat: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return value;
    }

    public static double inputDouble(String message) {
        System.out.print(message);
        while (!scanner.hasNextDouble()) {
            System.out.print("Input tidak valid. Masukkan angka: ");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        return value;
    }
}

// 9. Main Class
public class MiniMarketApp {
    public static void main(String[] args) {
        List<Product> productList = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("\n=== MENU UTAMA MINI MARKET ===");
            System.out.println("1. Tambah Produk");
            System.out.println("2. Print Semua Produk");
            System.out.println("3. Keluar");
            int choice = InputUtil.inputInt("Pilih menu (1-3): ");

            switch (choice) {
                case 1:
                    System.out.println("\nPilih Kategori Produk:");
                    System.out.println("1. Mie");
                    System.out.println("2. Buah-buahan");
                    System.out.println("3. Frozen Food");
                    int catChoice = InputUtil.inputInt("Pilih kategori (1-3): ");

                    String id = InputUtil.inputString("Masukkan ID: ");
                    String nama = InputUtil.inputString("Masukkan Nama: ");
                    double harga = InputUtil.inputDouble("Masukkan Harga (Rp): ");
                    int stok = InputUtil.inputInt("Masukkan Stok: ");

                    switch (catChoice) {
                        case 1:
                            String rasa = InputUtil.inputString("Masukkan Rasa: ");
                            double beratMie = InputUtil.inputDouble("Masukkan Berat (gram): ");
                            Product mie = new Mie(id, nama, harga, stok, rasa, beratMie);
                            productList.add(mie);
                            System.out.println("-> Produk Mie berhasil ditambahkan!");
                            break;
                        case 2:
                            String jenis = InputUtil.inputString("Masukkan Jenis (Lokal / Import): ");
                            double beratBuah = InputUtil.inputDouble("Masukkan Berat (kg): ");
                            String kadaluarsaBuah = InputUtil.inputString("Masukkan Tanggal Kadaluarsa (YYYY-MM-DD): ");
                            Product buah = new BuahBuahan(id, nama, harga, stok, jenis, beratBuah, kadaluarsaBuah);
                            productList.add(buah);
                            System.out.println("-> Produk Buah-buahan berhasil ditambahkan!");
                            break;
                        case 3:
                            double suhu = InputUtil.inputDouble("Masukkan Suhu Penyimpanan (°C): ");
                            String kadaluarsaFrozen = InputUtil.inputString("Masukkan Tanggal Kadaluarsa (YYYY-MM-DD): ");
                            Product frozen = new FrozenFood(id, nama, harga, stok, suhu, kadaluarsaFrozen);
                            productList.add(frozen);
                            System.out.println("-> Produk Frozen Food berhasil ditambahkan!");
                            break;
                        default:
                            System.out.println("Kategori tidak valid!");
                    }
                    break;

                case 2:
                    System.out.println("\n=== DAFTAR SEMUA PRODUK ===");
                    if (productList.isEmpty()) {
                        System.out.println("Belum ada produk yang terdaftar.");
                    } else {
                        for (Product p : productList) {
                            System.out.println("• " + p.getProductDetail());
                        }
                    }
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
}