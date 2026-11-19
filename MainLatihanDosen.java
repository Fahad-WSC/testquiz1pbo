package latihandosen;

import latihandosen.controller.KontrolerKafeChat;
import latihandosen.view.TampilanKafeChat;

public class MainLatihanDosen {
    public static void main(String[] args) {
        TampilanKafeChat view = new TampilanKafeChat();
        KontrolerKafeChat controller = new KontrolerKafeChat(view);

        boolean jalan = true;
        while (jalan) {
            view.tampilkanMenu();
            String pilihan = view.bacaTeks("Pilihan");

            if (pilihan.equals("1")) {
                controller.prosesPesananDanBayar();
            } else if (pilihan.equals("2")) {
                controller.prosesKirimChat();
            } else if (pilihan.equals("3")) {
                controller.lihatRiwayatChat();
            } else if (pilihan.equals("0")) {
                jalan = false;
                view.tampilkanPesan("Selesai.");
            } else {
                view.tampilkanPesan("Pilihan tidak valid.");
            }
        }
    }
}