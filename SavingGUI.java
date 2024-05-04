import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class SavingGUI {
	JButton displayAllTransaction;
	JButton sendMoney;
	JButton depositMoney;
	JButton withdrawMoney;
	JFrame savingFrame;
	JScrollPane scrollPane;
	private Saving userSaving;
	private JTable recentTransactions;
	JLabel balanceLabel;

	public SavingGUI(Saving s) {
		userSaving = s;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void displayCheckingGUI() {
		savingFrame = new JFrame();
		savingFrame.setResizable(false);
		savingFrame.setType(Type.NORMAL);
		savingFrame.setBounds(0, 0, 1113, 840);
		savingFrame.setTitle("Saving");
		savingFrame.getContentPane().setLayout(null);
		savingFrame.setVisible(true);

		JButton btnNewButton = new JButton("Return");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				savingFrame.setVisible(false);
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);

		btnNewButton.setSize(150, 267);
		btnNewButton.setBounds(0, 0, 90, 35);
		savingFrame.getContentPane().add(btnNewButton);

		JLabel accountNumber = new JLabel("Saving Number:" + userSaving.getSavingNumber());
		accountNumber.setFont(new Font("Arial", Font.BOLD, 16));
		accountNumber.setHorizontalAlignment(SwingConstants.CENTER);
		accountNumber.setBounds(308, 0, 246, 42);
		savingFrame.getContentPane().add(accountNumber);

		balanceLabel = new JLabel("Balance: $" + Double.toString(userSaving.getBalance()));
		balanceLabel.setFont(new Font("Arial", Font.BOLD, 18));
		balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		balanceLabel.setBounds(334, 37, 246, 50);
		savingFrame.getContentPane().add(balanceLabel);

		Button deposit_button = new Button("Deposit Money");
		deposit_button.setBounds(885, 72, 193, 27);
		deposit_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String amount = JOptionPane.showInputDialog(deposit_button, "Enter Amount to Deposit", null,
						JOptionPane.PLAIN_MESSAGE);
				System.out.println(amount);
				try {

					userSaving.DepositMoney(Double.valueOf(amount), userSaving.getSavingNumber());
					updateTransactionsGUI();
					balanceLabel.setText("Balance: $" + userSaving.getBalance());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

		savingFrame.getContentPane().add(deposit_button);

		Button withdraw_button = new Button("Withdraw Money");
		withdraw_button.setBounds(885, 144, 193, 27);
		savingFrame.getContentPane().add(withdraw_button);
		withdraw_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String amount = JOptionPane.showInputDialog(deposit_button, "Enter Amount to Withdraw", null,
						JOptionPane.PLAIN_MESSAGE);
				System.out.println(amount);
				try {

					userSaving.withdrawMoney(Double.valueOf(amount));
					updateTransactionsGUI();
					balanceLabel.setText("Balance: $" + Double.toString(userSaving.getBalance()));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

		Button send_button = new Button("Send Money");
		send_button.setBounds(885, 220, 193, 27);
		savingFrame.getContentPane().add(send_button);

		Button routing_number = new Button("Routing Number");
		routing_number.setBounds(885, 293, 193, 35);
		savingFrame.getContentPane().add(routing_number);

		routing_number.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				JOptionPane.showMessageDialog(routing_number, String.valueOf(userSaving.getRoutingNumber()),
						"Routing Number", JOptionPane.INFORMATION_MESSAGE);

			}

		});

		Button transaction_histroy_button = new Button("Transaction History");
		transaction_histroy_button.setBounds(885, 374, 193, 35);
		savingFrame.getContentPane().add(transaction_histroy_button);

		transaction_histroy_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String[] temp = userSaving.getParseTransaction();
				String sol = "<html>Transaction&emsp;&emsp;Amount&emsp;&emsp;Account Balance&emsp;&emsp;Date&emsp;&emsp;Account Number<br/>";
				for (int i = temp.length - 1; i > 0; i -= 5) {
					sol += temp[i] + "&emsp;&emsp;" + temp[i - 1] + "&emsp;" + temp[i - 2] + "&emsp;" + temp[i - 3]
							+ temp[i - 4] + "<br/>";
				}
				sol += "</html>";

				JFrame myFrame = new JFrame("Transaction History");
				myFrame.setSize(700, 600);
				myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				myFrame.setVisible(true);
				myFrame.setLocationRelativeTo(null);
				myFrame.setAutoRequestFocus(true);

				JLabel myLabel = new JLabel();
				myLabel.setVerticalAlignment(SwingConstants.TOP);
				myLabel.setText(sol);

				myFrame.getContentPane().add(myLabel);

			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 98, 865, 569);

		savingFrame.getContentPane().add(scrollPane);

		updateTransactionsGUI();
		scrollPane.setViewportView(recentTransactions);
	}

	public void updateTransactionsGUI() {
		Object[][] temp = new Object[34][5];
		
		String[] tempChecking = userSaving.displayTransaction();
		if(tempChecking.length/5 > 34) {
			int k = 0;
			for(int i= 0;i < temp.length;i++) {
				for(int j = 0; j< temp[i].length;j++) {
					temp[i][j] = tempChecking[k++];
				}
			}
		}else {
			int k = 0;
			for(int i = 0; i <tempChecking.length/5;i++) {
				for(int j = 0; j<5;j++) {
					temp[i][j] = tempChecking[k++];
				}
			}
			
		}
		

		recentTransactions = new JTable();
		recentTransactions.setShowGrid(false);
		recentTransactions.setModel(new DefaultTableModel(temp,
				new String[] { "Account Number", "Date", "Account Balance", "Amount", "Transaction Type" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		recentTransactions.getColumnModel().getColumn(0).setResizable(false);
		recentTransactions.getColumnModel().getColumn(0).setPreferredWidth(96);
		recentTransactions.getColumnModel().getColumn(1).setResizable(false);
		recentTransactions.getColumnModel().getColumn(2).setResizable(false);
		recentTransactions.getColumnModel().getColumn(2).setPreferredWidth(93);
		recentTransactions.getColumnModel().getColumn(3).setResizable(false);
		recentTransactions.getColumnModel().getColumn(3).setPreferredWidth(155);
		scrollPane.setViewportView(recentTransactions);
		balanceLabel.setText("Balance: $" + Double.toString(userSaving.getBalance()));


	}
}
