package src.models;

public abstract class Product {
    private String id;
    private String nama;
    private double harga;
    private int stok;
    private int amount;

    public Product(String id, String nama, double harga, int stok, int amount) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    public int getAmount() {
        return amount;
    }

    public abstract String getProductDetail();
    public abstract double calculatedSubTotal(int amount);
}
