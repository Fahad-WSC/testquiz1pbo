package model;

public class Ruangan {
    private String id;
    private String nama;
    private int lokasiLantai;
    private int kapasitas;

    public Ruangan() {
    }

    public Ruangan(String id, String nama, int lokasiLantai, int kapasitas) {
        this.id = id;
        this.nama = nama;
        this.lokasiLantai = lokasiLantai;
        this.kapasitas = kapasitas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getLokasiLantai() {
        return lokasiLantai;
    }

    public void setLokasiLantai(int lokasiLantai) {
        this.lokasiLantai = lokasiLantai;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }
}