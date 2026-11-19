package manajemenproyek.model;

public class SkorStandar implements StrategiSkor {
    @Override
    public String getNamaStrategi() {
        return "Skor Standar (1x Story Point)";
    }

    @Override
    public boolean berlakuUntuk(ItemPekerjaan item) {
        return item != null;
    }

    @Override
    public double hitungSkorAkhir(ItemPekerjaan item) {
        return item.getStoryPoint() * 1.0;
    }
}