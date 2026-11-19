package manajemenproyek.model;

public class SkorUrgent implements StrategiSkor {
    @Override
    public String getNamaStrategi() {
        return "Skor Prioritas Tinggi (1.5x Story Point)";
    }

    @Override
    public boolean berlakuUntuk(ItemPekerjaan item) {
        return item != null && item.getPrioritas() == PrioritasTugas.TINGGI;
    }

    @Override
    public double hitungSkorAkhir(ItemPekerjaan item) {
        return item.getStoryPoint() * 1.5;
    }
}