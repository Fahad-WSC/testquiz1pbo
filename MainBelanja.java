package belanjaleveltinggi;

import belanjaleveltinggi.controller.BelanjaController;
import belanjaleveltinggi.view.BelanjaView;

public class MainBelanja {
    public static void main(String[] args) {
        BelanjaView view = new BelanjaView();
        BelanjaController controller = new BelanjaController(view);

        boolean lanjut = true;
        while (lanjut) {
            view.tampilkanMenu();
            String pilihan = view.mintaTeks("Pilihan");

            if (pilihan.equals("1")) {
                controller.lihatBarang();
            } else if (pilihan.equals("2")) {
                controller.prosesBelanja();
            } else if (pilihan.equals("3")) {
                controller.kirimInfoKasir();
            } else if (pilihan.equals("4")) {
                controller.lihatLogKasir();
            } else if (pilihan.equals("0")) {
                lanjut = false;
                view.tampilkanPesan("Program selesai.");
            } else {
                view.tampilkanPesan("Pilihan tidak valid.");
            }
        }
    }
}