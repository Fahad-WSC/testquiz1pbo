package src.models;

public class Kopi extends MenuItem implements Customizable {

    public Kopi(String id, String nama, double hargaBase, int stok , int rum , int es , int gula, int karamel) {
        super(id, nama, hargaBase, stok);
        this.rum = rum;
        this.es = es;
        this.gula = gula;
        this.karamel = karamel; 
    }

    public int getRum() {
        return rum;
    }
    public void setRum(int rum) {
        this.rum = rum;
    }
    public int getEs() {
        return es;
    }
    public void setEs(int es) {
        this.es = es;
    }
    public int getGula() {
        return gula;
    }
    public void setGula(int gula) {
        this.gula = gula;
    }
    public int getKaramel() {
        return karamel;
    }
    public void setKaramel(int karamel) {
        this.karamel = karamel;
    }

    @Override
    void customize(List<Integer> customizations) {
    
    }

    @Override 
    void customize(int[] customizations) {
    
    }

    @Override
    public double hitungHargaTotal() {
        double totalHarga = getHargaBase();
        totalHarga += rum * 3000; 
        totalHarga += karamel * 3000; 
        return totalHarga;
    }

    @Override 
    public String getOrderDetail() {
        return String.format("Kopi [%s] (ID: %s) - Harga: Rp%.0f | Stok: %d | Rum: %d | Es: %d | Gula: %d | Karamel: %d",
                getNama(), getId(), hitungHargaTotal(), getStok(), rum, es, gula, karamel);
    }
   
}