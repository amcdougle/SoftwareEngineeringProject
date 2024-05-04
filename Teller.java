import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Teller implements Serializable{
    private Account customerAccount;
    private Double tellersRegisterMoney;
    private File myCheckingFile;
    private FileWriter myCheckingWriter;
    private File mySavingFile;
    private FileWriter mySavingWriter;
    private Scanner mySavingReader;
    private Scanner scanner = new Scanner(System.in);
    private String sourceName;
    private double balance;
    private int accountNumber;
    private AccountType checkingType;
    LocalDateTime timeStamp;
    private AccountType savingType;
    private boolean isFreeze = false; 
    private ContactInfo TellerInfo;
    
    public Teller(ContactInfo ci) {
    	TellerInfo = ci;
    }

    public void depositCheck(Double amount, int accountNumber) {
        if (checkingType == AccountType.Checking) {
            Checking account = (Checking) customerAccount.getAccount(checkingType, accountNumber);
            account.appendTransaction(amount, accountNumber, "Deposit",balance);
            return;
        }
        if (checkingType == AccountType.Saving) {
            Saving account = (Saving) customerAccount.getAccount(checkingType, accountNumber);
            account.appendTransaction(amount, accountNumber, "Deposit", balance);
            return;
        }

    }

    public void withdrawMoney(double amount) throws Exception {
        if (amount < 0) {
            throw new Exception("Error incorrect amount!!!");
        } else if (amount > balance && balance - amount > -10.00) {
            throw new Exception("Insufficient funds");
        } else if ((balance - amount < 0 && balance - amount >= -10)) {
            this.balance -= amount;
        } else if (balance > amount) {
            this.balance -= amount;
        }
        Checking account = (Checking) customerAccount.getAccount(checkingType, accountNumber);
        account.appendTransaction(amount, this.getCheckingNumber(), "WITHDRAW", balance);

    }

    public void depositMoney(double amount, int accountNumber) throws Exception {
        if (amount < 0) {
            throw new Exception("ERROR incorrect amount!!");
        }
        this.balance += amount;
        Checking account = (Checking) customerAccount.getAccount(checkingType, accountNumber);
        account.appendTransaction(amount, accountNumber, "DEPOSIT", balance);
    }


    public void openAccount(AccountType Type) {
    	
    	System.out.println("Enter Account No: ");
    	
    	int accountNo = scanner.nextInt();
    	
    	System.out.println("Enter Account pin: ");
    	
    	int accountPin = scanner.nextInt();
    	
    	customerAccount = new Account(); 
    	
    	customerAccount.setAccountDetails(accountNo, accountPin);
    	
        if (checkingType == AccountType.Checking) {
        	
        	System.out.println("Enter Account No: ");
        	accountNumber = scanner.nextInt();
        	
        	System.out.println("Enter routing number: ");
        	int routing = scanner.nextInt();
        	
            Checking checkingAccount = new Checking(accountNumber);
            checkingAccount.setRoutingNumber(routing);
                 
            customerAccount.AddAccount(checkingAccount);
            return;
            
        }
        
        if (savingType == AccountType.Saving) {
        	
        	System.out.println("Enter Account No: ");
        	accountNumber = scanner.nextInt();
        	
        	System.out.println("Enter routing number: ");
        	int routing = scanner.nextInt();
            
        	Saving savingAccount = new Saving(accountNumber);
            savingAccount.setRoutingNumber(routing);
            
            customerAccount.AddAccount(savingAccount);
            return;
        }
    }
    
//    public void freezeAccount(int number) { 
//    	
//    	//find account with given account number and change the status of it. 
//    	
//    	
//    	 
//    }
    
	/*
	 * public void terminateAccount(int number) { //find account with given account
	 * number and delete it if (checkingType == AccountType.Checking) {
	 * customerAccount.getAccount(checkingType, accountNumber).delete();//delete
	 * method from account class
	 * 
	 * return; } if (checkingType == AccountType.Saving) {
	 * customerAccount.getAccount(checkingType, accountNumber).delete();
	 * 
	 * return; } }
	 */

    public Account getCustomerAccount() {
        return customerAccount;
    }

    public int getCheckingNumber() {
        return this.accountNumber;
    }
    
    

}