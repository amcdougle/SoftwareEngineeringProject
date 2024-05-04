import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LoginGUI implements Serializable {
	private static final long serialVersionUID = 1L;
	public JFrame loginFrame;
	private JTextField userNameTextField;
	private JPasswordField passwordField;
	private JButton submitButton;
	private boolean clicked = false;
	
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void displayLoginGUI() {
		loginFrame = new JFrame("Login");
		loginFrame.setTitle("Login Page");
		loginFrame.getContentPane().setLayout(null);
		loginFrame.setSize(500, 500);
		
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(203, 49, 58, 29);
		loginFrame.getContentPane().add(lblNewLabel);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(111, 92, 78, 14);
		loginFrame.getContentPane().add(usernameLabel);
		
		userNameTextField = new JTextField();
		userNameTextField.setBounds(179, 89, 96, 20);
		loginFrame.getContentPane().add(userNameTextField);
		userNameTextField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(111, 123, 71, 14);
		loginFrame.getContentPane().add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(179, 120, 96, 20);
		loginFrame.getContentPane().add(passwordField);
		
		 submitButton = new JButton("submit");
		submitButton.setBounds(179, 178, 89, 23);
		loginFrame.getContentPane().add(submitButton);
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clicked = true;
			}
		});
		
		loginFrame.setVisible(true);
	}
	
	
	public boolean getClicked() {
		return this.clicked;
	}
	
	
	public String getUserNameTextField() {
		return this.userNameTextField.getText();
	}
	
	public JButton getSubmitButton() {
		return this.submitButton;
	}
	
	@SuppressWarnings("deprecation")
	public String getPasswordField() {
		return this.passwordField.getText();
	}
	
}
