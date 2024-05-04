import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class OnlineCustomerGUI implements Serializable {
	JFrame onlineFrame;
	OnlineCustomer onCust;
	Checking c;
	Saving s;

	public OnlineCustomerGUI(OnlineCustomer oc) {
		onCust = oc;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void displayOnlineCustomerGUI() {
		onlineFrame = new JFrame();
		onlineFrame.setTitle(onCust.getCustomerContactInfo().getFullName().getFirst() + " " +
		onCust.getCustomerContactInfo().getFullName().getLast());
		onlineFrame.setSize(800, 400);
		onlineFrame.setVisible(true);
		onlineFrame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Please choose the account you want to view");
		lblNewLabel.setBounds(211, 88, 477, 22);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		onlineFrame.getContentPane().add(lblNewLabel);
		
		
		JLabel userGreetingLabel = new JLabel("Welcome" + onCust.getCustomerContactInfo().getFullName().toString());
		userGreetingLabel.setFont(new Font("Arial Black", Font.BOLD, 24));

		Account temp = onCust.getOnlineCustomerAccount();

		boolean checking = false, saving = false;
		Account.readAccounts();
		ArrayList<String> accountTypes = Account.accounts.get(OnlineCustomer.getAccountNumber(onCust.getUserName()));
		int value = 0;
		for (int i = 0; i < accountTypes.size(); i++) {
			value = Integer.valueOf(accountTypes.get(i));
			if (accountTypes.get(i).charAt(0) == '4') {
				c = new Checking(value);
				temp.AddAccount(c);
				checking = true;
			} else if (accountTypes.get(i).charAt(0) == '5') {
				s = new Saving(value);
				temp.AddAccount(s);
				saving = true;
			}
		}

		
		JButton checkingButton = new JButton("Checking");
		checkingButton.setBounds(356, 149, 100,28);
		onlineFrame.getContentPane().add(checkingButton);
		checkingButton.setVisible(false);
		if (checking) {
			checkingButton.setVisible(true);
			checkingButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					CheckingGUI cg = new CheckingGUI(c);
					cg.displayCheckingGUI();
				}
			});

		}
		
		JButton savingButton = new JButton("Saving");
		savingButton.setBounds(356, 217, 100,28);
		onlineFrame.getContentPane().add(savingButton);
		
		
		userGreetingLabel.setBounds(10, 11, 483, 51);
		onlineFrame.getContentPane().add(userGreetingLabel);
		
		
		
		savingButton.setVisible(false);

		if (saving) {
			savingButton.setVisible(true);;
			savingButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					SavingGUI cg = new SavingGUI(s);
					cg.displayCheckingGUI();
				}
			});

		}
	}
}
