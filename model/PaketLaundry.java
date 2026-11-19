package laundrysimpel.model;

public abstract class PaketLaundry {
    private String kode;
    private String namaPelanggan;
    private JenisLayanan layanan;

    public PaketLaundry(String kode, String namaPelanggan, JenisLayanan layanan) {
        this.kode = kode;
        this.namaPelanggan = namaPelanggan;
        this.layanan = layanan;
    }

    public String getKode() {
        return kode;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public JenisLayanan getLayanan() {
        return layanan;
    }

    public abstract double hitungBiaya();

    public abstract String getInfo();
}