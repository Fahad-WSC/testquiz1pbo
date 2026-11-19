import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//tahap 2 view
public abstract class DataView<T> {
    private List<T> records;
    protected static final Scanner scanner = new Scanner(System.in);

    public DataView() {
        this.records = new ArrayList<>();
    }

    public void printAll() {
        String[] headers = getFields();
        System.out.println("\n--- DAFTAR DATA ---");
        if (records.isEmpty()) {
            System.out.println("Belum ada data.");
            return;
        }

        for (int i = 0; i < records.size(); i++) {
            T item = records.get(i);
            String[] values = convertModelToValues(item);
            System.out.println("Data ke-" + (i + 1) + ":");
            for (int j = 0; j < headers.length; j++) {
                String val = "";
                if (j < values.length && values[j] != null) {
                    val = values[j];
                }
                System.out.println("  " + headers[j] + ": " + val);
            }
        }
    }

    public void printByPk(String primaryKey) {
        String[] headers = getFields();
        boolean found = false;

        for (int i = 0; i < records.size(); i++) {
            T item = records.get(i);
            String pkValue = getPrimaryKeyValue(item);
            if (pkValue != null && pkValue.equals(primaryKey)) {
                String[] values = convertModelToValues(item);
                System.out.println("\n--- DATA DITEMUKAN ---");
                for (int j = 0; j < headers.length; j++) {
                    String val = "";
                    if (j < values.length && values[j] != null) {
                        val = values[j];
                    }
                    System.out.println("  " + headers[j] + ": " + val);
                }
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Data dengan primary key '" + primaryKey + "' tidak ditemukan.");
        }
    }

    public void showInsertForm() {
        String[] fields = getFields();
        String[] values = new String[fields.length];

        System.out.println("\n--- TAMBAH DATA ---");
        for (int i = 0; i < fields.length; i++) {
            System.out.print("Masukkan " + fields[i] + ": ");
            values[i] = scanner.nextLine();
        }

        T newModel = convertValuesToModel(values);
        records.add(newModel);
        System.out.println("Data berhasil ditambahkan.");
    }

    public void showUpdateForm() {
        System.out.print("\nMasukkan primary key data yang akan diubah: ");
        String targetPk = scanner.nextLine();

        int targetIndex = -1;
        for (int i = 0; i < records.size(); i++) {
            T item = records.get(i);
            String pkValue = getPrimaryKeyValue(item);
            if (pkValue != null && pkValue.equals(targetPk)) {
                targetIndex = i;
                break;
            }
        }

        if (targetIndex == -1) {
            System.out.println("Data dengan primary key '" + targetPk + "' tidak ditemukan.");
            return;
        }

        String[] fields = getFields();
        String[] values = new String[fields.length];

        System.out.println("--- UBAH DATA ---");
        for (int i = 0; i < fields.length; i++) {
            System.out.print("Masukkan " + fields[i] + " baru: ");
            values[i] = scanner.nextLine();
        }

        T updatedModel = convertValuesToModel(values);
        records.set(targetIndex, updatedModel);
        System.out.println("Data berhasil diubah.");
    }

    public void promptDelete() {
        System.out.print("\nMasukkan primary key data yang akan dihapus: ");
        String targetPk = scanner.nextLine();

        int targetIndex = -1;
        for (int i = 0; i < records.size(); i++) {
            T item = records.get(i);
            String pkValue = getPrimaryKeyValue(item);
            if (pkValue != null && pkValue.equals(targetPk)) {
                targetIndex = i;
                break;
            }
        }

        if (targetIndex == -1) {
            System.out.println("Data dengan primary key '" + targetPk + "' tidak ditemukan.");
            return;
        }

        records.remove(targetIndex);
        System.out.println("Data berhasil dihapus.");
    }

    protected abstract String getPrimaryKeyValue(T data);

    protected abstract String[] getFields();

    protected abstract T convertValuesToModel(String[] values);

    protected abstract String[] convertModelToValues(T data);
}