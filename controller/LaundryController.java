package laundrysimpel.controller;

import laundrysimpel.model.AntreanLaundry;
import laundrysimpel.model.CuciKomplit;
import laundrysimpel.model.JenisLayanan;
import laundrysimpel.model.PaketLaundry;
import laundrysimpel.view.LaundryView;

public class LaundryController {
    private LaundryView view;
    private AntreanLaundry<PaketLaundry> antrean;

    public LaundryController(LaundryView view) {
        this.view = view;
        this.antrean = new AntreanLaundry<>();
    }

    public void tambahPesanan() {
        try {
            String kode = view.mintaTeks("Kode pesanan");
            String nama = view.mintaTeks("Nama pelanggan");
            String jenisInput = view.mintaTeks("Layanan (1 = REGULER, 2 = EKSPRES)");
            double berat = view.mintaAngkaDesimal("Berat (kg)");

            if (berat <= 0) {
                throw new IllegalArgumentException("Berat harus lebih dari 0 kg.");
            }

            JenisLayanan layanan = JenisLayanan.REGULER;
            if (jenisInput.equals("2")) {
                layanan = JenisLayanan.EKSPRES;
            }

            CuciKomplit pesanan = new CuciKomplit(kode, nama, layanan, berat);
            antrean.tambahPesanan(pesanan);
            view.tampilPesan("Pesanan berhasil disimpan.");
        } catch (NumberFormatException e) {
            view.tampilPesan("Input berat harus berupa angka.");
        } catch (IllegalArgumentException e) {
            view.tampilPesan("Error: " + e.getMessage());
        }
    }

    public void lihatSemuaPesanan() {
        view.tampilDaftar(antrean.ambilSemua());
    }
}