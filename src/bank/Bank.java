package bank;

import java.util.ArrayList; // Müşterileri dinamik bir listede tutmak için

public class Bank {
    private String bankName;
    private ArrayList<Customer> customers; // Müşteri listesi

    // Constructor (Kurucu Metot)
    public Bank(String bankName) {
        this.bankName = bankName;
        this.customers = new ArrayList<>(); // Listeyi başlatıyoruz
    }

    // Yeni müşteri ekleme metodu
    public void addCustomer(Customer customer) {
        this.customers.add(customer);
        System.out.println(customer.getName() + " isimli müşteri " + this.bankName + "'a eklendi.");
    }

    // Müşterileri listeleme metodu
    public void listCustomers() {
        System.out.println("--- " + this.bankName + " Müşteri Listesi ---");
        for (Customer c : customers) {
            System.out.println("- " + c.getName()); // Customer sınıfında getName() olduğunu varsayıyoruz
        }
    }

    // Getter
    public String getBankName() {
        return bankName;
    }
}