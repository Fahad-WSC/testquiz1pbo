package belanjaleveltinggi.model;

import java.util.ArrayList;
import java.util.List;

public class RakBelanja<T extends BarangBelanja> {
    private List<T> daftarBarang;

    public RakBelanja() {
        this.daftarBarang = new ArrayList<>();
    }

    public void tambahBarang(T barang) {
        daftarBarang.add(barang);
    }

    public List<T> ambilSemua() {
        return daftarBarang;
    }

    public T cariByKode(String kode) {
        for (int i = 0; i < daftarBarang.size(); i++) {
            T barang = daftarBarang.get(i);
            if (barang.getKode().equalsIgnoreCase(kode)) {
                return barang;
            }
        }
        return null;
    }

    public static void cetakDaftar(List<? extends BarangBelanja> sumber) {
        if (sumber.isEmpty()) {
            System.out.println("Rak kosong.");
            return;
        }
        for (int i = 0; i < sumber.size(); i++) {
            System.out.println((i + 1) + ". " + sumber.get(i).getInfoBarang());
        }
    }

    public static void salinBarang(List<? extends BarangBelanja> sumber, List<? super BarangBelanja> tujuan) {
        for (int i = 0; i < sumber.size(); i++) {
            tujuan.add(sumber.get(i));
        }
    }
}