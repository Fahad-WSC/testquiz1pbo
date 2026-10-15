package pbo;

import pbo.controllers.MarketController;
import pbo.views.MarketView;

public class App {
    public static void main(String[] args) {
        MarketController controller = new MarketController();
        MarketView view = new MarketView(controller);
        
        // Menjalankan program melalui view/controller
        view.showMenu();
    }
}