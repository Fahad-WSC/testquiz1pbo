package reservasiolahraga.model;
//(Abstract Class + Static + Overloading)
public abstract class FasilitasOlahraga {
    public static final int MAKSIMAL_JAM_SEWA = 5;
    public static String namaGor = "GOR Utama";

    private String kode;
    private String nama;
    private TipeLapangan tipe;
    private int slotTersedia;

    public FasilitasOlahraga(String kode, String nama, TipeLapangan tipe, int slotTersedia) {
        this.kode = kode;
        this.nama = nama;
        this.tipe = tipe;
        this.slotTersedia = slotTersedia;
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public TipeLapangan getTipe() {
        return tipe;
    }

    public int getSlotTersedia() {
        return slotTersedia;
    }

    public void pakaiSlot(int jumlahJam) {
        this.slotTersedia = this.slotTersedia - jumlahJam;
    }

    public double hitungBiayaDasar(int jam) {
        return 50000.0 * jam;
    }

    public double hitungBiayaDasar(int jam, boolean sewaRaketAtauBola) {
        double biaya = 50000.0 * jam;
        if (sewaRaketAtauBola) {
            biaya = biaya + (15000.0 * jam);
        }
        return biaya;
    }

    public abstract String getDeskripsiFasilitas();
}