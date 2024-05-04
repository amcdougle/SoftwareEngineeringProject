import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class checkingTest {
	Checking myChecking = new Checking(4583831);
	@Test
	public boolean sourceTest() {
		assertEquals(myChecking.getSourceName(),"4583831-Checking.txt");
		return true;
	}
	
	
	@Test
	public void balanceTest() {
		assertEquals(myChecking.getBalance(),0.0);
	}
	
	@Test
	public void RoutingTest() {
		myChecking.setRoutingNumber(1235123);
		assertEquals(myChecking.getRoutingNumber(),1235123);
	}
	
	@Test
	public void overDraftTest() {
		myChecking.setBalance(15000);
		
		assertFalse(myChecking.overDraft());
		
	}
	
	
	@Test
	public void TranscationTest() {
		try {
			this.myChecking.depositMoney(10000,4583831);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(this.myChecking.getBalance(),10000);
	}
	
	
	@Test
	public void withdrawTest() {
		try {
			this.myChecking.depositMoney(10000,4583831);
			this.myChecking.withdrawMoney(1500);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(this.myChecking.getBalance(),8500);
	}
	
	
	@Test
	public void sendMoneyTest() throws Exception {
		myChecking.depositMoney(10000, 4583831);
		Checking tempChecking = new Checking(4143131);
		System.out.println(tempChecking.getBalance());
		tempChecking.depositMoney(1000, 4583831);
		assertEquals(1000,tempChecking.getBalance());
	}
	
	

}
