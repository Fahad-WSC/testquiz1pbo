import java.util.List;

public class Roti extends MenuItem implements Customizable {
    private int gula;
    private int meses;
    private int karamel;
    private int jumlah;
    private Ukuran ukuran;
    
    public Roti(String id, String nama, double harga, int stok ,int gula, int meses, int karamel , int jumlah, Ukuran ukuran ) {
        super(id, nama, harga, stok);
        this.gula = gula;
        this.meses = meses;
        this.karamel = karamel;
        this.jumlah = jumlah;
        this.ukuran = ukuran;
    }

    public int getMeses() {
        return meses;
    }
    public void setMeses(int meses) {
        this.meses = meses;
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
        totalHarga += meses * 500;
        totalHarga += karamel * 3000;
        return totalHarga;
    }
    public double hitungHargaTotal(int jumlah , String kodePromo) {
        double totalHarga = getHargaBase() * jumlah;
        totalHarga += meses * 500;
        totalHarga += karamel * 3000;
        return totalHarga;
    }
    public double hitungHargaTotal(int jumlah , Ukuran ukuran) {
        double totalHarga = getHargaBase() * jumlah;
        totalHarga += meses * 500;
        totalHarga += karamel * 3000;
        return totalHarga;
    }


    @Override 
    public String getOrderDetail() {
        return String.format("Roti [%s] (ID: %s) - Harga: Rp%.0f | Stok: %d | Meses: %d | Gula: %d | Karamel: %d | Ukuran: %s",
                getNama(), getId(), hitungHargaTotal(), getStok(), meses, gula, karamel, ukuran.getNama());
    }
}
