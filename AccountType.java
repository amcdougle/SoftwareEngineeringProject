
public enum AccountType {
	Checking("checking"),
	Saving("saving"),
	CreditCard("credit card");
	
	public String AccountTypeStr;
	
	private AccountType(String temp) {
		this.AccountTypeStr = temp;
	}
	
	public String getAccountTypeNum() {
		return this.AccountTypeStr;
	}
}
