package bank;

import java.util.ArrayList;

public class Customer {
    private String firstName;
    private String lastName;
    // Müşterinin sahip olduğu hesapları tutan liste
    private ArrayList<Account> accounts;

    // Constructor
    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accounts = new ArrayList<>(); // Listeyi başlatıyoruz
    }

    // Müşteriye yeni bir hesap ekleme metodu
    public void addAccount(Account account) {
        this.accounts.add(account);
        // İstersen burada ekrana bilgi de yazdırabilirsin
        // System.out.println("Yeni hesap eklendi. Bakiye: " + account.getBalance());
    }

    // Bank sınıfında kullandığımız getName metodu
    public String getName() {
        return firstName + " " + lastName;
    }

    // Müşterinin hesaplarına ulaşmak istersek diye getter
    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}