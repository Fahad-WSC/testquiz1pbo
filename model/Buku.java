package aplikasiakademik.model;
//(Inheritance, Constructor Overloading, Method Overloading & Interface)
public class Buku extends ItemAkademik implements BisaDipinjam {
    private String penulis;
    private int jumlahHalaman;

    public Buku(String kode, String judul, String penulis) {
        super(kode, judul, KategoriItem.BUKU);
        this.penulis = penulis;
        this.jumlahHalaman = 100;
    }

    public Buku(String kode, String judul, String penulis, int jumlahHalaman) {
        super(kode, judul, KategoriItem.BUKU);
        this.penulis = penulis;
        this.jumlahHalaman = jumlahHalaman;
    }

    public String getPenulis() {
        return penulis;
    }

    public int getJumlahHalaman() {
        return jumlahHalaman;
    }

    public String infoSingkat() {
        return infoSingkat(false);
    }

    public String infoSingkat(boolean sertakanKampus) {
        String hasil = "[" + getKode() + "] " + getJudul() + " - " + penulis + " (" + jumlahHalaman + " hlm)";
        if (sertakanKampus) {
            hasil = hasil + " | Kampus: " + namaKampus;
        }
        return hasil;
    }

    @Override
    public String getRingkasanDetail() {
        return infoSingkat(true);
    }

    @Override
    public void pinjam(int hari) {
        tampilkanStatusPinjam("Buku", hari);
    }
}