package models;

public class Mie extends Product implements Discountable {
    private Rasa rasa;
    private double berat;

    public Mie(String id, String nama, double harga, int stok, Rasa rasa, double berat) {
        super(id, nama, harga, stok);
        this.rasa = rasa;
        this.berat = berat;
    }

    public Rasa getRasa() {
        return rasa;
    }

    public double getBerat() {
        return berat;
    }

    @Override
    public double calculateDiscount(int amount) {
        double totalHarga = getHarga() * amount;

        if (amount < 10) {
            return 0;
        } else if (amount >= 10 && amount < 20) {
            return totalHarga * 0.05;
        } else {
            return totalHarga * 0.10;
        }
    }

    @Override
    public double calculateSubTotal(int amount) {
        return (getHarga() * amount) - calculateDiscount(amount);
    }

    @Override
    public String getProductDetail() {
        return "• Mie [" + getNama() + "] (ID: " + getId()
                + ") - Harga: " + getHarga() + " | Stok: " + getStok()
                + " | Rasa: " + getRasa() + " | Berat: " + getBerat();
    }
}