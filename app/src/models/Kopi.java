import java.util.List;

public class Kopi extends MenuItem {
    private int rum;    // 0-1 (+3000)
    private int es;     // 0-5 (free)
    private int gula;   // 0-5 (free)
    private int karamel;// 0-5 (+3000)

    public Kopi(String id, String nama, double hargaBase, int stok) {
        super(id, nama, hargaBase, stok);
    }

    @Override
    public void customize(List<Integer> customizations) {
        if (customizations != null && customizations.size() >= 4) {
            this.rum = Math.min(Math.max(customizations.get(0), 0), 1);
            this.es = Math.min(Math.max(customizations.get(1), 0), 5);
            this.gula = Math.min(Math.max(customizations.get(2), 0), 5);
            this.karamel = Math.min(Math.max(customizations.get(3), 0), 5);
        }
    }

    @Override
    public void customize(int[] customizations) {
        if (customizations != null && customizations.length >= 4) {
            this.rum = Math.min(Math.max(customizations[0], 0), 1);
            this.es = Math.min(Math.max(customizations[1], 0), 5);
            this.gula = Math.min(Math.max(customizations[2], 0), 5);
            this.karamel = Math.min(Math.max(customizations[3], 0), 5);
        }
    }

    @Override
    public double hitungHargaTotal() {
        double tambahan = (rum * 3000.0) + (karamel * 3000.0);
        return getHargaBase() + tambahan;
    }

    @Override
    public String getOrderDetail() {
        return getNama() + " [Kopi] - Rum: " + rum + ", Es: " + es + ", Gula: " + gula + ", Karamel: " + karamel;
    }
}