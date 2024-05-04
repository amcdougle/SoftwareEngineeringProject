import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Client {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		try (Socket socket = new Socket("localhost", 1234)) {
			// Output stream socket.
			OutputStream outputStream = socket.getOutputStream();
			InputStream inputStream = socket.getInputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

			Message temp = new Message(Type.LOGIN.toString(), "Initiated", "Login Message");
			objectOutputStream.writeObject(temp);
			LoginGUI tempGUI = new LoginGUI();
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
			tempGUI = (LoginGUI) objectInputStream.readObject();
			tempGUI.displayLoginGUI();
			Thread t1 =new Thread();
			while(!tempGUI.getClicked()) {
			if(!tempGUI.getClicked()) {
				Thread.sleep(100);
			}else {
				t1.interrupt();
			}
			}
			tempGUI.loginFrame.setVisible(false);
			objectOutputStream.writeObject(tempGUI);
			
			temp = (Message)objectInputStream.readObject();
			String typeOfTemp = temp.getType();
			System.out.println(typeOfTemp);
			if(typeOfTemp.equalsIgnoreCase(Type.TELLERGUI.getType())) {
				System.out.println("inside of teller gu stuff");
				TellerGUI tellerG = (TellerGUI) objectInputStream.readObject();
				tellerG.displayTellerGUI();
			}else if (typeOfTemp.equalsIgnoreCase(Type.CUSTOMERGUI.getType())) {
				OnlineCustomerGUI OCG = (OnlineCustomerGUI) objectInputStream.readObject();
				OCG.displayOnlineCustomerGUI();
			}
						


			socket.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void display(Object m) {
		if(m instanceof Message) {
		    Message msg = (Message) m;
		System.out.println("Message from the Server:\nMessage Text: " + msg.getText() + "\nMessage Status: "
				+ msg.getStatus() + "\nMessage Type: " + msg.getType());
		}

	}
	
}

