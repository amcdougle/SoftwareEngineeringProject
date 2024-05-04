import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

public class ContactInfo implements Serializable{
    private String age;
    private Name fullName;
    private Address address;
    private Phone phone;
    private static HashMap<String,Integer> customerContactInfo;
    private static File myFile;
    private FileWriter myWriter;

    public ContactInfo(String age, Name fullName, Address address,Phone phone) {
        this.age = age;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
    }

    public ContactInfo() {
        this.age = "";
        this.fullName = new Name();
        this.address = new Address();
        this.phone = new Phone();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Name getFullName() {
        return fullName;
    }

    public void setFullName(Name fullName) {
        this.fullName = fullName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }



    @Override
    public String toString() {
        return "Full name: " + this.fullName + "\n" + "Age: " + this.age + "\n" + "Address: " + this.address;
    }
    
    
    public static void readData() {
    	  
    	String temp = "";
    	int counter = 0;
    	try {
    		myFile = new File("contact.txt");
			Scanner in = new Scanner(myFile);
			while(in.hasNextLine()) {
				temp += in.nextLine() + ",";
				counter++;
			}
			
			String[] parser = parseLine(temp,counter);
			customerContactInfo = new HashMap<>();
			for(int i =0;i < parser.length-2;i+=2) {
				customerContactInfo.put(parser[i], Integer.valueOf(parser[i+1]));
			}

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