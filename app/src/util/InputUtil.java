package pbo.util;

import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public static int inputInt(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("Input tidak valid. Masukkan angka bulat: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public static double inputDouble(String message) {
        System.out.print(message);
        while (!scanner.hasNextDouble()) {
            System.out.print("Input tidak valid. Masukkan angka: ");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    public static Ukuran inputUkuran(String message) {
        System.out.print(message);
        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();
            try {
                return Ukuran.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.print("Input tidak valid. Masukkan ukuran yang benar (KECIL, SEDANG, BESAR): ");
            }
        }
    }
}