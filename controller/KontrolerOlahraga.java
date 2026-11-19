package reservasiolahraga.controller;

import java.util.ArrayList;
import java.util.List;
import reservasiolahraga.exception.JadwalPenuhException;
import reservasiolahraga.exception.TarifTidakValidException;
import reservasiolahraga.model.BisaDicekKondisi;
import reservasiolahraga.model.FasilitasOlahraga;
import reservasiolahraga.model.KeranjangReservasi;
import reservasiolahraga.model.LapanganFutsal;
import reservasiolahraga.model.PapanPengumuman;
import reservasiolahraga.model.PesertaLatihan;
import reservasiolahraga.model.StrategiTarif;
import reservasiolahraga.model.TarifMember;
import reservasiolahraga.model.TarifReguler;
import reservasiolahraga.model.TipeLapangan;
import reservasiolahraga.view.TampilanOlahraga;

public class KontrolerOlahraga {
    private TampilanOlahraga view;
    private KeranjangReservasi<FasilitasOlahraga> keranjang;
    private List<StrategiTarif> daftarStrategi;
    private PapanPengumuman papanPengumuman;
    private PesertaLatihan kapten1;
    private PesertaLatihan kapten2;

    public KontrolerOlahraga(TampilanOlahraga view) {
        this.view = view;
        this.keranjang = new KeranjangReservasi<>();
        this.keranjang.tambah(new LapanganFutsal("LP-01", "Lapangan A", TipeLapangan.INDOOR_VINYL, 8, true));
        this.keranjang.tambah(new LapanganFutsal("LP-02", "Lapangan B", TipeLapangan.OUTDOOR_RUMPUT, 6, false));

        this.daftarStrategi = new ArrayList<>();
        this.daftarStrategi.add(new TarifReguler());
        this.daftarStrategi.add(new TarifMember("MBR-77"));

        this.papanPengumuman = new PapanPengumuman("Komunitas Futsal");
        this.kapten1 = new PesertaLatihan("Rizky");
        this.kapten2 = new PesertaLatihan("Dimas");
        this.papanPengumuman.gabungPeserta(kapten1);
        this.papanPengumuman.gabungPeserta(kapten2);
    }

    public void tampilkanLapangan() {
        KeranjangReservasi.cetakDaftarFasilitas(keranjang.ambilSemua());
    }

    public void prosesSewaLapangan() {
        try {
            tampilkanLapangan();
            int indeksLapangan = view.mintaAngka("Pilih nomor lapangan") - 1;
            List<FasilitasOlahraga> items = keranjang.ambilSemua();
            if (indeksLapangan < 0 || indeksLapangan >= items.size()) {
                throw new JadwalPenuhException("Nomor lapangan tidak ditemukan.");
            }

            FasilitasOlahraga lapanganDipilih = items.get(indeksLapangan);
            int jumlahJam = view.mintaAngka("Masukkan jumlah jam sewa");
            if (jumlahJam <= 0 || jumlahJam > FasilitasOlahraga.MAKSIMAL_JAM_SEWA) {
                throw new TarifTidakValidException("Jumlah jam harus antara 1 sampai " + FasilitasOlahraga.MAKSIMAL_JAM_SEWA);
            }
            if (jumlahJam > lapanganDipilih.getSlotTersedia()) {
                throw new JadwalPenuhException("Slot tidak cukup. Sisa slot: " + lapanganDipilih.getSlotTersedia());
            }

            String jawabAlat = view.mintaTeks("Sewa perlengkapan tambahan? (ya/tidak)");
            boolean sewaAlat = jawabAlat.equalsIgnoreCase("ya");
            double biayaAwal = lapanganDipilih.hitungBiayaDasar(jumlahJam, sewaAlat);

            List<StrategiTarif> strategiAktif = new ArrayList<>();
            for (int i = 0; i < daftarStrategi.size(); i++) {
                StrategiTarif st = daftarStrategi.get(i);
                if (st.bisaDipakai(biayaAwal)) {
                    strategiAktif.add(st);
                }
            }

            if (strategiAktif.isEmpty()) {
                throw new TarifTidakValidException("Tidak ada strategi tarif yang cocok.");
            }

            view.tampilkanPesan("Biaya awal: Rp" + biayaAwal);
            for (int i = 0; i < strategiAktif.size(); i++) {
                view.tampilkanPesan((i + 1) + ". " + strategiAktif.get(i).getNamaStrategi());
            }

            int pilihTarif = view.mintaAngka("Pilih jenis tarif") - 1;
            if (pilihTarif < 0 || pilihTarif >= strategiAktif.size()) {
                throw new TarifTidakValidException("Pilihan tarif tidak valid.");
            }

            double biayaAkhir = strategiAktif.get(pilihTarif).hitungBiayaAkhir(biayaAwal);
            lapanganDipilih.pakaiSlot(jumlahJam);

            if (lapanganDipilih instanceof BisaDicekKondisi) {
                BisaDicekKondisi dicek = (BisaDicekKondisi) lapanganDipilih;
                dicek.cekKondisi();
            }

            // Contoh Anonymous Inner Class untuk catatan verifikasi sekali pakai
            BisaDicekKondisi catatanKhusus = new BisaDicekKondisi() {
                @Override
                public void cekKondisi() {
                    System.out.println("Verifikasi tambahan selesai untuk " + lapanganDipilih.getNama() + " (" + jumlahJam + " jam).");
                }
            };
            catatanKhusus.cekKondisi();

            view.tampilkanPesan("Reservasi berhasil! Total bayar: Rp" + biayaAkhir);
        } catch (NumberFormatException e) {
            view.tampilkanPesan("Input harus berupa angka.");
        } catch (JadwalPenuhException e) {
            view.tampilkanPesan("Reservasi gagal: " + e.getMessage());
        } catch (TarifTidakValidException e) {
            view.tampilkanPesan("Tarif gagal: " + e.getMessage());
        } finally {
            view.tampilkanPesan("[Selesai memproses reservasi]");
        }
    }

    public void prosesKirimInfo() {
        String info = view.mintaTeks("Masukkan info mabar dari Rizky");
        kapten1.kirimPengumuman(info);
    }

    public void tampilkanLog() {
        papanPengumuman.tampilkanSemuaLog();
    }
}