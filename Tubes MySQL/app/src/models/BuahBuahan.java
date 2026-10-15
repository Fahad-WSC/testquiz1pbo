package pbo.models;

public class BuahBuahan extends Product implements Discountable, Expireable {
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