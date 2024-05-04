import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class Server {
	static HashMap<String,String> myCustomerMap;
	static HashMap<String,String> myTellerMap;
	 static File myFile;
	public static void main(String[] args) {
		ServerSocket server = null;
		Socket client = null;

		try {

			server = new ServerSocket(1234);
			server.setReuseAddress(true);

			System.out.println("Starting the Server..."+"\n"+server.toString() + "\n");
			while (true) {
				client = server.accept();
				ClientHandler clientSocket = new ClientHandler(client);
				new Thread(clientSocket).start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (server != null) {
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private static void printMessage(Message msg) {
		System.out.println(
				"Type: " + msg.getType() + "\nStatus: " + msg.getStatus() + "\nText: " + msg.getText());
	}

	private static class ClientHandler implements Runnable {
		private final Socket ClientSocket;

		public ClientHandler(Socket socket) {
			this.ClientSocket = socket;
		}

		@Override
		public void run() {
			InputStream inputStream;
			OutputStream outputStream;
			try {
				
				System.out.println("Getting connection request from " + ClientSocket.toString());
				inputStream = ClientSocket.getInputStream();
				outputStream = ClientSocket.getOutputStream();
				LoginGUI lg = new LoginGUI();
				
				ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
				Message serverMessage = (Message) objectInputStream.readObject();
				printMessage(serverMessage);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
				objectOutputStream.writeObject(lg);
				lg = (LoginGUI) objectInputStream.readObject();
				
				this.readDataUser();
				this.readDataTeller();
				
				System.out.println("inside of server");

				
				if(myCustomerMap.containsKey(lg.getUserNameTextField()) &&
						myCustomerMap.containsValue(lg.getPasswordField())) {
					System.out.println("inside of customer");
					serverMessage = new Message(Type.CUSTOMERGUI.getType(),"initiate","customer gui");
					objectOutputStream.writeObject(serverMessage);
					Name n = new Name("Ebadullah","Wardak");
					Address a = new Address("1 Washington Street","Fremont","CA","95236","ewardak@yahoo.com");
					Phone p = new Phone("209","542","2421");
					ContactInfo ci = new ContactInfo("22",n,a,p);
					int temp = OnlineCustomer.getAccountNumber(lg.getUserNameTextField());
					Account.readAccounts();
					ArrayList<String> listAcc = Account.accounts.get(temp);
					Account tempAcc = new Account();
					Account.readPins();
					
					tempAcc.setAccountDetails(temp,Account.pin.get(temp));
					for(int i =0;i < listAcc.size();i++) {
						int value = Integer.valueOf(listAcc.get(i));
						if(listAcc.get(i).charAt(0) =='4'){
							System.out.println(value + " Inside of checking");
							Checking c = new Checking(value);
							tempAcc.AddAccount(c);
						}else if(listAcc.get(i).charAt(0)=='5') {
							System.out.println(value + " Inside of savings");
							 Saving s = new Saving(value);
							 tempAcc.AddAccount(s);
						}
					}
					
					OnlineCustomer oncc = 
							new OnlineCustomer(lg.getUserNameTextField(),lg.getPasswordField(),false,tempAcc,ci);
					
					OnlineCustomerGUI CG = new OnlineCustomerGUI(oncc);
					objectOutputStream.writeObject(CG);
					
					
				}
				
				if(myTellerMap.containsKey(lg.getUserNameTextField()) 
						&& myTellerMap.containsValue(lg.getPasswordField())) {
					System.out.println("this is teller");
					serverMessage = new Message(Type.TELLERGUI.getType(),"initiate","teller gui");

						Name n = new Name("Gurjas","Singh");
						Address a = new Address("1 Carlos Bee Drive","Hayward","CA","95236","gurjas1@gmail.com");
						Phone p = new Phone("207","343","5521");
						ContactInfo c = new ContactInfo("23",n,a,p);
						Teller temp = new Teller(c);
						TellerGUI TG = new TellerGUI(temp);
						System.out.println("ALL the way here");
						objectOutputStream.writeObject(TG);
				}	
					
					
		}catch(EOFException e) {
			try {
				ClientSocket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
		
		

		private  void readDataUser() {
			myFile = new File("userDatabase.txt");
			String temp = "";
			int counter = 0;
			try {
				Scanner in = new Scanner(myFile);
				while(in.hasNextLine()) {
					temp += in.nextLine() + ",";
					counter++;
				}
				
				 String [] parseCustomers = parseLine(temp,counter,2);
				 myCustomerMap = new HashMap<>();
				 for(int i = 0;i < parseCustomers.length-2;i+=2) {
					 myCustomerMap.put(parseCustomers[i], parseCustomers[i+1]);
				 }
				in.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		private  void readDataTeller() {
			myFile = new File("TellerDatabase.txt");
			String temp = "";
			int counter = 0;
			try {
				Scanner in = new Scanner(myFile);
				while(in.hasNextLine()) {
					temp += in.nextLine() + ",";
					counter++;
				}
				
				System.out.println("data teller readerr: "+ temp);
				
				 String [] parseTellers = parseLine(temp,counter,2);
				 myTellerMap = new HashMap<>();
				 for(int i = 0;i <= parseTellers.length-2;i+=2) {
					System.out.println(parseTellers[i]);
					 myTellerMap.put(parseTellers[i], parseTellers[i+1]);
				 }
				 
				 System.out.println(myTellerMap);
				in.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		private  String[] parseLine(String line,int counter,int size) {
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
	
	
	
	
	
}

