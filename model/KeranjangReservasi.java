package reservasiolahraga.model;
//(Generics & Wildcard)
import java.util.ArrayList;
import java.util.List;

public class KeranjangReservasi<T extends FasilitasOlahraga> {
    private List<T> itemList;

    public KeranjangReservasi() {
        this.itemList = new ArrayList<>();
    }

    public void tambah(T item) {
        itemList.add(item);
    }

    public List<T> ambilSemua() {
        return itemList;
    }

    public static void cetakDaftarFasilitas(List<? extends FasilitasOlahraga> list) {
        if (list.isEmpty()) {
            System.out.println("Daftar fasilitas kosong.");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).getDeskripsiFasilitas());
        }
    }

    public static void pindahkanFasilitas(List<? extends FasilitasOlahraga> asal, List<? super FasilitasOlahraga> tujuan) {
        for (int i = 0; i < asal.size(); i++) {
            tujuan.add(asal.get(i));
        }
    }
}