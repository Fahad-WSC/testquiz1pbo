package laundrysimpel.model;

import java.util.ArrayList;
import java.util.List;

public class AntreanLaundry<T extends PaketLaundry> {
    private List<T> daftarPesanan;

    public AntreanLaundry() {
        this.daftarPesanan = new ArrayList<>();
    }

    public void tambahPesanan(T pesanan) {
        daftarPesanan.add(pesanan);
    }

    public List<T> ambilSemua() {
        return daftarPesanan;
    }
}