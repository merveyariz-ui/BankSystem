package bank;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Account implements Transferable {
    
    private String accountNumber;
    private double balance;
    private String ownerName;
    private List<Transaction> transactionHistory; // Geçmiş listesi

    public Account(String accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
        addTransaction("Hesap Açılışı", balance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            addTransaction("Para Yatırma", amount);
            System.out.println(amount + " TL yatırıldı. Yeni bakiye: " + this.balance);
        }
    }

    public abstract void withdraw(double amount);

    @Override
    public boolean transfer(Account toAccount, double amount) {
        if (this.balance >= amount) {
            this.withdraw(amount); 
            toAccount.deposit(amount);
            addTransaction("Transfer Gönderildi -> " + toAccount.getAccountNumber(), -amount);
            return true;
        }
        System.out.println("Yetersiz bakiye.");
        return false;
    }

    // İşlem geçmişine ekleme yapan metod
    protected void addTransaction(String type, double amount) {
        Transaction t = new Transaction(new Date(), type, amount);
        transactionHistory.add(t);
    }
    
    // Geçmişi ekrana yazdıran metod
    public void printHistory() {
        System.out.println("\n--- " + ownerName + " (" + accountNumber + ") Hesap Özeti ---");
        for (Transaction t : transactionHistory) {
            System.out.println(t);
        }
        System.out.println("--------------------------------------------------");
    }

    public double getBalance() {
        return balance;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
 // Alt sınıfların (Checking, Savings, Credit) bakiyeyi değiştirebilmesi için:
    protected void decreaseBalance(double amount) {
        this.balance -= amount;
        addTransaction("Para Çekme", -amount); // Otomatik kayıt
    }
}