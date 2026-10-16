import java.util.List;

public class Roti extends MenuItem {
    private int gula;   // 0-5 (free)
    private int meses;  // 0-5 (+500)
    private int karamel;// 0-5 (+3000)

    public Roti(String id, String nama, double hargaBase, int stok) {
        super(id, nama, hargaBase, stok);
    }

    @Override
    public void customize(List<Integer> customizations) {
        if (customizations != null && customizations.size() >= 3) {
            this.gula = Math.min(Math.max(customizations.get(0), 0), 5);
            this.meses = Math.min(Math.max(customizations.get(1), 0), 5);
            this.karamel = Math.min(Math.max(customizations.get(2), 0), 5);
        }
    }

    @Override
    public void customize(int[] customizations) {
        if (customizations != null && customizations.length >= 3) {
            this.gula = Math.min(Math.max(customizations[0], 0), 5);
            this.meses = Math.min(Math.max(customizations[1], 0), 5);
            this.karamel = Math.min(Math.max(customizations[2], 0), 5);
        }
    }

    @Override
    public double hitungHargaTotal() {
        double tambahan = (meses * 500.0) + (karamel * 3000.0);
        return getHargaBase() + tambahan;
    }

    @Override
    public String getOrderDetail() {
        return getNama() + " [Roti] - Gula: " + gula + ", Meses: " + meses + ", Karamel: " + karamel;
    }
}