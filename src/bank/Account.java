package bank;

import java.security.PublicKey;


public abstract class Account implements Transferable{
	private String accountNumber;
	private double balance;
	private String ownerName;

	public Account (String accountNumber, String ownerName, double balance) {
		this.accountNumber = accountNumber;
		this.ownerName = ownerName;
		this.balance = balance;
	}
	
	public void deposit (double amount) {
		if(amount > 0) {
			this.balance += amount;
			System.out.println(amount + "TL yatırıldı. Yeni bakiye: " + this.balance);
			
		}
	}
	
	public abstract void withdraw(double amount);
	@Override
	public boolean transfer (Account toAccount, double amount) {
		if(this.balance >= amount) {
			this.withdraw(amount);
			toAccount.deposit(amount);
			System.out.println("Transfer başarılı.");
			return true;
		}
		System.out.println("Yetersiz bakiye.");
		return false;
	}
		public double getBalance() {
			return balance;
		}
	}

