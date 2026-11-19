package latihandosen.model;
//(Concrete Strategy 2 - Menambah Metode Baru Sesuai OCP)
import java.util.Arrays;
import java.util.List;
import latihandosen.exception.PembayaranGagalException;

public class BayarEWallet implements StrategiBayar {
    private String nomorHp;
    private double saldo;
    private String pinBenar;

    public BayarEWallet(String nomorHp, double saldo, String pinBenar) {
        this.nomorHp = nomorHp;
        this.saldo = saldo;
        this.pinBenar = pinBenar;
    }

    public String getPinBenar() {
        return pinBenar;
    }

    @Override
    public String getNamaMetode() {
        return "E-Wallet " + nomorHp + " (Saldo: Rp" + saldo + ")";
    }

    @Override
    public boolean canPay(double amount) {
        return amount >= 1000 && amount <= 5000000;
    }

    @Override
    public boolean validate(double amount) {
        return saldo >= amount;
    }

    @Override
    public List<String> getRequiredAuthorizations() {
        return Arrays.asList("PIN", "OTP");
    }

    @Override
    public void pay(double amount) throws PembayaranGagalException {
        if (!validate(amount)) {
            throw new PembayaranGagalException("Saldo E-Wallet tidak cukup.");
        }
        saldo = saldo - amount;
        System.out.println("Pembayaran E-Wallet berhasil. Sisa saldo: Rp" + saldo);
    }
}