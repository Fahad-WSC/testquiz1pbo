package latihandosen.model;
//(Strategy Pattern + canPay + Otorisasi)
import java.util.List;
import latihandosen.exception.PembayaranGagalException;

public interface StrategiBayar {
    String getNamaMetode();

    boolean canPay(double amount);

    boolean validate(double amount);

    List<String> getRequiredAuthorizations();

    void pay(double amount) throws PembayaranGagalException;
}