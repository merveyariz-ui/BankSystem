package bank;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Önce bankamızı kuralım
        System.out.print("Banka adını giriniz: ");
        String bankName = scanner.nextLine();
        Bank myBank = new Bank(bankName);
        
        // İşlemler için geçici değişkenler
        Customer activeCustomer = null;
        Account activeAccount = null;

        while (true) {
            System.out.println("\n--- " + myBank.getBankName() + " ANA MENÜ ---");
            System.out.println("1. Yeni Müşteri Ekle");
            System.out.println("2. Müşteri Seç (İşlem yapmak için)");
            System.out.println("3. Hesap Oluştur (Seçili Müşteri İçin)");
            System.out.println("4. Para Yatır");
            System.out.println("5. Para Çek");
            System.out.println("6. Müşterileri Listele");
            System.out.println("7. Para Transferi");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Enter karakterini temizlemek için (Buffer temizliği)

            switch (choice) {
                case 1: // Müşteri Ekle
                    System.out.print("Müşteri Adı: ");
                    String name = scanner.nextLine();
                    System.out.print("Müşteri Soyadı: ");
                    String surname = scanner.nextLine();
                    
                    Customer newCust = new Customer(name, surname);
                    myBank.addCustomer(newCust);
                    System.out.println("Müşteri başarıyla eklendi!");
                    
                    // Eklediğimiz kişiyi hemen aktif müşteri yapalım
                    activeCustomer = newCust; 
                    break;

                case 2: // Müşteri Seç (Basitlik olsun diye son ekleneni seçiyoruz şimdilik)
                     // Not: İleride isme göre arama eklenebilir.
                    System.out.println("Şu an aktif olan müşteri: " + 
                            (activeCustomer != null ? activeCustomer.getName() : "YOK"));
                    break;

                case 3: // Hesap Oluştur
                    if (activeCustomer == null) {
                        System.out.println("Önce bir müşteri eklemelisiniz (Seçenek 1)!");
                        break;
                    }
                    
                    System.out.print("Hesap No Giriniz (Örn: TR123): ");
                    String accNo = scanner.nextLine();
                    
                    System.out.print("Başlangıç Bakiyesi: ");
                    double balance = scanner.nextDouble();
                    
                    System.out.print("Kredi Limiti: ");
                    double limit = scanner.nextDouble();
                    
                    // Credit sınıfını kullanarak hesap oluşturuyoruz
                    // Not: Senin Credit sınıfın (AccNo, OwnerName, Balance, Limit) istiyor
                    Credit newCreditAccount = new Credit(accNo, activeCustomer.getName(), balance, limit);
                    
                    activeCustomer.addAccount(newCreditAccount);
                    activeAccount = newCreditAccount; // Şu an üzerinde işlem yapılan hesap bu olsun
                    
                    System.out.println("Kredi Hesabı oluşturuldu ve müşteriye tanımlandı.");
                    break;

                case 4: // Para Yatır
                    if (activeAccount == null) {
                        System.out.println("Aktif bir hesap yok! Önce hesap oluşturun (Seçenek 3).");
                        break;
                    }
                    System.out.print("Yatırılacak Tutar: ");
                    double depositAmount = scanner.nextDouble();
                    activeAccount.deposit(depositAmount);
                    break;

                case 5: // Para Çek
                    if (activeAccount == null) {
                        System.out.println("Aktif bir hesap yok! Önce hesap oluşturun (Seçenek 3).");
                        break;
                    }
                    System.out.print("Çekilecek Tutar: ");
                    double withdrawAmount = scanner.nextDouble();
                    activeAccount.withdraw(withdrawAmount);
                    break;

                case 6: // Listele
                    myBank.listCustomers();
                    break;
                    
                case 7: // Para Transferi
                    if (activeAccount == null) {
                        System.out.println("Transfer yapmak için önce bir hesaba giriş yapmalısınız (Seçenek 3)!");
                        break;
                    }
                    
                    System.out.println("--- Kime para göndereceksiniz? ---");
                    myBank.listCustomers(); // Müşterileri listeliyoruz ki sırasını görelim
                    
                    System.out.print("Alıcı Müşterinin Sıra Numarasını Giriniz (0, 1, 2...): ");
                    int receiverIndex = scanner.nextInt();
                    
                    // Bankadan o müşteriyi buluyoruz
                    Customer receiver = myBank.getCustomer(receiverIndex);
                    
                    if (receiver != null) {
                        // Basitlik olsun diye alıcının İLK hesabını seçiyoruz
                        if (receiver.getAccounts().size() > 0) {
                            Account receiverAccount = receiver.getAccounts().get(0);
                            
                            System.out.print("Gönderilecek Tutar: ");
                            double transferAmount = scanner.nextDouble();
                            
                            // Account sınıfındaki transfer metodunu kullanıyoruz
                            boolean success = activeAccount.transfer(receiverAccount, transferAmount);
                            if(success) {
                                System.out.println("Transfer Başarılı!");
                            }
                        } else {
                            System.out.println("Bu müşterinin aktif bir hesabı yok.");
                        }
                    } else {
                        System.out.println("Hatalı müşteri seçimi!");
                    }
                    break;

                case 0:
                    System.out.println("Sistemden çıkılıyor. İyi günler!");
                    scanner.close();
                    return; // Programı sonlandırır

                default:
                    System.out.println("Geçersiz seçim! Tekrar deneyin.");
            }
        }
    }
}