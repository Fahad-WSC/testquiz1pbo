import java.util.ArrayList;
import java.util.List;

public class OrderController {
    private List<MenuItem> daftarMenu;

    public OrderController() {
        daftarMenu = new ArrayList<>();
        // Inisialisasi Menu Awal
        daftarMenu.add(new Kopi("M-01", "Kopi Susu", 18000, 5));
        daftarMenu.add(new Teh("M-02", "Teh Tarik", 15000, 10));
        daftarMenu.add(new Roti("M-03", "Roti Cokelat", 12000, 8));
    }

    public List<MenuItem> getDaftarMenu() {
        return daftarMenu;
    }

    public MenuItem cariMenuById(String id) throws MenuTidakDitemukanException {
        for (MenuItem item : daftarMenu) {
            if (item.getId().equalsIgnoreCase(id)) {
                return item;
            }
        }
        throw new MenuTidakDitemukanException("Error: ID Menu '" + id + "' tidak ditemukan!");
    }

    public void validasiJumlahDanStok(MenuItem item, int jumlah) throws InputTidakValidException, StokTidakCukupException {
        if (jumlah <= 0) {
            throw new InputTidakValidException("Error: Jumlah pesanan harus lebih besar dari 0!");
        }
        if (jumlah > item.getStok()) {
            throw new StokTidakCukupException("Error: Stok tidak cukup! Stok yang tersedia hanya: " + item.getStok());
        }
    }

    public void prosesPemesanan(MenuItem item, int jumlah) {
        item.setStok(item.getStok() - jumlah);
    }
}