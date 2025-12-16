package bank;

public class CheckingAccount extends Account {
    public CheckingAccount(String accountNumber, String ownerName, double balance) {
        super(accountNumber, ownerName, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (getBalance() >= amount) {
            System.out.println("Vadesiz hesaptan " + amount + " TL çekildi.");
            // Gerçek bakiye düşme işlemi Account sınıfı üzerinden yapılır.
        } else {
            System.out.println("Yetersiz bakiye.");
        }
    }
}