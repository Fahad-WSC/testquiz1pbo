package reservasiolahraga.model;
//(Interface dengan default & private method serta konstanta)
public interface BisaDicekKondisi {
    int BIAYA_INSPEKSI = 10000;

    private String formatLabelInspeksi() {
        return "Biaya inspeksi standar Rp" + BIAYA_INSPEKSI;
    }

    default void cetakHasilPengecekan(String target, String kondisi) {
        System.out.println("Inspeksi " + target + ": " + kondisi + " (" + formatLabelInspeksi() + ")");
    }

    void cekKondisi();
}