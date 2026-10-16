package pbo.controllers;

import pbo.models.*;
import pbo.exceptions.InputTidakValidException;
import pbo.exceptions.StokTidakCukupException;
import pbo.exceptions.MenuTidakDitemukanException;
import java.util.ArrayList;
import java.util.List;

public class MenuItemController {
    private List<MenuItem> menuItems;

    public MenuItemController() {
        this.menuItems = new ArrayList<>();
    }

    public void addKopi(String id, String nama, double hargaBase, int stok , int rum , int es , int gula, int karamel, int jumlah, Ukuran ukuran) throws InputTidakValidException, StokTidakCukupException , MenuTidakDitemukanException {
        if(id == null || id.isEmpty()) {
            throw new MenuTidakDitemukanException("ID salah");
        }
        if (jumlah < 0 ) {
            throw new InputTidakValidException("JumlahTidak boleh 0");
        } 
        if (jumlah > stok) {
            throw new StokTidakCukupException("Jumlah melebihi stok yang tersedia");
        }
        MenuItem kopi = new Kopi(id, nama, hargaBase, stok, rum, es, gula, karamel, jumlah, ukuran);
        menuItems.add(kopi);
    }

    public void addTeh(String id, String nama, double hargaBase, int stok, String rasa, int jumlah, Ukuran ukuran) throws InputTidakValidException, StokTidakCukupException , MenuTidakDitemukanException {
        if(id == null || id.isEmpty()) {
            throw new MenuTidakDitemukanException("ID salah");
        }
        if (jumlah < 0 ) {
            throw new InputTidakValidException("JumlahTidak boleh 0");
        } 
        if (jumlah > stok) {
            throw new StokTidakCukupException("Jumlah melebihi stok yang tersedia");
        }
        MenuItem teh = new Teh(id, nama, hargaBase, stok, rasa, jumlah, ukuran);
        menuItems.add(teh);
    }

    public void addRoti(String id, String nama, double harga, int stok ,int gula, int meses, int karamel, int jumlah, Ukuran ukuran) throws InputTidakValidException, StokTidakCukupException , MenuTidakDitemukanException {
        if(id == null || id.isEmpty()) {
            throw new MenuTidakDitemukanException("ID salah");
        }
        if (jumlah < 0 ) {
            throw new InputTidakValidException("Jumlah Tidak boleh 0");
        }
        if (jumlah > stok ) {
            throw new StokTidakCukupException("Jumlah melebihi stok yang tersedia");
        }
        MenuItem roti = new Roti(id, nama, harga, stok, gula, meses, karamel, jumlah, ukuran);
        menuItems.add(roti);
    }

    public List<MenuItem> getAllMenuItems() {
        return this.menuItems;
    }
}