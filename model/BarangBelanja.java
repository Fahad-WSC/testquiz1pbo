package belanjaleveltinggi.model;

public abstract class BarangBelanja {
    public static final int MAKS_ITEM_PER_TRANSAKSI = 50;
    public static String namaToko = "Toko Pojok";

    private String kode;
    private String nama;
    private TipeBarang tipe;
    private double hargaSatuan;

    public BarangBelanja(String kode, String nama, TipeBarang tipe, double hargaSatuan) {
        this.kode = kode;
        this.nama = nama;
        this.tipe = tipe;
        this.hargaSatuan = hargaSatuan;
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public TipeBarang getTipe() {
        return tipe;
    }

    public double getHargaSatuan() {
        return hargaSatuan;
    }

    public double hitungSubtotal(int jumlah) {
        return hargaSatuan * jumlah;
    }

    public double hitungSubtotal(int jumlah, double biayaKemasan) {
        return (hargaSatuan * jumlah) + biayaKemasan;
    }

    public abstract String getInfoBarang();
}