package latihandosen.model;
//(Abstract Class + Overloading)
public abstract class ItemPesanan {
    private String id;
    private String nama;
    private double hargaDasar;
    private int stok;

    public ItemPesanan(String id, String nama, double hargaDasar, int stok) {
        this.id = id;
        this.nama = nama;
        this.hargaDasar = hargaDasar;
        this.stok = stok;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public double getHargaDasar() {
        return hargaDasar;
    }

    public int getStok() {
        return stok;
    }

    public void kurangiStok(int jumlah) {
        this.stok = this.stok - jumlah;
    }

    public double hitungTotal(int jumlah) {
        return hargaDasar * jumlah;
    }

    public double hitungTotal(int jumlah, UkuranPesanan ukuran) {
        double pengali = 1.0;
        if (ukuran == UkuranPesanan.MEDIUM) {
            pengali = 1.2;
        } else if (ukuran == UkuranPesanan.LARGE) {
            pengali = 1.4;
        }
        return hargaDasar * pengali * jumlah;
    }

    public abstract String getRingkasanStruk(int jumlah, double totalBayar);
}