package bank;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AccountTest {

    @Test
    void testParaYatirma() {
        // 1. HAZIRLIK
        // Account sınıfının istediği 3 bilgiyi veriyoruz: (No, İsim, Bakiye)
        Account hesap = new CheckingAccount("TR12345", "Test Kullanıcısı", 100.0); 

        // 2. EYLEM
        hesap.deposit(50.0); 

        // 3. KONTROL
        // 100 + 50 = 150 olmalı
        assertEquals(150.0, hesap.getBalance(), "Para yatırma işlemi yanlış hesaplandı!");
    }
    
    @Test
    void testParaCekme() {
        // 1. HAZIRLIK
        Account hesap = new CheckingAccount("TR67890", "Test Kullanıcısı", 200.0);
        
        // 2. EYLEM
        hesap.withdraw(50.0);
        
        // 3. KONTROL
        // 200 - 50 = 150 kalmalı
        assertEquals(150.0, hesap.getBalance(), "Para çekme işlemi yanlış hesaplandı!");
    }
    
    @Test
    void testYetersizBakiye() {
        // 1. HAZIRLIK
        Account hesap = new CheckingAccount("TR999", "Test", 50.0);
        
        // 2. EYLEM
        hesap.withdraw(100.0); // 50 var, 100 çekmeye çalışıyoruz
        
        // 3. KONTROL
        // Bakiye değişmemeli (Hala 50 kalmalı)
        assertEquals(50.0, hesap.getBalance(), "Yetersiz bakiyede para çekilmemeli!");
    }
}