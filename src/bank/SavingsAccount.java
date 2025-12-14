package bank;

public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountNumber, String ownerName, double balance, double interestRate) {
        super(accountNumber, ownerName, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) {
        if (getBalance() >= amount) {
            System.out.println("Vadeli hesaptan " + amount + " TL çekildi. (Faiz bozuldu)");
            // Not: Bakiye düşme işlemi Account sınıfı üzerinden yapılır.
        } else {
            System.out.println("Yetersiz Bakiye.");
        }
    }

    public void calculateInterest() {
        double interest = getBalance() * interestRate;
        deposit(interest);
        System.out.println("Faiz hesaplandı: " + interest);
    }
}