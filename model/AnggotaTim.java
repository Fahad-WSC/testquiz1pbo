package manajemenproyek.model;

public class AnggotaTim {
    private String nama;
    private String peran;
    private PusatNotifikasiTim pusatNotifikasi;

    public AnggotaTim(String nama, String peran) {
        this.nama = nama;
        this.peran = peran;
    }

    public String getNama() {
        return nama;
    }

    public String getPeran() {
        return peran;
    }

    public void setPusatNotifikasi(PusatNotifikasiTim pusatNotifikasi) {
        this.pusatNotifikasi = pusatNotifikasi;
    }

    public void kirimPesan(String pesan) {
        if (pusatNotifikasi != null) {
            pusatNotifikasi.kirimSiaran(this, pesan);
        }
    }

    public void terimaPesan(String pengirim, String pesan) {
        System.out.println("[" + nama + " (" + peran + ")] menerima dari " + pengirim + ": " + pesan);
    }
}