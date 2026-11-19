package manajemenproyek.model;

public interface StrategiSkor {
    String getNamaStrategi();

    boolean berlakuUntuk(ItemPekerjaan item);

    double hitungSkorAkhir(ItemPekerjaan item);
}