package model;

public class MataKuliah {
    private String kode;
    private String nama;
    private int jumlahSks;
    private int semester;

    public MataKuliah() {
    }

    public MataKuliah(String kode, String nama, int jumlahSks, int semester) {
        this.kode = kode;
        this.nama = nama;
        this.jumlahSks = jumlahSks;
        this.semester = semester;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getJumlahSks() {
        return jumlahSks;
    }

    public void setJumlahSks(int jumlahSks) {
        this.jumlahSks = jumlahSks;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}