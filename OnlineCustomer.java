import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

public class OnlineCustomer implements Serializable{
    private String userName;
    private String passWord;
    private boolean accountStatus;
    private  Account onlineCustomerAccount;
    private ContactInfo customerContactInfo;
    private static HashMap<String,Integer> customerAccount;
    private static File myFile;
    private FileWriter myWriter;

    
    //TODO: ADD a function which adds new customers to the file

    public OnlineCustomer(String userName, String passWord, boolean accountStatus, Account onlineCustomerAccount,
    		ContactInfo customerContactInfo) {
        this.userName = userName;
        this.passWord = passWord;
        this.accountStatus = accountStatus;
        this.onlineCustomerAccount = onlineCustomerAccount;
        this.customerContactInfo = customerContactInfo;
    }
    
    
    public static int getAccountNumber(String s) {
    	readData();
    	return customerAccount.get(s);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean isAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(boolean accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Account getOnlineCustomerAccount() {
    	return this.onlineCustomerAccount;
    }

    public void setOnlineCustomerAccount(Account onlineCustomerAccount) {
        this.onlineCustomerAccount = onlineCustomerAccount;
    }

    public ContactInfo getCustomerContactInfo() {
        return customerContactInfo;
    }

    public void setCustomerContactInfo(ContactInfo customerContactInfo) {
        this.customerContactInfo = customerContactInfo;
    }
    
    public void addOnlineCustomer(String user,String pass) {
		try {
			if(!myFile.createNewFile()) {
				myWriter = new FileWriter("CustomerAccount.txt", true);
				myWriter.write(user+","+pass);
				myWriter.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    
    public static void readData() {
  
    	String temp = "";
    	int counter = 0;
    	try {
    		myFile = new File("CustomerAccount.txt");
			Scanner in = new Scanner(myFile);
			while(in.hasNextLine()) {
				temp += in.nextLine() + ",";
				counter++;
			}
			
			String[] parser = parseLine(temp,counter);
			customerAccount = new HashMap<>();
			for(int i =0;i < parser.length-2;i+=2) {
				customerAccount.put(parser[i], Integer.valueOf(parser[i+1]));
			}
			
			in.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    private static String[] parseLine(String line,int counter) {
		String temp = "";
		int j = 0;
		String[] parse = new String[counter*2];
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