package src.models;
import java.util.List;
public class Kopi extends MenuItem implements Customizable {

    public Kopi(String id, String nama, double hargaBase, int stok , int rum , int es , int gula, int karamel,int jumlah, Ukuran ukuran) {
        super(id, nama, hargaBase, stok);
        this.rum = rum;
        this.es = es;
        this.gula = gula;
        this.karamel = karamel;
        this.jumlah = jumlah;
        this.ukuran = ukuran;
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
    public Ukuran getUkuran() {
        return ukuran;
    }
    public void setUkuran(Ukuran ukuran) {
        this.ukuran = ukuran;
    }
    public int getJumlah() {
        return jumlah;
    }
    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    //Gatau cara pakainya
    @Override
    void customize(List<Integer> customizations) {
    }

    @Override 
    void customize(int[] customizations) {
    
    }
    //--------


    @Override
    public double hitungHargaTotal(int jumlah) {
        double totalHarga = getHargaBase() * jumlah;
        totalHarga += rum * 3000;
        totalHarga += karamel * 3000;
        return totalHarga;
    }

    public double hitungHargaTotal(int jumlah , String kodePromo) {
        double totalHarga = getHargaBase() * jumlah * kodePromo.getDiskon();
        totalHarga += rum * 3000;
        totalHarga += karamel * 3000;
        return totalHarga;
    }

    public double hitungHargaTotal(int jumlah , Ukuran ukuran) {
        double totalHarga = getHargaBase() * jumlah * ukuran.getHargaTambahan();
        totalHarga += rum * 3000;
        totalHarga += karamel * 3000;
        return totalHarga;
    }


    @Override 
    public String getOrderDetail() {
        return String.format("Kopi [%s] (ID: %s) - Harga: Rp%.0f | Stok: %d | Rum: %d | Es: %d | Gula: %d | Karamel: %d | Ukuran: %s",
                getNama(), getId(), hitungHargaTotal(), getStok(), rum, es, gula, karamel, ukuran.getNama());
    }
   
}