package aplikasiakademik;

import aplikasiakademik.controller.AkademikController;
import aplikasiakademik.model.Buku;
import aplikasiakademik.model.ItemAkademik;
import aplikasiakademik.model.ModulPraktikum;
import aplikasiakademik.model.RepositoriGenerik;
import aplikasiakademik.view.TerminalView;

public class MainAplikasi {
    public static void main(String[] args) {
        RepositoriGenerik<ItemAkademik> repositori = new RepositoriGenerik<>();
        repositori.tambah(new Buku("BK01", "Dasar Pemrograman Java", "Tim Pengajar", 240));
        repositori.tambah(new ModulPraktikum("MD01", "Modul Generics & MVC", "PPBO", 6));

        TerminalView view = new TerminalView();
        AkademikController controller = new AkademikController(repositori, view);

        boolean berjalan = true;
        while (berjalan) {
            view.tampilkanMenuUtama();
            String pilihan = view.mintaInputTeks("Pilihan");

            if (pilihan.equals("1")) {
                controller.tambahBukuDariInput();
            } else if (pilihan.equals("2")) {
                controller.tambahModulDariInput();
            } else if (pilihan.equals("3")) {
                controller.tampilkanSemuaItem();
            } else if (pilihan.equals("4")) {
                controller.pinjamItemDariInput();
            } else if (pilihan.equals("5")) {
                controller.ubahNamaKampus();
            } else if (pilihan.equals("0")) {
                berjalan = false;
                view.tampilkanPesan("Terima kasih. Program selesai.");
            } else {
                view.tampilkanPesan("Pilihan tidak valid.");
            }
        }
    }
}