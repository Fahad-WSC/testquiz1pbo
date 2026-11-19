package manajemenproyek.controller;

import java.util.ArrayList;
import java.util.List;
import manajemenproyek.exception.AksesDitolakException;
import manajemenproyek.exception.KapasitasSprintPenuhException;
import manajemenproyek.model.AnggotaTim;
import manajemenproyek.model.BisaDilaporkan;
import manajemenproyek.model.ItemPekerjaan;
import manajemenproyek.model.PapanSprint;
import manajemenproyek.model.PrioritasTugas;
import manajemenproyek.model.PusatNotifikasiTim;
import manajemenproyek.model.SkorStandar;
import manajemenproyek.model.SkorUrgent;
import manajemenproyek.model.StrategiSkor;
import manajemenproyek.model.TugasPengembangan;
import manajemenproyek.view.TampilanSprint;

public class KontrolerSprint {
    private TampilanSprint view;
    private PapanSprint<ItemPekerjaan> papanSprint;
    private List<StrategiSkor> strategiList;
    private PusatNotifikasiTim kanalTim;
    private AnggotaTim anggota1;
    private AnggotaTim anggota2;

    public KontrolerSprint(TampilanSprint view) {
        this.view = view;
        this.papanSprint = new PapanSprint<>();
        this.papanSprint.tambahItem(new TugasPengembangan("TSK-1", "Setup Repository", PrioritasTugas.SEDANG, 3, "Fahad"));
        this.papanSprint.tambahItem(new TugasPengembangan("TSK-2", "Fix Bug Login", PrioritasTugas.TINGGI, 5, "Raka"));

        this.strategiList = new ArrayList<>();
        this.strategiList.add(new SkorStandar());
        this.strategiList.add(new SkorUrgent());

        this.kanalTim = new PusatNotifikasiTim("Sprint-9");
        this.anggota1 = new AnggotaTim("Fahad", "Developer");
        this.anggota2 = new AnggotaTim("Raka", "QA");
        this.kanalTim.gabung(anggota1);
        this.kanalTim.gabung(anggota2);
    }

    public void tambahTugas() {
        try {
            String kode = view.mintaTeks("Kode tugas");
            String judul = view.mintaTeks("Judul tugas");
            String prioInput = view.mintaTeks("Prioritas (1 = RENDAH, 2 = SEDANG, 3 = TINGGI)");
            int sp = view.mintaAngka("Story point");
            String pic = view.mintaTeks("Nama penanggung jawab");

            if (kode.isEmpty() || judul.isEmpty() || pic.isEmpty()) {
                throw new AksesDitolakException("Kode, judul, dan penanggung jawab wajib diisi.");
            }
            if (sp <= 0) {
                throw new KapasitasSprintPenuhException("Story point harus lebih dari 0.");
            }
            if (papanSprint.totalStoryPoint() + sp > ItemPekerjaan.MAX_STORY_POINT_SPRINT) {
                throw new KapasitasSprintPenuhException(
                    "Kapasitas sprint penuh! Maksimal " + ItemPekerjaan.MAX_STORY_POINT_SPRINT
                        + ", terpakai: " + papanSprint.totalStoryPoint()
                );
            }

            PrioritasTugas prioritas = PrioritasTugas.RENDAH;
            if (prioInput.equals("2")) {
                prioritas = PrioritasTugas.SEDANG;
            } else if (prioInput.equals("3")) {
                prioritas = PrioritasTugas.TINGGI;
            }

            TugasPengembangan tugasBaru = new TugasPengembangan(kode, judul, prioritas, sp, pic);
            papanSprint.tambahItem(tugasBaru);
            view.tampilkanPesan("Tugas berhasil ditambahkan ke sprint.");
        } catch (NumberFormatException e) {
            view.tampilkanPesan("Story point harus berupa angka bulat.");
        } catch (KapasitasSprintPenuhException e) {
            view.tampilkanPesan("Gagal tambah tugas: " + e.getMessage());
        } catch (AksesDitolakException e) {
            view.tampilkanPesan("Validasi gagal: " + e.getMessage());
        } finally {
            view.tampilkanPesan("[Proses tambah tugas selesai]");
        }
    }

    public void lihatSemuaTugas() {
        PapanSprint.tampilkanSemuaItem(papanSprint.ambilSemua());
        view.tampilkanPesan("Total Story Point: " + papanSprint.totalStoryPoint() + "/" + ItemPekerjaan.MAX_STORY_POINT_SPRINT);
    }

    public void hitungSkorTugas() {
        try {
            String kode = view.mintaTeks("Masukkan kode tugas");
            ItemPekerjaan item = papanSprint.cariByKode(kode);
            if (item == null) {
                throw new AksesDitolakException("Tugas dengan kode '" + kode + "' tidak ditemukan.");
            }

            List<StrategiSkor> tersedia = new ArrayList<>();
            for (int i = 0; i < strategiList.size(); i++) {
                StrategiSkor s = strategiList.get(i);
                if (s.berlakuUntuk(item)) {
                    tersedia.add(s);
                }
            }

            for (int i = 0; i < tersedia.size(); i++) {
                view.tampilkanPesan((i + 1) + ". " + tersedia.get(i).getNamaStrategi());
            }

            int pilih = view.mintaAngka("Pilih strategi skor") - 1;
            if (pilih < 0 || pilih >= tersedia.size()) {
                throw new AksesDitolakException("Pilihan strategi tidak valid.");
            }

            double skor = tersedia.get(pilih).hitungSkorAkhir(item);
            view.tampilkanPesan("Skor akhir untuk " + item.getJudul() + ": " + skor);
        } catch (NumberFormatException e) {
            view.tampilkanPesan("Pilihan harus berupa angka.");
        } catch (AksesDitolakException e) {
            view.tampilkanPesan("Error: " + e.getMessage());
        }
    }

    public void selesaikanDanLaporkanTugas() {
        try {
            String kode = view.mintaTeks("Masukkan kode tugas yang diselesaikan");
            ItemPekerjaan item = papanSprint.cariByKode(kode);
            if (item == null) {
                throw new AksesDitolakException("Tugas tidak ditemukan.");
            }

            if (item instanceof TugasPengembangan) {
                TugasPengembangan tugas = (TugasPengembangan) item;
                tugas.tandaiSelesai();
                view.tampilkanPesan(tugas.buatRingkasanStatus());
            }

            // Anonymous Inner Class untuk catatan audit sekali pakai
            BisaDilaporkan catatanAudit = new BisaDilaporkan() {
                @Override
                public String buatRingkasanStatus() {
                    return "Audit catatan untuk item " + item.getKode() + " telah dicatat.";
                }
            };
            view.tampilkanPesan(catatanAudit.buatRingkasanStatus());
        } catch (AksesDitolakException e) {
            view.tampilkanPesan("Error: " + e.getMessage());
        }
    }

    public void kirimUpdateTim() {
        String pesan = view.mintaTeks("Masukkan pesan update dari Fahad");
        anggota1.kirimPesan(pesan);
    }

    public void lihatLogTim() {
        kanalTim.cetakLog();
    }
}