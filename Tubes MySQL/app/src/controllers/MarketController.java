package pbo.controllers;

import pbo.models.*;
import java.util.List;

public class MarketController {
    private MarketContainer<Product> productContainer;

    public MarketController() {
        this.productContainer = new MarketContainer<>();
    }

    public void addMie(String id, String nama, double harga, int stok, String rasa, double berat) throws MarketException {
        if (harga < 0 || stok < 0) {
            throw new MarketException("Harga dan stok Mie tidak boleh bernilai negatif!");
        }
        Product mie = new Mie(id, nama, harga, stok, rasa, berat);
        productContainer.addItem(mie);
    }

    public void addBuahBuahan(String id, String nama, double harga, int stok, String jenis, double berat, String kadaluarsa) throws MarketException {
        if (harga < 0 || stok < 0) {
            throw new MarketException("Harga dan stok Buah-buahan tidak boleh bernilai negatif!");
        }
        Product buah = new BuahBuahan(id, nama, harga, stok, jenis, berat, kadaluarsa);
        productContainer.addItem(buah);
    }

    public void addFrozenFood(String id, String nama, double harga, int stok, double suhu, String kadaluarsa) throws MarketException {
        if (harga < 0 || stok < 0) {
            throw new MarketException("Harga dan stok Frozen Food tidak boleh bernilai negatif!");
        }
        Product frozen = new FrozenFood(id, nama, harga, stok, suhu, kadaluarsa);
        productContainer.addItem(frozen);
    }

    public List<Product> getAllProducts() {
        return productContainer.getItems();
    }
}