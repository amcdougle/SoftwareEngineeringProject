import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Saving extends Account{
	private double balance;
	private final double interestRate = 1.5;
	private int accountNumber;
	private int routingNumber;
	private File mySavingFile;
	private FileWriter mySavingWriter;
	private Scanner mySavingReader;
	private String sourceName;
	private String[] parseTransactions;
	private AccountType savingType;
	LocalDateTime timeStamp;

	public Saving(int accountNumber) {
		this.accountNumber = accountNumber;
		sourceName = Integer.valueOf(accountNumber) + "-Saving.txt";
		mySavingFile = new File(sourceName);
	}
	
	public String getType() {
		return this.savingType.getAccountTypeNum();
	}
	
	
	public void setRoutingNumber(int num) {
		this.routingNumber = num;
	}
	
	
	public double getBalance() {
		return this.balance;
	}
	
	private void setParseTranscations(String[] temp) {
        this.parseTransactions = temp;
    }
	
	public String[] getParseTransaction() {
		return this.parseTransactions;
	}
	
	public String[] displayTransaction() {
		String tempDisplay = "";
		int counter = 0;

		try {
			if (mySavingFile.createNewFile() == false) {
				this.mySavingReader = new Scanner(mySavingFile);
					while (this.mySavingReader.hasNextLine()) {
						counter++;
						tempDisplay += this.mySavingReader.nextLine()+",";
						
					}
					
					this.setParseTranscations(this.parseLine(tempDisplay, counter));
					if(getParseTransaction().length>0) {
						this.balance = Double.valueOf(this.getParseTransaction()[this.getParseTransaction().length-1-2]);
				
					}
				return this.getParseTransaction();

			} 
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new String[0];
	}
	
	public String[] parseLine(String line,int counter) {
		String temp = "";
		int j = 0;
		String[] parse = new String[counter*5];
		for(int i = 0;i < line.length();i++) {
			if (line.charAt(i) != ',') {
				temp += line.charAt(i);
			} else {
				parse[j] = temp;
				j++;
				temp = "";
			}
		}
		
		return parse;
	}

	public void appendTransaction(double amount, int AccountNumber, String transactionType, double balance) {
		try {
			mySavingWriter = new FileWriter(this.sourceName, true);
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
				timeStamp = LocalDateTime.now();
			String temp =String.valueOf(AccountNumber) + ","+dtf.format(timeStamp) + "," + String.valueOf(balance)
			+ "," + String.valueOf(amount) + "," + transactionType + "\n";
			System.out.println(temp);
			mySavingWriter.write(temp);
			mySavingWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	public int getaccountNumber() {
		return this.accountNumber;
	}
	
	public int routingNumber() {
		return this.routingNumber;
	}
	
	public File getmySavingFile() {
		return this.mySavingFile;
	}
	
	
	public void withdrawMoney(double amount) throws Exception {
		if (amount <= 0) {
			throw new Exception("Error incorrect amount!!!");
		} else if (balance > amount && balance - amount > 0) {
				balance -= amount;
				this.appendTransaction(amount, this.accountNumber, "withdraw", balance);
				
		}
	}
	
	public void sendMoney(double amount, AccountType accountType, int AccountNumber, Account personAccount) throws Exception {
		Account temp = personAccount.getAccount(accountType,AccountNumber);
		if(temp.getAccountType().equalsIgnoreCase("checking")) {
			Checking tempChecking = (Checking) temp;
			try {
				tempChecking.depositMoney(amount,tempChecking.getCheckingNumber());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(temp.getAccountType().equalsIgnoreCase("saving")) {
			Saving tempSaving = (Saving) temp;
			
			tempSaving.DepositMoney(amount,accountNumber);
		}
		
	}
	
	
	
	public void DepositMoney(double amount,int Account) throws Exception{
		if(amount <= 0) {
			throw new Exception ("Error incorrect amount!!!\nAmount needs to be greater than 0");
		}
		
		double tempValue = amount * (this.interestRate/100);
		this.balance += amount + tempValue;
		this.appendTransaction(amount, Account, "Deposit with " + Double.valueOf(interestRate) + "% interest", balance);
	}

	public int getRoutingNumber() {
		return this.routingNumber;
	}
	
	public int getSavingNumber() {
        return this.accountNumber;
    }
	
}
