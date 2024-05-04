import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class bank {
	
	public static void main(String [] args) {
		File myFile = new File("CustomerAccount.txt");
		try {
			Scanner in = new Scanner(myFile);
			
			while(in.hasNext()) {
				System.out.println(in.next());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
