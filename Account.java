
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	private ArrayList<Account> userAccount;
	private int accountNumber;
	private int accountPin;
	public AccountType type;
	static File myAccountFile;
	static Map<Integer,Integer> pin;
	static Map<Integer,ArrayList<String>> accounts;
	FileWriter myAccountWriter;

	
	
	
	public Account() {
		userAccount = new ArrayList<Account>();
	}
	
	 
	public String getAccountType() {
		return this.type.AccountTypeStr;
	}
	
	public String getDisplayAccounts() {
		String temp = "";
		for(int i =0;i < userAccount.size();i++) {
			if(userAccount.get(i).getAccountType().equalsIgnoreCase("Checking")) {
				Checking cb = (Checking) userAccount.get(i);
				temp += cb.getCheckingNumber() + " " + cb.getType() + " " + cb.getBalance() + "\n";
			} else if(userAccount.get(i).getAccountType().equalsIgnoreCase("Saving")) {
				Saving sav = (Saving) userAccount.get(i);
				temp += sav.getaccountNumber() + " " + sav.getAccountType() + " " + sav.getBalance() + "\n";
			}else if(userAccount.get(i).getAccountType().equalsIgnoreCase("CreditCard")) {
				//TODO: Need the credit card account
			}
		}
		
		return temp;
	}
	
	public int getAccountNo() {
		return this.accountNumber;
	}
	
	public ArrayList<String> getAccounts(){
		readAccounts();
		return accounts.get(this.getAccountNo());
	}
	
	public boolean validateAccount(int accountNum, int pin) {
		if(accountNum == this.accountNumber && pin == this.accountPin) {
			return true;
		}
		
		return false;
	}
	
	public void AddAccount(Account type) {
		userAccount.add(type);
	}
	
	
	public Account getAccount(AccountType AT,int number) {
		ArrayList<Account> acc = this.getInstanceAccount(AT);
		
		if(AT.getAccountTypeNum().equalsIgnoreCase("checking")) {
			for(int i = 0;i < acc.size();i++) {
				Checking temp = (Checking) acc.get(i);
				if(temp.getAccountNo()==number) {
					return temp;
				}
			}
		}else if(AT.getAccountTypeNum().equalsIgnoreCase("saving")) {
			for(int i = 0;i < acc.size();i++) {
				Saving temp = (Saving) acc.get(i);
				if(temp.getAccountNo()==number) {
					return temp;
				}
			}
		}
		return new Account();
	}
	
	private ArrayList<Account> getInstanceAccount(AccountType AT) {
		ArrayList<Account> tempAccount= new ArrayList<>();
		for(int i= 0;i < userAccount.size();i++) {
			if(userAccount.get(i).getAccountType().equalsIgnoreCase(AT.getAccountTypeNum())) {
				tempAccount.add(userAccount.get(i));
			}
		}
		
		return tempAccount;
	}
	
	public void setAccountDetails(int AN, int AP) {
		this.accountNumber = AN;
		this.accountPin = AP;
	} 
	
	 public static void readPins() {
		  
	    	String temp = "";
	    	int counter = 0;
	    	try {
	    		myAccountFile = new File("AccountPin.txt");
				Scanner in = new Scanner(myAccountFile);
				while(in.hasNextLine()) {
					temp += in.nextLine() + ",";
					counter++;
				}
				
				String[] parser = parseLine(temp,counter,2);
				pin = new HashMap<>();
				for(int i =0;i < parser.length-2;i+=2) {
					pin.put(Integer.valueOf(parser[i]), Integer.valueOf(parser[i+1]));
				}

				in.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	 
	 public static void readAccounts() {
		 String temp = "";
	    	int counter = 0;
	    	try {
	    		myAccountFile = new File("TypeOfAccounts.txt");
				Scanner in = new Scanner(myAccountFile);
				while(in.hasNextLine()) {
					temp += in.nextLine() + ",";
					counter++;
				}
				
					String[] arr = parseLine(temp,counter,3);
					accounts = new HashMap<Integer,ArrayList<String>>();
				for(int i = 0; i < arr.length-3;i+=3) {
					ArrayList<String> tList = new ArrayList<>();
					tList.add(arr[i+1]);
					tList.add(arr[i+2]);
					accounts.put(Integer.valueOf(arr[i]), tList);
				}

				in.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	    
	    private static String[] parseLine(String line,int counter,int size) {
			String temp = "";
			int j = 0;
			String[] parse = new String[counter*size];
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
	}
	
