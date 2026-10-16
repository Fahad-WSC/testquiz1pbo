import java.util.List;

public abstract class MenuItem implements Customizable {
    private String id;
    private String nama;
    private double hargaBase;
    private int stok;

    public MenuItem(String id, String nama, double hargaBase, int stok) {
        this.id = id;
        this.nama = nama;
        this.hargaBase = hargaBase;
        this.stok = stok;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public double getHargaBase() {
        return hargaBase;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public abstract double hitungHargaTotal();
    public abstract String getOrderDetail();

    // Overloading Method Pemesanan / Hitung Harga
    public double hitungHargaTotal(int jumlah) {
        return hitungHargaTotal() * jumlah;
    }

    public double hitungHargaTotal(int jumlah, String kodePromo) {
        double total = hitungHargaTotal(jumlah);
        if ("PROMO10".equalsIgnoreCase(kodePromo)) {
            total *= 0.90; // Diskon 10%
        } else if ("PROMO20".equalsIgnoreCase(kodePromo)) {
            total *= 0.80; // Diskon 20%
        }
        return total;
    }

    public double hitungHargaTotal(int jumlah, EnumUkuran ukuran) {
        return hitungHargaTotal(jumlah) * ukuran.getMultiplier();
    }
}