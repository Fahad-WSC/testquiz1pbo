package belanjaleveltinggi.model;

import belanjaleveltinggi.exception.KuponTidakValidException;

public class DiskonPotonganTetap implements StrategiDiskon {
    private String kodeKupon;
    private double nominalPotongan;

    public DiskonPotonganTetap(String kodeKupon, double nominalPotongan) {
        this.kodeKupon = kodeKupon;
        this.nominalPotongan = nominalPotongan;
    }

    @Override
    public String getNamaKupon() {
        return kodeKupon + " (Potongan Rp" + nominalPotongan + ")";
    }

    @Override
    public boolean bisaDipakai(double subtotal) {
        return subtotal > nominalPotongan;
    }

    @Override
    public double hitungTotalSetelahDiskon(double subtotal) throws KuponTidakValidException {
        if (!bisaDipakai(subtotal)) {
            throw new KuponTidakValidException("Subtotal harus lebih besar dari potongan Rp" + nominalPotongan);
        }
        return subtotal - nominalPotongan;
    }
}