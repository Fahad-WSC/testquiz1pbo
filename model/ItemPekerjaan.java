package manajemenproyek.model;

public abstract class ItemPekerjaan {
    public static final int MAX_STORY_POINT_SPRINT = 20;
    public static String namaTim = "Tim Inti";

    private String kode;
    private String judul;
    private PrioritasTugas prioritas;
    private int storyPoint;

    public ItemPekerjaan(String kode, String judul, PrioritasTugas prioritas, int storyPoint) {
        this.kode = kode;
        this.judul = judul;
        this.prioritas = prioritas;
        this.storyPoint = storyPoint;
    }

    public String getKode() {
        return kode;
    }

    public String getJudul() {
        return judul;
    }

    public PrioritasTugas getPrioritas() {
        return prioritas;
    }

    public int getStoryPoint() {
        return storyPoint;
    }

    public int hitungEstimasiJam() {
        return storyPoint * 4;
    }

    public int hitungEstimasiJam(int jamBuffer) {
        return (storyPoint * 4) + jamBuffer;
    }

    public abstract String getDetailItem();
}