package pbo;

import pbo.controllers.MenuItemController;
import pbo.views.MemuItemView;

public class App {
    public static void main(String[] args) {
        MenuItemController controller = new MenuItemController();
        MenuItemView view = new MenuItemView(controller);

        MenuItemView view = new MenuItemView(controller);
        
        view.showMenu();
    }
}