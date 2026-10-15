package pbo.models;

public class ProductFactory {
    public static Product createProduct(int categoryChoice, String id, String nama, double harga, int stok, String param1, double param2, String kadaluarsa) throws MarketException {
        switch (categoryChoice) {
            case 1:
                // param1 = rasa, param2 = berat (gram)
                return new Mie(id, nama, harga, stok, param1, param2);
            case 2:
                // param1 = jenis (Lokal/Import), param2 = berat (kg), kadaluarsa = tanggal
                return new BuahBuahan(id, nama, harga, stok, param1, param2, kadaluarsa);
            case 3:
                // param1 diabaikan atau untuk suhu, param2 = suhuPenyimpanan, kadaluarsa = tanggal
                return new FrozenFood(id, nama, harga, stok, param2, kadaluarsa);
            default:
                throw new MarketException("Kategori produk tidak valid!");
        }
    }
}