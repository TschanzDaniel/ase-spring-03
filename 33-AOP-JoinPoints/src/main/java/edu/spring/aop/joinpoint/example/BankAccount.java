package edu.spring.aop.joinpoint.example;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("bankAccount")
@Scope("prototype")
public class BankAccount {

    private int accountID = 0;
    private double balance = 0;

    public void insertMoney(double amount) {
        setBalance(getBalance() + amount);
        System.out.println("Es wurden " + amount + "€ auf " + accountID + " eingezahlt");
    }


    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}
	
    @Override
    public boolean equals(Object obj) {
        return ((BankAccount)obj).accountID == accountID;
    }

}
