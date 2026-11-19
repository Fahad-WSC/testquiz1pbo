package manajemenproyek.model;

public class TugasPengembangan extends ItemPekerjaan implements BisaDilaporkan {
    private String penanggungJawab;
    private boolean sudahSelesai;

    public TugasPengembangan(String kode, String judul, PrioritasTugas prioritas, int storyPoint, String penanggungJawab) {
        super(kode, judul, prioritas, storyPoint);
        this.penanggungJawab = penanggungJawab;
        this.sudahSelesai = false;
    }

    public String getPenanggungJawab() {
        return penanggungJawab;
    }

    public boolean isSudahSelesai() {
        return sudahSelesai;
    }

    public void tandaiSelesai() {
        this.sudahSelesai = true;
    }

    @Override
    public String getDetailItem() {
        String status = "Belum Selesai";
        if (sudahSelesai) {
            status = "Selesai";
        }
        return "[" + getKode() + "] " + getJudul()
            + " | Prioritas: " + getPrioritas()
            + " | SP: " + getStoryPoint()
            + " | PIC: " + penanggungJawab
            + " | Status: " + status
            + " | Tim: " + namaTim;
    }

    @Override
    public String buatRingkasanStatus() {
        cetakHeaderLaporan(getKode());
        return getDetailItem() + " | Estimasi: " + hitungEstimasiJam(2) + " jam";
    }
}