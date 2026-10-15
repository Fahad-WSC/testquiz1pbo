package pbo.controllers;

import pbo.models.*;
import java.util.List;

public class MarketController {
    private MarketContainer<Product> productContainer;

    public MarketController() {
        this.productContainer = new MarketContainer<>();
    }

    public void addProduct(int categoryChoice, String id, String nama, double harga, int stok, String param1, double param2, String kadaluarsa) throws MarketException {
        if (harga < 0 || stok < 0) {
            throw new MarketException("Harga dan stok produk tidak boleh bernilai negatif!");
        }
        
        // Menggunakan Factory Pattern untuk membuat objek produk
        Product product = ProductFactory.createProduct(categoryChoice, id, nama, harga, stok, param1, param2, kadaluarsa);
        productContainer.addItem(product);
    }

    public List<Product> getAllProducts() {
        return productContainer.getItems();
    }
}