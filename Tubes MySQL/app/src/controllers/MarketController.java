package pbo.controllers;

import pbo.models.*;
import java.util.ArrayList;
import java.util.List;

public class MarketController {
    private List<Product> productList;

    public MarketController() {
        this.productList = new ArrayList<>();
    }

    public void addMie(String id, String nama, double harga, int stok, String rasa, double berat) {
        Product mie = new Mie(id, nama, harga, stok, rasa, berat);
        productList.add(mie);
    }

    public void addBuahBuahan(String id, String nama, double harga, int stok, String jenis, double berat, String kadaluarsa) {
        Product buah = new BuahBuahan(id, nama, harga, stok, jenis, berat, kadaluarsa);
        productList.add(buah);
    }

    public void addFrozenFood(String id, String nama, double harga, int stok, double suhu, String kadaluarsa) {
        Product frozen = new FrozenFood(id, nama, harga, stok, suhu, kadaluarsa);
        productList.add(frozen);
    }

    public List<Product> getAllProducts() {
        return this.productList;
    }
}