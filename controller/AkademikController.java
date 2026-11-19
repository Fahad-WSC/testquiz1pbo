package aplikasiakademik.controller;
//(Controller pada MVC + SOLID DIP/SRP + Exception Handling + Anonymous Inner Class)
import aplikasiakademik.model.BisaDipinjam;
import aplikasiakademik.model.Buku;
import aplikasiakademik.model.ItemAkademik;
import aplikasiakademik.model.ModulPraktikum;
import aplikasiakademik.model.RepositoriGenerik;
import aplikasiakademik.view.TerminalView;

public class AkademikController {

    public static class InputTidakValidException extends Exception {
        public InputTidakValidException(String message) {
            super(message);
        }
    }

    private RepositoriGenerik<ItemAkademik> repositori;
    private TerminalView view;

    public AkademikController(RepositoriGenerik<ItemAkademik> repositori, TerminalView view) {
        this.repositori = repositori;
        this.view = view;
    }

    private void validasiDurasiPinjam(int hari) throws InputTidakValidException {
        if (hari < 1 || hari > ItemAkademik.BATAS_PINJAM_HARI) {
            throw new InputTidakValidException(
                "Durasi pinjam harus antara 1 sampai " + ItemAkademik.BATAS_PINJAM_HARI + " hari."
            );
        }
    }

    public void tambahBukuDariInput() {
        try {
            String kode = view.mintaInputTeks("Masukkan kode buku");
            String judul = view.mintaInputTeks("Masukkan judul buku");
            String penulis = view.mintaInputTeks("Masukkan nama penulis");
            int halaman = view.mintaInputAngka("Masukkan jumlah halaman");

            if (kode.isEmpty() || judul.isEmpty() || halaman <= 0) {
                throw new InputTidakValidException("Kode, judul wajib diisi, dan halaman harus > 0.");
            }

            Buku bukuBaru = new Buku(kode, judul, penulis, halaman);
            repositori.tambah(bukuBaru);
            view.tampilkanPesan("Buku berhasil ditambahkan.");
        } catch (NumberFormatException e) {
            view.tampilkanPesan("Input jumlah halaman harus berupa angka bulat.");
        } catch (InputTidakValidException e) {
            view.tampilkanPesan("Validasi gagal: " + e.getMessage());
        } finally {
            view.tampilkanPesan("[Proses tambah buku selesai]");
        }
    }

    public void tambahModulDariInput() {
        try {
            String kode = view.mintaInputTeks("Masukkan kode modul");
            String judul = view.mintaInputTeks("Masukkan judul modul");
            String mataKuliah = view.mintaInputTeks("Masukkan nama mata kuliah");
            int pertemuan = view.mintaInputAngka("Masukkan pertemuan ke");

            if (kode.isEmpty() || judul.isEmpty() || pertemuan <= 0) {
                throw new InputTidakValidException("Kode, judul wajib diisi, dan pertemuan harus > 0.");
            }

            ModulPraktikum modulBaru = new ModulPraktikum(kode, judul, mataKuliah, pertemuan);
            repositori.tambah(modulBaru);
            view.tampilkanPesan("Modul praktikum berhasil ditambahkan.");
        } catch (NumberFormatException e) {
            view.tampilkanPesan("Input pertemuan harus berupa angka bulat.");
        } catch (InputTidakValidException e) {
            view.tampilkanPesan("Validasi gagal: " + e.getMessage());
        } finally {
            view.tampilkanPesan("[Proses tambah modul selesai]");
        }
    }

    public void tampilkanSemuaItem() {
        view.tampilkanDaftarItem(repositori.ambilSemua());
    }

    public void pinjamItemDariInput() {
        try {
            String kode = view.mintaInputTeks("Masukkan kode item yang ingin dipinjam");
            int hari = view.mintaInputAngka("Masukkan lama pinjam (hari)");
            validasiDurasiPinjam(hari);

            ItemAkademik item = repositori.cariBerdasarkanKode(kode);
            if (item == null) {
                view.tampilkanPesan("Item dengan kode '" + kode + "' tidak ditemukan.");
                return;
            }

            if (item instanceof BisaDipinjam) {
                BisaDipinjam objekPinjam = (BisaDipinjam) item;
                objekPinjam.pinjam(hari);
            } else {
                // Contoh Anonymous Inner Class untuk perilaku sekali pakai
                BisaDipinjam pinjamKhusus = new BisaDipinjam() {
                    @Override
                    public void pinjam(int durasi) {
                        System.out.println("Peminjaman khusus untuk " + item.getJudul() + " selama " + durasi + " hari.");
                    }
                };
                pinjamKhusus.pinjam(hari);
            }
        } catch (NumberFormatException e) {
            view.tampilkanPesan("Lama pinjam harus berupa angka bulat.");
        } catch (InputTidakValidException e) {
            view.tampilkanPesan("Gagal meminjam: " + e.getMessage());
        }
    }

    public void ubahNamaKampus() {
        String kampusBaru = view.mintaInputTeks("Masukkan nama kampus baru");
        if (!kampusBaru.isEmpty()) {
            ItemAkademik.namaKampus = kampusBaru;
            view.tampilkanPesan("Nama kampus static berhasil diubah menjadi: " + ItemAkademik.namaKampus);
        } else {
            view.tampilkanPesan("Nama kampus tidak boleh kosong.");
        }
    }
}