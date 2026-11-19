package belanjaleveltinggi.controller;

import belanjaleveltinggi.exception.JumlahBarangInvalidException;
import belanjaleveltinggi.exception.KuponTidakValidException;
import belanjaleveltinggi.model.BarangBelanja;
import belanjaleveltinggi.model.BarangToko;
import belanjaleveltinggi.model.DiskonPersen;
import belanjaleveltinggi.model.DiskonPotonganTetap;
import belanjaleveltinggi.model.MediatorKasir;
import belanjaleveltinggi.model.PetugasKasir;
import belanjaleveltinggi.model.RakBelanja;
import belanjaleveltinggi.model.StrategiDiskon;
import belanjaleveltinggi.model.TipeBarang;
import belanjaleveltinggi.view.BelanjaView;
import java.util.ArrayList;
import java.util.List;

public class BelanjaController {
    private BelanjaView view;
    private RakBelanja<BarangBelanja> rak;
    private List<StrategiDiskon> daftarKupon;
    private MediatorKasir mediator;
    private PetugasKasir kasir1;
    private PetugasKasir kasir2;

    public BelanjaController(BelanjaView view) {
        this.view = view;
        this.rak = new RakBelanja<>();
        this.rak.tambahBarang(new BarangToko("BRG-1", "Roti Tawar", TipeBarang.MAKANAN, 15000.0, false));
        this.rak.tambahBarang(new BarangToko("BRG-2", "Kopi Botol", TipeBarang.MINUMAN, 12000.0, true));
        this.rak.tambahBarang(new BarangToko("BRG-3", "Buku Catatan", TipeBarang.ALAT_TULIS, 18000.0, false));

        this.daftarKupon = new ArrayList<>();
        this.daftarKupon.add(new DiskonPersen("HEMAT10", 10.0));
        this.daftarKupon.add(new DiskonPotonganTetap("POTONG5K", 5000.0));

        this.mediator = new MediatorKasir("Shift Pagi");
        this.kasir1 = new PetugasKasir("Kasir A");
        this.kasir2 = new PetugasKasir("Kasir B");
        this.mediator.tambahPetugas(kasir1);
        this.mediator.tambahPetugas(kasir2);
    }

    public void lihatBarang() {
        RakBelanja.cetakDaftar(rak.ambilSemua());
    }

    public void prosesBelanja() {
        try {
            lihatBarang();
            String kode = view.mintaTeks("Masukkan kode barang");
            BarangBelanja barang = rak.cariByKode(kode);
            if (barang == null) {
                throw new JumlahBarangInvalidException("Barang dengan kode '" + kode + "' tidak ada.");
            }

            int jumlah = view.mintaAngka("Masukkan jumlah barang");
            if (jumlah <= 0 || jumlah > BarangBelanja.MAKS_ITEM_PER_TRANSAKSI) {
                throw new JumlahBarangInvalidException(
                    "Jumlah harus antara 1 sampai " + BarangBelanja.MAKS_ITEM_PER_TRANSAKSI
                );
            }

            double subtotal = barang.hitungSubtotal(jumlah, 1000.0);
            view.tampilkanPesan("Subtotal (termasuk kemasan Rp1000): Rp" + subtotal);

            List<StrategiDiskon> kuponBisa = new ArrayList<>();
            for (int i = 0; i < daftarKupon.size(); i++) {
                StrategiDiskon kupon = daftarKupon.get(i);
                if (kupon.bisaDipakai(subtotal)) {
                    kuponBisa.add(kupon);
                }
            }

            double totalAkhir = subtotal;
            if (!kuponBisa.isEmpty()) {
                for (int i = 0; i < kuponBisa.size(); i++) {
                    view.tampilkanPesan((i + 1) + ". " + kuponBisa.get(i).getNamaKupon());
                }
                int pilihKupon = view.mintaAngka("Pilih kupon (0 jika tanpa kupon)");
                if (pilihKupon > 0) {
                    if (pilihKupon > kuponBisa.size()) {
                        throw new KuponTidakValidException("Nomor kupon tidak valid.");
                    }
                    StrategiDiskon strategiDipilih = kuponBisa.get(pilihKupon - 1);
                    totalAkhir = strategiDipilih.hitungTotalSetelahDiskon(subtotal);
                }
            }

            // Anonymous Inner Class untuk mencetak catatan struk sekali pakai
            Runnable cetakPenutup = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Terima kasih telah berbelanja di " + BarangBelanja.namaToko + ".");
                }
            };
            cetakPenutup.run();

            view.tampilkanPesan("Total yang harus dibayar: Rp" + totalAkhir);
            kasir1.beritahuTim("Transaksi " + barang.getNama() + " x" + jumlah + " = Rp" + totalAkhir);
        } catch (NumberFormatException e) {
            view.tampilkanPesan("Input angka tidak valid.");
        } catch (JumlahBarangInvalidException e) {
            view.tampilkanPesan("Error jumlah: " + e.getMessage());
        } catch (KuponTidakValidException e) {
            view.tampilkanPesan("Error kupon: " + e.getMessage());
        } finally {
            view.tampilkanPesan("[Sesi transaksi selesai]");
        }
    }

    public void kirimInfoKasir() {
        String info = view.mintaTeks("Pesan dari Kasir A");
        kasir1.beritahuTim(info);
    }

    public void lihatLogKasir() {
        mediator.cetakLog();
    }
}