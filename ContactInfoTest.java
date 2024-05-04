import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class contactinfoTest {
	
	
	ContactInfo myContactInfo = new ContactInfo( "25" , new Name("Tuong", "Tran"), 
			new  Address( "123 Hayward" , "San Jose", "CA" , "95136", "something@gmail"), 
			new Phone( "234" , "123" , "123"));

	@Test
	void getAge() {
		
		assertEquals(myContactInfo.getAge(), "25");
	}
	
	@Test
	void setAge() {
		myContactInfo.setAge("45");
		assertEquals(myContactInfo.getAge(), "45");
	}
	
	@Test
	void getFullName() {
		assertEquals(myContactInfo.getFullName().getFirst(), "Tuong");
		assertEquals(myContactInfo.getFullName().getLast(), "Tran");
	}
	
	@Test
	void setFullName() {
		Name myName = new Name("John", "Wall");
		//myContactInfo.setFullName(myName);
		//assertEquals(myContactInfo.getFullName(), "John", "Smith");
		myContactInfo.setFullName(myName);
		assertEquals(myContactInfo.getFullName().getFirst(), "John");
		assertEquals(myContactInfo.getFullName().getLast(), "Wall");
		//assertEquals(myName.getLast(), "");
		
		
	
	}
	
	@Test
	void getAddress() {
		Address add = new Address();
		assertEquals(add.getStreet(), "");
		assertEquals(add.getCity(), "");
		assertEquals(add.getZipCode(), "");
		assertEquals(add.getEmail(), "");
		
	}
	
	@Test
	void setAddress() {
		Address myAddress = new Address("Canal St", "Oakland", "CA", "94545", "jordan@gmail.com");
		myContactInfo.setAddress(myAddress);
		assertEquals(myContactInfo.getAddress().getCity(), "Oakland");
	}
		
	@Test
	void getPhone() {
		Phone phone = new Phone();
		
		assertEquals(phone.getNumber1(),"");
		assertEquals(phone.getNumber2(),"");
		assertEquals(phone.getAreaCode(),"");


		
	}
	
	@Test
	void setPhone() {
		//assertEquals(myContactInfo.setPhone(510-123-4545), 510-123-4545);
				
	}

}
