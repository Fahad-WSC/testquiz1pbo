package latihandosen.model;
//(Subclass + Interface Kustomisasi)
public class KopiPesanan extends ItemPesanan {
    private int jumlahGula;
    private int jumlahEs;

    public KopiPesanan(String id, String nama, double hargaDasar, int stok) {
        super(id, nama, hargaDasar, stok);
        this.jumlahGula = 1;
        this.jumlahEs = 1;
    }

    public void aturKustomisasi(int gula, int es) {
        this.jumlahGula = gula;
        this.jumlahEs = es;
    }

    @Override
    public String getRingkasanStruk(int jumlah, double totalBayar) {
        return "Item: " + getNama() + " (" + getId() + ")"
            + " | Jumlah: " + jumlah
            + " | Gula: " + jumlahGula + ", Es: " + jumlahEs
            + " | Total: Rp" + totalBayar;
    }
}