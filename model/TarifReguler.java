package reservasiolahraga.model;
//(Concrete Strategy 1)
public class TarifReguler implements StrategiTarif {
    @Override
    public String getNamaStrategi() {
        return "Tarif Reguler (Normal)";
    }

    @Override
    public boolean bisaDipakai(double biayaAwal) {
        return biayaAwal > 0;
    }

    @Override
    public double hitungBiayaAkhir(double biayaAwal) {
        return biayaAwal;
    }
}