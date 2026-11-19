package aplikasiakademik.model;
//(Interface dengan default & private method serta konstanta)
public interface BisaDipinjam {
    int DENDA_PER_HARI = 2000;

    private String formatAturan() {
        return "Maksimal " + ItemAkademik.BATAS_PINJAM_HARI + " hari, denda Rp" + DENDA_PER_HARI + "/hari";
    }

    default void tampilkanStatusPinjam(String jenis, int hari) {
        System.out.println(jenis + " dipinjam selama " + hari + " hari (" + formatAturan() + ").");
    }

    void pinjam(int hari);
}