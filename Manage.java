
public class Manage {
	
	public static void main(String[] args) {
		Account temp = new Account();
		temp.setAccountDetails(13435523, 1534);
		
		Saving mySaving = new Saving(1234431);
		
		temp.AddAccount(mySaving);

		SavingGUI cg = new SavingGUI(mySaving);
		
		
		
		cg.displayCheckingGUI();
		
		

}
}