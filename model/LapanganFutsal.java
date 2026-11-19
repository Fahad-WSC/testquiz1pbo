package reservasiolahraga.model;
//(Inheritance + Interface)
public class LapanganFutsal extends FasilitasOlahraga implements BisaDicekKondisi {
    private boolean pakaiLampuMalam;

    public LapanganFutsal(String kode, String nama, TipeLapangan tipe, int slotTersedia, boolean pakaiLampuMalam) {
        super(kode, nama, tipe, slotTersedia);
        this.pakaiLampuMalam = pakaiLampuMalam;
    }

    public boolean isPakaiLampuMalam() {
        return pakaiLampuMalam;
    }

    @Override
    public String getDeskripsiFasilitas() {
        String statusLampu = "Lampu standar";
        if (pakaiLampuMalam) {
            statusLampu = "Lampu malam aktif";
        }
        return "[" + getKode() + "] " + getNama() + " (" + getTipe() + ") - Slot: "
            + getSlotTersedia() + " jam | " + statusLampu + " | " + namaGor;
    }

    @Override
    public void cekKondisi() {
        cetakHasilPengecekan("Lapangan Futsal " + getNama(), "Siap dipakai");
    }
}