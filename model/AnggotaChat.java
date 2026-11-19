package latihandosen.model;
//(Colleague pada Mediator Pattern)
public class AnggotaChat {
    private String nama;
    private RuangObrolan ruangObrolan;

    public AnggotaChat(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setRuangObrolan(RuangObrolan ruangObrolan) {
        this.ruangObrolan = ruangObrolan;
    }

    public void kirimPesan(String pesan) {
        if (ruangObrolan != null) {
            ruangObrolan.kirimSiaran(this, pesan);
        }
    }

    public void terimaPesan(String pengirim, String pesan) {
        System.out.println("[" + nama + "] menerima dari " + pengirim + ": " + pesan);
    }
}