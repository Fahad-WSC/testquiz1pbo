package belanjaleveltinggi.model;

public class BarangToko extends BarangBelanja {
    private boolean butuhKantongKhusus;

    public BarangToko(String kode, String nama, TipeBarang tipe, double hargaSatuan, boolean butuhKantongKhusus) {
        super(kode, nama, tipe, hargaSatuan);
        this.butuhKantongKhusus = butuhKantongKhusus;
    }

    public boolean isButuhKantongKhusus() {
        return butuhKantongKhusus;
    }

    @Override
    public String getInfoBarang() {
        String kantong = "Kantong biasa";
        if (butuhKantongKhusus) {
            kantong = "Kantong khusus";
        }
        return "[" + getKode() + "] " + getNama()
            + " (" + getTipe() + ") - Rp" + getHargaSatuan()
            + " | " + kantong + " | " + namaToko;
    }
}