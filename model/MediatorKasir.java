package belanjaleveltinggi.model;

import java.util.ArrayList;
import java.util.List;

public class MediatorKasir {
    private String namaShift;
    private List<PetugasKasir> daftarPetugas;
    private List<String> logTransaksi;

    public MediatorKasir(String namaShift) {
        this.namaShift = namaShift;
        this.daftarPetugas = new ArrayList<>();
        this.logTransaksi = new ArrayList<>();
    }

    public void tambahPetugas(PetugasKasir petugas) {
        daftarPetugas.add(petugas);
        petugas.setMediator(this);
    }

    public void kirimInfo(PetugasKasir pengirim, String pesan) {
        String catatan = "[" + namaShift + "] " + pengirim.getNama() + ": " + pesan;
        logTransaksi.add(catatan);

        for (int i = 0; i < daftarPetugas.size(); i++) {
            PetugasKasir penerima = daftarPetugas.get(i);
            if (penerima != pengirim) {
                penerima.terimaInfo(pengirim.getNama(), pesan);
            }
        }
    }

    public void cetakLog() {
        System.out.println("\n--- LOG KASIR (" + namaShift + ") ---");
        if (logTransaksi.isEmpty()) {
            System.out.println("Belum ada catatan transaksi.");
            return;
        }
        for (int i = 0; i < logTransaksi.size(); i++) {
            System.out.println((i + 1) + ". " + logTransaksi.get(i));
        }
    }
}