package reservasiolahraga.model;
//(Mediator Pattern + Filter + Log)
import java.util.ArrayList;
import java.util.List;

public class PapanPengumuman {
    private String namaKomunitas;
    private List<PesertaLatihan> daftarPeserta;
    private List<String> daftarLog;

    public PapanPengumuman(String namaKomunitas) {
        this.namaKomunitas = namaKomunitas;
        this.daftarPeserta = new ArrayList<>();
        this.daftarLog = new ArrayList<>();
    }

    public void gabungPeserta(PesertaLatihan peserta) {
        daftarPeserta.add(peserta);
        peserta.setPapanPengumuman(this);
    }

    public void siarkanInfo(PesertaLatihan pengirim, String pesan) {
        String catatan = "[" + namaKomunitas + "] " + pengirim.getNama() + ": " + pesan;
        daftarLog.add(catatan);

        for (int i = 0; i < daftarPeserta.size(); i++) {
            PesertaLatihan penerima = daftarPeserta.get(i);
            if (penerima != pengirim) {
                penerima.terimaInfo(pengirim.getNama(), pesan);
            }
        }
    }

    public void tampilkanSemuaLog() {
        System.out.println("\n--- LOG PENGUMUMAN (" + namaKomunitas + ") ---");
        if (daftarLog.isEmpty()) {
            System.out.println("Belum ada pengumuman.");
            return;
        }
        for (int i = 0; i < daftarLog.size(); i++) {
            System.out.println((i + 1) + ". " + daftarLog.get(i));
        }
    }
}