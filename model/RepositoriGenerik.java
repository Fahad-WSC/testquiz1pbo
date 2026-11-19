package aplikasiakademik.model;
//(Generics & Wildcard)
import java.util.ArrayList;
import java.util.List;

public class RepositoriGenerik<T extends ItemAkademik> {
    private List<T> daftarItem;

    public RepositoriGenerik() {
        this.daftarItem = new ArrayList<>();
    }

    public void tambah(T item) {
        daftarItem.add(item);
    }

    public List<T> ambilSemua() {
        return daftarItem;
    }

    public T cariBerdasarkanKode(String kode) {
        for (int i = 0; i < daftarItem.size(); i++) {
            T item = daftarItem.get(i);
            if (item.getKode().equalsIgnoreCase(kode)) {
                return item;
            }
        }
        return null;
    }

    public static void cetakRingkasanSemua(List<? extends ItemAkademik> items) {
        if (items.isEmpty()) {
            System.out.println("Belum ada data item.");
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getRingkasanDetail());
        }
    }

    public static void salinKeTujuan(List<? extends ItemAkademik> sumber, List<? super ItemAkademik> tujuan) {
        for (int i = 0; i < sumber.size(); i++) {
            tujuan.add(sumber.get(i));
        }
    }
}