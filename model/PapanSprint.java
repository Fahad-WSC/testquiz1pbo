package manajemenproyek.model;

import java.util.ArrayList;
import java.util.List;

public class PapanSprint<T extends ItemPekerjaan> {
    private List<T> daftarItem;

    public PapanSprint() {
        this.daftarItem = new ArrayList<>();
    }

    public void tambahItem(T item) {
        daftarItem.add(item);
    }

    public List<T> ambilSemua() {
        return daftarItem;
    }

    public int totalStoryPoint() {
        int total = 0;
        for (int i = 0; i < daftarItem.size(); i++) {
            total = total + daftarItem.get(i).getStoryPoint();
        }
        return total;
    }

    public T cariByKode(String kode) {
        for (int i = 0; i < daftarItem.size(); i++) {
            T item = daftarItem.get(i);
            if (item.getKode().equalsIgnoreCase(kode)) {
                return item;
            }
        }
        return null;
    }

    public static void tampilkanSemuaItem(List<? extends ItemPekerjaan> items) {
        if (items.isEmpty()) {
            System.out.println("Belum ada item di sprint.");
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getDetailItem());
        }
    }

    public static void salinItem(List<? extends ItemPekerjaan> sumber, List<? super ItemPekerjaan> tujuan) {
        for (int i = 0; i < sumber.size(); i++) {
            tujuan.add(sumber.get(i));
        }
    }
}