package bank;
import java.util.Date;

// Bu sınıf sadece işlem bilgilerini saklar
public class Transaction {
    private Date date;
    private String type; // İşlem türü (Para Yatırma vb.)
    private double amount; // Tutar

    public Transaction(Date date, String type, double amount) {
        this.date = date;
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return date + " | " + type + " | " + amount + " TL";
    }
}