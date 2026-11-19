package manajemenproyek.model;

public interface BisaDilaporkan {
    int POIN_DASAR_LAPORAN = 10;

    private String formatFooterLaporan() {
        return "Poin pelaporan standar: " + POIN_DASAR_LAPORAN;
    }

    default void cetakHeaderLaporan(String judul) {
        System.out.println("=== LAPORAN: " + judul + " (" + formatFooterLaporan() + ") ===");
    }

    String buatRingkasanStatus();
}