package aplikasiakademik.model;
//(Inheritance & Interface)
public class ModulPraktikum extends ItemAkademik implements BisaDipinjam {
    private String mataKuliah;
    private int pertemuanKe;

    public ModulPraktikum(String kode, String judul, String mataKuliah, int pertemuanKe) {
        super(kode, judul, KategoriItem.MODUL);
        this.mataKuliah = mataKuliah;
        this.pertemuanKe = pertemuanKe;
    }

    public String getMataKuliah() {
        return mataKuliah;
    }

    public int getPertemuanKe() {
        return pertemuanKe;
    }

    @Override
    public String getRingkasanDetail() {
        return "[" + getKode() + "] " + getJudul() + " | MK: " + mataKuliah + " (Pertemuan " + pertemuanKe + ")";
    }

    @Override
    public void pinjam(int hari) {
        tampilkanStatusPinjam("Modul Praktikum", hari);
    }
}