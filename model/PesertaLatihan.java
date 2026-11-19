package reservasiolahraga.model;
//(Colleague pada Mediator)
public class PesertaLatihan {
    private String nama;
    private PapanPengumuman papanPengumuman;

    public PesertaLatihan(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setPapanPengumuman(PapanPengumuman papanPengumuman) {
        this.papanPengumuman = papanPengumuman;
    }

    public void kirimPengumuman(String pesan) {
        if (papanPengumuman != null) {
            papanPengumuman.siarkanInfo(this, pesan);
        }
    }

    public void terimaInfo(String pengirim, String pesan) {
        System.out.println("[" + nama + "] mendapat info dari " + pengirim + ": " + pesan);
    }
}