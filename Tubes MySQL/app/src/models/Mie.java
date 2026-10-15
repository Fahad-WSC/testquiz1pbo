package pbo.models;

public class Mie extends Product implements Discountable {
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