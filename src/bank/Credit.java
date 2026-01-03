package bank;

public class Credit extends Account {
    
    private double creditLimit; 

    // Account sınıfı 3 bilgi istiyordu, biz de o yüzden 3 bilgi + Limit istiyoruz
    public Credit(String accountNumber, String ownerName, double balance, double creditLimit) {
        // "super" ile babasının (Account) istediği 3 bilgiyi gönderiyoruz
        super(accountNumber, ownerName, balance); 
        this.creditLimit = creditLimit;
    }

    @Override
    public void withdraw(double amount) {
        // Mevcut bakiye + Kredi limiti, çekilmek istenen tutarı karşılıyor mu?
        if (balance + creditLimit >= amount) {
            // Senin Account sınıfında harika bir metod var: decreaseBalance
            // Hem parayı düşüyor hem de "addTransaction" ile geçmişe kaydediyor.
            // Onu kullanıyoruz:
            decreaseBalance(amount); 
            
            System.out.println("Kredi hesabından işlem yapıldı. Kalan Bakiye: " + balance);
        } else {
            System.out.println("Yetersiz Limit! (Bakiye + Kredi Limiti yetmiyor)");
        }
    }
}