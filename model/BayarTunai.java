package latihandosen.model;

import java.util.ArrayList;
import java.util.List;
import latihandosen.exception.PembayaranGagalException;
//(Concrete Strategy 1)
public class BayarTunai implements StrategiBayar {
    private double saldoTunai;

    public BayarTunai(double saldoTunai) {
        this.saldoTunai = saldoTunai;
    }

    @Override
    public String getNamaMetode() {
        return "Tunai (Saldo: Rp" + saldoTunai + ")";
    }

    @Override
    public boolean canPay(double amount) {
        return amount >= 100 && amount <= 1000000;
    }

    @Override
    public boolean validate(double amount) {
        return saldoTunai >= amount;
    }

    @Override
    public List<String> getRequiredAuthorizations() {
        return new ArrayList<>();
    }

    @Override
    public void pay(double amount) throws PembayaranGagalException {
        if (!validate(amount)) {
            throw new PembayaranGagalException("Saldo tunai tidak cukup.");
        }
        saldoTunai = saldoTunai - amount;
        System.out.println("Pembayaran tunai berhasil. Sisa saldo: Rp" + saldoTunai);
    }
}