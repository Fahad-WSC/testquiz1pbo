package belanjaleveltinggi.model;

import belanjaleveltinggi.exception.KuponTidakValidException;

public class DiskonPersen implements StrategiDiskon {
    private String kodeKupon;
    private double persenDiskon;

    public DiskonPersen(String kodeKupon, double persenDiskon) {
        this.kodeKupon = kodeKupon;
        this.persenDiskon = persenDiskon;
    }

    @Override
    public String getNamaKupon() {
        return kodeKupon + " (Diskon " + persenDiskon + "%)";
    }

    @Override
    public boolean bisaDipakai(double subtotal) {
        return subtotal >= 20000.0;
    }

    @Override
    public double hitungTotalSetelahDiskon(double subtotal) throws KuponTidakValidException {
        if (!bisaDipakai(subtotal)) {
            throw new KuponTidakValidException("Kupon " + kodeKupon + " minimal belanja Rp20000.");
        }
        double potongan = subtotal * (persenDiskon / 100.0);
        return subtotal - potongan;
    }
}