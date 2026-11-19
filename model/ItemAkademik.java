package aplikasiakademik.model;
//(Abstract Class, Encapsulation, Overloading, Interface & Polymorphism)
public abstract class ItemAkademik {
    public static final int BATAS_PINJAM_HARI = 7;
    public static String namaKampus = "ITHB";

    private String kode;
    private String judul;
    private KategoriItem kategori;

    public ItemAkademik(String kode, String judul, KategoriItem kategori) {
        this.kode = kode;
        this.judul = judul;
        this.kategori = kategori;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public KategoriItem getKategori() {
        return kategori;
    }

    public void setKategori(KategoriItem kategori) {
        this.kategori = kategori;
    }

    public abstract String getRingkasanDetail();
}