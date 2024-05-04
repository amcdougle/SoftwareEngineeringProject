import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NameTest {
	Name myName = new Name("John","Doe");
	
	
	@Test
	public void getLastNameTest() {
		assertEquals("Doe", myName.getLast());
	}
	
	@Test
	public void getFirstNameTest() {
		assertEquals("John",myName.getFirst());
	}
	
	
	@Test
	public void setLastNameTest() {
		this.myName.setLast("Smith");
		assertEquals(this.myName.getLast(),"Smith");
	}
	
	
	@Test
	public void setGetFirstNameTest() {
		this.myName.setFirst("Nathan");
		assertEquals(this.myName.getFirst(),"Nathan");
	}
	
	@Test
	public void toStringTest() {
		assertEquals(this.myName.getFirst() + " "+this.myName.getLast(), this.myName.toString()); 
	}
}

