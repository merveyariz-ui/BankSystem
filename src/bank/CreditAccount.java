package bank;

public class CreditAccount extends Account {
    
    private double creditLimit; // Kredi Limiti (Örn: 5000 TL)

    public CreditAccount(String accountNumber, String ownerName, double balance, double creditLimit) {
        super(accountNumber, ownerName, balance);
        this.creditLimit = creditLimit;
    }

    @Override
    public void withdraw(double amount) {
        // Mevcut bakiye + Kredi limiti, çekilmek istenen tutarı karşılıyor mu?
        if (getBalance() + creditLimit >= amount) {
            // Bakiyeyi güncellemek için Account sınıfında balance protected olmalıydı 
            // veya azaltma metodu olmalıydı. 
            // Şimdilik Account'taki withdraw abstract olduğu için mantığı buraya kuruyoruz
            // Ancak Account sınıfındaki 'balance' private olduğu için doğrudan erişemiyoruz.
            // BU NEDENLE: Account sınıfına küçük bir "protected" metod eklememiz gerekecek.
            // (Aşağıda Account sınıfını güncellemeni isteyeceğim).
            
            decreaseBalance(amount); // Bunu birazdan Account'a ekleyeceğiz.
            System.out.println("Kredi hesabından " + amount + " TL çekildi. (Limit kullanıldı)");
        } else {
            System.out.println("Yetersiz Limit! (Bakiye + Kredi Limiti yetmiyor)");
        }
    }
}