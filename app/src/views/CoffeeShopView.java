import java.util.Scanner;

public class CoffeeShopView {
    private OrderController controller;
    private Scanner scanner;

    public CoffeeShopView(OrderController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("==========================================");
        System.out.println("   SELAMAT DATANG DI COFFEE SHOP APP     ");
        System.out.println("==========================================");

        boolean meprosesMenu = true;
        while (meprosesMenu) {
            tampilkanMenu();
            
            MenuItem selectedMenu = null;
            // 1. Input ID Menu
            while (selectedMenu == null) {
                System.out.print("\nMasukkan ID Menu yang ingin dipesan: ");
                String idInput = scanner.nextLine();
                try {
                    selectedMenu = controller.cariMenuById(idInput);
                } catch (MenuTidakDitemukanException e) {
                    System.out.println(e.getMessage());
                }
            }

            // 2. Input Jumlah Pesanan
            int jumlah = 0;
            boolean jumlahValid = false;
            while (!jumlahValid) {
                System.out.print("Masukkan Jumlah Pesanan: ");
                try {
                    jumlah = Integer.parseInt(scanner.nextLine());
                    controller.validasiJumlahDanStok(selectedMenu, jumlah);
                    jumlahValid = true;
                } catch (NumberFormatException e) {
                    System.out.println("Error: Input harus berupa angka!");
                } catch (InputTidakValidException | StokTidakCukupException e) {
                    System.out.println(e.getMessage());
                }
            }

            // 3. Input Kustomisasi berdasarkan tipe produk
            inputKustomisasi(selectedMenu);

            // 4. Pilih Ukuran / Promo (Overloading Demonstration)
            System.out.println("\nPilih Ukuran Minuman/Makanan:");
            System.out.println("1. SMALL");
            System.out.println("2. MEDIUM");
            System.out.println("3. LARGE");
            System.out.print("Pilihan (1-3): ");
            int opsiUkuran = Integer.parseInt(scanner.nextLine());
            EnumUkuran ukuran = EnumUkuran.MEDIUM;
            if (opsiUkuran == 1) ukuran = EnumUkuran.SMALL;
            else if (opsiUkuran == 3) ukuran = EnumUkuran.LARGE;

            // Update stok dan hitung total
            controller.prosesPemesanan(selectedMenu, jumlah);
            double totalHarga = selectedMenu.hitungHargaTotal(jumlah, ukuran);

            // 5. Cetak Struk
            cetakStruk(selectedMenu, jumlah, ukuran, totalHarga);

            System.out.print("\nApakah ingin memesan lagi? (y/n): ");
            String ulang = scanner.nextLine();
            if (!ulang.equalsIgnoreCase("y")) {
                meprosesMenu = false;
            }
        }
        System.out.println("\nTerima kasih telah berkunjung!");
    }

    private void tampilkanMenu() {
        System.out.println("\n--- DAFTAR MENU ---");
        for (MenuItem item : controller.getDaftarMenu()) {
            System.out.println(item.getId() + " : " + item.getNama() + 
                               " (Harga: Rp" + item.getHargaBase() + 
                               ", Stok: " + item.getStok() + ")");
        }
    }

    private void inputKustomisasi(MenuItem item) {
        System.out.println("\n--- ATUR KUSTOMISASI ---");
        if (item instanceof Kopi) {
            System.out.print("Rum (0-1, +3000): ");
            int rum = Integer.parseInt(scanner.nextLine());
            System.out.print("Es (0-5, Free): ");
            int es = Integer.parseInt(scanner.nextLine());
            System.out.print("Gula (0-5, Free): ");
            int gula = Integer.parseInt(scanner.nextLine());
            System.out.print("Karamel (0-5, +3000): ");
            int karamel = Integer.parseInt(scanner.nextLine());
            
            item.customize(new int[]{rum, es, gula, karamel});
        } else if (item instanceof Teh) {
            System.out.print("Boba (0-3, +1000): ");
            int boba = Integer.parseInt(scanner.nextLine());
            System.out.print("Es (0-5, Free): ");
            int es = Integer.parseInt(scanner.nextLine());
            System.out.print("Gula (0-5, Free): ");
            int gula = Integer.parseInt(scanner.nextLine());
            System.out.print("Karamel (0-5, +3000): ");
            int karamel = Integer.parseInt(scanner.nextLine());
            
            item.customize(new int[]{boba, es, gula, karamel});
        } else if (item instanceof Roti) {
            System.out.print("Gula (0-5, Free): ");
            int gula = Integer.parseInt(scanner.nextLine());
            System.out.print("Meses (0-5, +500): ");
            int meses = Integer.parseInt(scanner.nextLine());
            System.out.print("Karamel (0-5, +3000): ");
            int karamel = Integer.parseInt(scanner.nextLine());
            
            item.customize(new int[]{gula, meses, karamel});
        }
    }

    private void cetakStruk(MenuItem item, int jumlah, EnumUkuran ukuran, double totalHarga) {
        System.out.println("\n==========================================");
        System.out.println("              STRUK PEMESANAN             ");
        System.out.println("==========================================");
        System.out.println(" Detail Item : " + item.getOrderDetail());
        System.out.println(" Ukuran      : " + ukuran);
        System.out.println(" Jumlah      : " + jumlah);
        System.out.println(" Total Harga : Rp " + String.format("%.2f", totalHarga));
        System.out.println(" Sisa Stok   : " + item.getStok());
        System.out.println("==========================================");
    }

    public static void main(String[] args) {
        OrderController controller = new OrderController();
        CoffeeShopView view = new CoffeeShopView(controller);
        view.start();
    }
}