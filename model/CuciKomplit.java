package laundrysimpel.model;

public class CuciKomplit extends PaketLaundry implements DiskonLaundry {
    private double beratKg;
    private double tarifPerKg;

    public CuciKomplit(String kode, String namaPelanggan, JenisLayanan layanan, double beratKg) {
        super(kode, namaPelanggan, layanan);
        this.beratKg = beratKg;
        this.tarifPerKg = 8000.0;
        if (layanan == JenisLayanan.EKSPRES) {
            this.tarifPerKg = 12000.0;
        }
    }

    @Override
    public double hitungBiaya() {
        return beratKg * tarifPerKg;
    }

    @Override
    public double potongHarga(double totalAwal) {
        if (beratKg >= 5.0) {
            return totalAwal - 5000.0;
        }
        return totalAwal;
    }

    @Override
    public String getInfo() {
        double biayaAkhir = potongHarga(hitungBiaya());
        return "[" + getKode() + "] " + getNamaPelanggan()
            + " | " + getLayanan()
            + " | " + beratKg + " kg"
            + " | Total: Rp" + biayaAkhir;
    }
}