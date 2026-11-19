package belanjaleveltinggi.model;

import belanjaleveltinggi.exception.KuponTidakValidException;

public interface StrategiDiskon {
    String getNamaKupon();

    boolean bisaDipakai(double subtotal);

    double hitungTotalSetelahDiskon(double subtotal) throws KuponTidakValidException;
}