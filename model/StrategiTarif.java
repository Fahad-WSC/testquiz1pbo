package reservasiolahraga.model;
//(Strategy Pattern)
public interface StrategiTarif {
    String getNamaStrategi();

    boolean bisaDipakai(double biayaAwal);

    double hitungBiayaAkhir(double biayaAwal);
}