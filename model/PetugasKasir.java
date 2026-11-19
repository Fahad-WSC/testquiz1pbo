package belanjaleveltinggi.model;

public class PetugasKasir {
    private String nama;
    private MediatorKasir mediator;

    public PetugasKasir(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setMediator(MediatorKasir mediator) {
        this.mediator = mediator;
    }

    public void beritahuTim(String pesan) {
        if (mediator != null) {
            mediator.kirimInfo(this, pesan);
        }
    }

    public void terimaInfo(String pengirim, String pesan) {
        System.out.println("[" + nama + "] menerima info dari " + pengirim + ": " + pesan);
    }
}