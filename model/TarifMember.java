package reservasiolahraga.model;
//(Concrete Strategy 2 - Diskon 20%)
public class TarifMember implements StrategiTarif {
    private String kodeMember;

    public TarifMember(String kodeMember) {
        this.kodeMember = kodeMember;
    }

    public String getKodeMember() {
        return kodeMember;
    }

    @Override
    public String getNamaStrategi() {
        return "Tarif Member (" + kodeMember + " - Diskon 20%)";
    }

    @Override
    public boolean bisaDipakai(double biayaAwal) {
        return biayaAwal >= 50000.0;
    }

    @Override
    public double hitungBiayaAkhir(double biayaAwal) {
        return biayaAwal * 0.8;
    }
}