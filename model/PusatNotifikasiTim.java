package manajemenproyek.model;

import java.util.ArrayList;
import java.util.List;

public class PusatNotifikasiTim {
    private String namaKanal;
    private List<AnggotaTim> anggotaList;
    private List<String> daftarMuted;
    private List<String> kataDiblokir;
    private List<String> logAktivitas;

    public PusatNotifikasiTim(String namaKanal) {
        this.namaKanal = namaKanal;
        this.anggotaList = new ArrayList<>();
        this.daftarMuted = new ArrayList<>();
        this.kataDiblokir = new ArrayList<>();
        this.logAktivitas = new ArrayList<>();
        this.kataDiblokir.add("spoiler");
        this.kataDiblokir.add("bocoran");
    }

    public void gabung(AnggotaTim anggota) {
        anggotaList.add(anggota);
        anggota.setPusatNotifikasi(this);
    }

    public void bisukan(String namaAnggota) {
        if (!daftarMuted.contains(namaAnggota)) {
            daftarMuted.add(namaAnggota);
        }
    }

    private String saringPesan(String pesan) {
        String hasil = pesan;
        for (int i = 0; i < kataDiblokir.size(); i++) {
            String kata = kataDiblokir.get(i);
            hasil = hasil.replace(kata, "[FILTERED]");
        }
        return hasil;
    }

    public void kirimSiaran(AnggotaTim pengirim, String pesan) {
        if (daftarMuted.contains(pengirim.getNama())) {
            System.out.println("[Kanal " + namaKanal + "] " + pengirim.getNama() + " sedang di-mute.");
            return;
        }

        String bersih = saringPesan(pesan);
        String entri = "[" + namaKanal + "] " + pengirim.getNama() + ": " + bersih;
        logAktivitas.add(entri);

        for (int i = 0; i < anggotaList.size(); i++) {
            AnggotaTim target = anggotaList.get(i);
            if (target != pengirim) {
                target.terimaPesan(pengirim.getNama(), bersih);
            }
        }
    }

    public void cetakLog() {
        System.out.println("\n--- LOG AKTIVITAS (" + namaKanal + ") ---");
        if (logAktivitas.isEmpty()) {
            System.out.println("Belum ada aktivitas.");
            return;
        }
        for (int i = 0; i < logAktivitas.size(); i++) {
            System.out.println((i + 1) + ". " + logAktivitas.get(i));
        }
    }
}