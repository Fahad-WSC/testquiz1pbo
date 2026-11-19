package laundrysimpel;

import laundrysimpel.controller.LaundryController;
import laundrysimpel.view.LaundryView;

public class MainLaundry {
    public static void main(String[] args) {
        LaundryView view = new LaundryView();
        LaundryController controller = new LaundryController(view);

        boolean jalan = true;
        while (jalan) {
            view.tampilMenu();
            String pilih = view.mintaTeks("Menu");

            if (pilih.equals("1")) {
                controller.tambahPesanan();
            } else if (pilih.equals("2")) {
                controller.lihatSemuaPesanan();
            } else if (pilih.equals("0")) {
                jalan = false;
                view.tampilPesan("Keluar dari program.");
            } else {
                view.tampilPesan("Pilihan tidak valid.");
            }
        }
    }
}