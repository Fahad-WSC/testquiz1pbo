public class Roti extends MenuItem implements Customizable {
    public Roti(String id, String nama, double harga, int stok ,int gula, int meses, int karamel) {
        super(id, nama, harga, stok);
        this.gula = gula;
        this.meses = meses;
        this.karamel = karamel;
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
}
