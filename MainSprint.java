package manajemenproyek;

import manajemenproyek.controller.KontrolerSprint;
import manajemenproyek.view.TampilanSprint;

public class MainSprint {
    public static void main(String[] args) {
        TampilanSprint view = new TampilanSprint();
        KontrolerSprint controller = new KontrolerSprint(view);

        boolean berjalan = true;
        while (berjalan) {
            view.tampilkanMenu();
            String pilihan = view.mintaTeks("Pilihan");

            if (pilihan.equals("1")) {
                controller.tambahTugas();
            } else if (pilihan.equals("2")) {
                controller.lihatSemuaTugas();
            } else if (pilihan.equals("3")) {
                controller.hitungSkorTugas();
            } else if (pilihan.equals("4")) {
                controller.selesaikanDanLaporkanTugas();
            } else if (pilihan.equals("5")) {
                controller.kirimUpdateTim();
            } else if (pilihan.equals("6")) {
                controller.lihatLogTim();
            } else if (pilihan.equals("0")) {
                berjalan = false;
                view.tampilkanPesan("Program ditutup.");
            } else {
                view.tampilkanPesan("Pilihan tidak valid.");
            }
        }
    }
}