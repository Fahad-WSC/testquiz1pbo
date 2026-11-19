package latihandosen.controller;

import java.util.ArrayList;
import java.util.List;
import latihandosen.exception.PembayaranGagalException;
import latihandosen.exception.StokKurangException;
import latihandosen.model.AnggotaChat;
import latihandosen.model.BayarEWallet;
import latihandosen.model.BayarTunai;
import latihandosen.model.KopiPesanan;
import latihandosen.model.RuangObrolan;
import latihandosen.model.StrategiBayar;
import latihandosen.model.UkuranPesanan;
import latihandosen.view.TampilanKafeChat;

public class KontrolerKafeChat {
    private TampilanKafeChat view;
    private KopiPesanan kopi;
    private List<StrategiBayar> metodePembayaran;
    private RuangObrolan ruangObrolan;
    private AnggotaChat anggota1;
    private AnggotaChat anggota2;

    public KontrolerKafeChat(TampilanKafeChat view) {
        this.view = view;
        this.kopi = new KopiPesanan("KP-01", "Kopi Susu Kampus", 20000.0, 10);

        this.metodePembayaran = new ArrayList<>();
        this.metodePembayaran.add(new BayarTunai(100000.0));
        this.metodePembayaran.add(new BayarEWallet("08123456789", 250000.0, "1234"));

        this.ruangObrolan = new RuangObrolan("Diskusi UAS");
        this.anggota1 = new AnggotaChat("Fahad");
        this.anggota2 = new AnggotaChat("Partner");
        this.ruangObrolan.daftarAnggota(anggota1);
        this.ruangObrolan.daftarAnggota(anggota2);
    }

    public void prosesPesananDanBayar() {
        try {
            view.tampilkanPesan("Menu tersedia: " + kopi.getNama() + " (Stok: " + kopi.getStok() + ", Harga: Rp" + kopi.getHargaDasar() + ")");
            int jumlah = view.bacaAngka("Masukkan jumlah pesanan");
            if (jumlah <= 0) {
                throw new StokKurangException("Jumlah pesanan harus lebih dari 0.");
            }
            if (jumlah > kopi.getStok()) {
                throw new StokKurangException("Stok tidak cukup. Sisa stok: " + kopi.getStok());
            }

            int gula = view.bacaAngka("Jumlah gula (0-5)");
            int es = view.bacaAngka("Jumlah es (0-5)");
            kopi.aturKustomisasi(gula, es);

            double totalTagihan = kopi.hitungTotal(jumlah, UkuranPesanan.MEDIUM);

            List<StrategiBayar> metodeTersedia = new ArrayList<>();
            for (int i = 0; i < metodePembayaran.size(); i++) {
                StrategiBayar strategi = metodePembayaran.get(i);
                if (strategi.canPay(totalTagihan)) {
                    metodeTersedia.add(strategi);
                }
            }

            if (metodeTersedia.isEmpty()) {
                throw new PembayaranGagalException("Tidak ada metode pembayaran yang mendukung nominal Rp" + totalTagihan);
            }

            view.tampilkanPesan("Total tagihan (MEDIUM): Rp" + totalTagihan);
            for (int i = 0; i < metodeTersedia.size(); i++) {
                view.tampilkanPesan((i + 1) + ". " + metodeTersedia.get(i).getNamaMetode());
            }

            int pilihMetode = view.bacaAngka("Pilih metode pembayaran") - 1;
            if (pilihMetode < 0 || pilihMetode >= metodeTersedia.size()) {
                throw new PembayaranGagalException("Pilihan metode tidak valid.");
            }

            StrategiBayar strategiDipilih = metodeTersedia.get(pilihMetode);
            List<String> otorisasi = strategiDipilih.getRequiredAuthorizations();
            for (int i = 0; i < otorisasi.size(); i++) {
                String jenisAuth = otorisasi.get(i);
                String kodeInput = view.bacaTeks("Masukkan " + jenisAuth);
                if (jenisAuth.equals("PIN") && strategiDipilih instanceof BayarEWallet) {
                    BayarEWallet ew = (BayarEWallet) strategiDipilih;
                    if (!kodeInput.equals(ew.getPinBenar())) {
                        throw new PembayaranGagalException("PIN E-Wallet salah.");
                    }
                } else if (kodeInput.isEmpty()) {
                    throw new PembayaranGagalException("Otorisasi " + jenisAuth + " tidak boleh kosong.");
                }
            }

            strategiDipilih.pay(totalTagihan);
            kopi.kurangiStok(jumlah);
            view.tampilkanPesan(kopi.getRingkasanStruk(jumlah, totalTagihan));
        } catch (NumberFormatException e) {
            view.tampilkanPesan("Input angka tidak valid.");
        } catch (StokKurangException e) {
            view.tampilkanPesan("Pesanan gagal: " + e.getMessage());
        } catch (PembayaranGagalException e) {
            view.tampilkanPesan("Pembayaran gagal: " + e.getMessage());
        }
    }

    public void prosesKirimChat() {
        String pesan = view.bacaTeks("Pesan dari Fahad ke ruang obrolan");
        anggota1.kirimPesan(pesan);
    }

    public void lihatRiwayatChat() {
        ruangObrolan.tampilkanRiwayat();
    }
}