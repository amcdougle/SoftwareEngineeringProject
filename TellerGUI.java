import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class TellerGUI implements Serializable {
Teller tellerGUI;
	public TellerGUI(Teller t) {
		tellerGUI = t;
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void displayTellerGUI() {
		JFrame newFrame = new JFrame();
		newFrame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Welcome, Teller");
		lblNewLabel_1.setBounds(20, 11, 111, 23);
		newFrame.getContentPane().add(lblNewLabel_1);
		
		JButton helpCustButton = new JButton("Help existing customer");
		helpCustButton.setBounds(139, 126, 141, 23);
		newFrame.getContentPane().add(helpCustButton);
		helpCustButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JPanel myPanel = new JPanel();
				JLabel myfLabel = new JLabel("User account number: ");
				JTextField fQuest = new JTextField(15);
				
				JLabel mySLabel = new JLabel("User account pin: ");
				JTextField sQuest = new JTextField(15);
				
				myPanel.add(myfLabel);
				myPanel.add(fQuest);
				myPanel.setLayout(new GridLayout(2, 1));
				myPanel.add(mySLabel);
				myPanel.add(sQuest);
				
				JOptionPane.showInternalMessageDialog(null,  myPanel);
				
				String accountNum = fQuest.getText();
				String accountPin = sQuest.getText();
				
			}
			
		});
		
		
		JButton btnNewButton_1 = new JButton("Create new customer");
		btnNewButton_1.setBounds(139, 160, 141, 23);
		newFrame.getContentPane().add(btnNewButton_1);
	}
}
