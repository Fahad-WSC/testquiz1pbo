package pbo.models;

import java.util.ArrayList;
import java.util.List;

public class MarketContainer<T> {
    private List<T> items;

    public MarketContainer() {
        this.items = new ArrayList<>();
    }

    public void addItem(T item) {
        items.add(item);
    }

    public List<T> getItems() {
        return items;
    }

    // Contoh Generic Method dengan Wildcard
    public static void printItemDetails(List<?> list) {
        for (Object obj : list) {
            System.out.println("• " + obj.toString());
        }
    }
}