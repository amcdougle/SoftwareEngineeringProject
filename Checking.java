import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Checking extends Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	private File myCheckingFile;
	private FileWriter myCheckingWriter;
	private Scanner myCheckingReader;
	private int accountNumber;
	private final int overdraftFee = 25;
	private int routingNumber;
	private double balance;
	private AccountType checkingType = AccountType.Checking;
	private String sourceName;
	private String[] parseTransactions;
	LocalDateTime timeStamp;


	public Checking(int aNum) {
		this.accountNumber = aNum;
		this.sourceName = Integer.valueOf(accountNumber) + "-Checking.txt";
		myCheckingFile = new File(sourceName);
	}
	
	public String getSourceName() {
		return this.sourceName;
	}

	public double getBalance() {
		return this.balance;
	}
	
	
	public void setBalance(double d) {
		this.balance = d;
	}
	
	public void setRoutingNumber(int num) {
		this.routingNumber = num;
	}
	public String[] getParseTransactions() {
		return this.parseTransactions;
	}
	
	private void setParseTranscations(String[] temp) {
		this.parseTransactions = temp;
	}

	public boolean overDraft() {
		if (this.balance < 0) {
			return true;
		}
		return false;
	}
	
	

	public String[] displayTransaction() {
		String tempDisplay = "";
		int counter = 0;

		try {
			if (myCheckingFile.createNewFile() == false) {
				this.myCheckingReader = new Scanner(myCheckingFile);
					while (this.myCheckingReader.hasNextLine()) {
						counter++;
						tempDisplay += this.myCheckingReader.nextLine()+",";
						
					}
					
					
					
					this.setParseTranscations(this.parseLine(tempDisplay, counter));
					
					
					if(getParseTransactions().length>0) {
						this.balance = Double.valueOf(this.getParseTransactions()[this.getParseTransactions().length-1-2]);
				
					}
				
				return this.getParseTransactions();

			} 
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new String[0];
	}
	
	private String[] parseLine(String line,int counter) {
		String temp = "";
		int j = 0;
		
		String[] parse = new String[counter*5];
		for(int i = 0;i < line.length();i++) {
			if (line.charAt(i) != ',') {
				temp += String.valueOf(line.charAt(i));
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
				myCheckingWriter = new FileWriter(this.sourceName, true);
				  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
				timeStamp = LocalDateTime.now();
				
			String temp = String.valueOf(AccountNumber) + ","+dtf.format(timeStamp).toString() + "," + String.valueOf(balance)
			+ "," + String.valueOf(amount) + "," + transactionType + "\n";
			myCheckingWriter.write(temp);
			this.myCheckingWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getType() {
		return this.checkingType.getAccountTypeNum();
	}

	public void sendMoney(double amount, AccountType accountType, int AccountNumber, Account personAccount) {
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
			
			try {
				tempSaving.DepositMoney(amount,this.getAccountNo());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public void depositMoney(double amount, int accountNumber) throws Exception{
		if (amount < 0) {
			throw new Exception("ERROR incorrect amount!!");
		}
		
		this.balance += amount;
		this.appendTransaction(amount, accountNumber, "DEPOSIT", balance);

	}

	public void withdrawMoney(double amount) throws Exception {
		if (amount < 0) {
			throw new Exception("Error incorrect amount!!!");
		} else if (amount > balance && balance - amount < -10.00) {
			throw new Exception("Insufficient funds");
		} else if ((balance - amount <= 0 && balance - amount <= -10)) {
			this.balance -= amount;
			this.balance -= this.overdraftFee;
		} else if (balance >= amount) {
			this.balance -= amount;
		}
		this.appendTransaction(amount, this.getCheckingNumber(), "WITHDRAW", balance);

		

	}

	public int getCheckingNumber() {
		return this.accountNumber;
	}
	
	public int getRoutingNumber() {
	return this.routingNumber;
	}
	
}
