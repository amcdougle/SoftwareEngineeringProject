import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MessageTest {
Message myMessage = new Message(Type.LOGIN.getType(),"initiate","I want to log in");

@Test
public void getTypeTest() {
	assertEquals(this.myMessage.getType(),Type.LOGIN.getType());
}

@Test
public void getStatusTest() {
	assertEquals(this.myMessage.getStatus(),"initiate");
}

@Test
public void getTextTest() {
	assertEquals(this.myMessage.getText(),"I want to log in");
}

@Test
public void setTextTest() {
	this.myMessage.setText("Sure");
	assertEquals(this.myMessage.getText(),"Sure");
}

@Test
public void setStatusTest() {
	this.myMessage.setStatus("Success");
	assertEquals(this.myMessage.getStatus(),"Success");
}

}
