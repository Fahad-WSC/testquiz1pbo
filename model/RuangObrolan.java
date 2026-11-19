package latihandosen.model;
//(Mediator Pattern + Filter Badword + Mute + Riwayat Chat)
import java.util.ArrayList;
import java.util.List;

public class RuangObrolan {
    private String namaRuang;
    private List<AnggotaChat> anggotaList;
    private List<String> kataTerlarang;
    private List<String> daftarMuted;
    private List<String> riwayatPesan;

    public RuangObrolan(String namaRuang) {
        this.namaRuang = namaRuang;
        this.anggotaList = new ArrayList<>();
        this.kataTerlarang = new ArrayList<>();
        this.daftarMuted = new ArrayList<>();
        this.riwayatPesan = new ArrayList<>();
        this.kataTerlarang.add("kasar");
        this.kataTerlarang.add("spam");
    }

    public void daftarAnggota(AnggotaChat anggota) {
        anggotaList.add(anggota);
        anggota.setRuangObrolan(this);
    }

    public void bisukanAnggota(String namaAnggota) {
        if (!daftarMuted.contains(namaAnggota)) {
            daftarMuted.add(namaAnggota);
        }
    }

    private String saringPesan(String pesan) {
        String hasil = pesan;
        for (int i = 0; i < kataTerlarang.size(); i++) {
            String kata = kataTerlarang.get(i);
            hasil = hasil.replace(kata, "***");
        }
        return hasil;
    }

    public void kirimSiaran(AnggotaChat pengirim, String pesan) {
        if (daftarMuted.contains(pengirim.getNama())) {
            System.out.println("[Mediator " + namaRuang + "] " + pengirim.getNama() + " sedang di-mute.");
            return;
        }

        String pesanBersih = saringPesan(pesan);
        String catatan = "[" + namaRuang + "] " + pengirim.getNama() + ": " + pesanBersih;
        riwayatPesan.add(catatan);

        for (int i = 0; i < anggotaList.size(); i++) {
            AnggotaChat penerima = anggotaList.get(i);
            if (penerima != pengirim) {
                penerima.terimaPesan(pengirim.getNama(), pesanBersih);
            }
        }
    }

    public void tampilkanRiwayat() {
        System.out.println("\n--- RIWAYAT CHAT (" + namaRuang + ") ---");
        if (riwayatPesan.isEmpty()) {
            System.out.println("Belum ada pesan.");
            return;
        }
        for (int i = 0; i < riwayatPesan.size(); i++) {
            System.out.println((i + 1) + ". " + riwayatPesan.get(i));
        }
    }
}