import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AddressTest {
Address myAddress = new Address("2400 Carlos Bee Drive","Hayward","CA","95330","eb123@gmail.com");
	@Test
	public boolean getStreetTest() {
		assertEquals("2400 Carlos Bee Drive",this.myAddress.getStreet());
		return true;
	}
	
	@Test
	public void getCityTest() {
		assertEquals("Hayward",this.myAddress.getCity());
	}
	
	@Test
	public void getStateTest() {
		assertEquals("CA",this.myAddress.getState());
	}
	
	
	@Test
	public void getZipCodeTest() {
		assertEquals("95330",this.myAddress.getZipCode());
	}
	
	@Test
	public void getEmailTest() {
		assertEquals("eb123@gmail.com",this.myAddress.getEmail());
	}
	
	@Test
	public void setStreetTest() {
		this.myAddress.setStreet("1600 Washington Blvd");
		
		assertEquals("1600 Washington Blvd",this.myAddress.getStreet());
	}
	
	
	@Test
	public void setCityTest() {
		this.myAddress.setCity("Tracy");
		assertEquals("Tracy",this.myAddress.getCity());
	}
	
	
	@Test
	public void setStateTest() {
		this.myAddress.setState("AK");
		assertEquals(this.myAddress.getState(),"AK");
	}
	
	public void setZipCode() {
		this.myAddress.setZipCode("95234");
		assertEquals(this.myAddress.getZipCode(),"95234");
	}

	
	public void setEmailTest() {
		this.myAddress.setEmail("somethingdifferent@gmail.com");
		assertEquals(this.myAddress.getEmail(),"somethingdifferent@gmail.com");
	}

}
