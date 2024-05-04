import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneTest {
    Phone p = new Phone("510", "123", "4567");

    @Test
    void getAreaCode() {
        p.setAreaCode("510");
        assertEquals(p.getAreaCode(),"510");
    }

    @Test
    void getNumber1() {
        p.setNumber1("123");
        assertEquals(p.getNumber1(), "123");
    }

    @Test
    void getNumber2() {
        p.setNumber2("4567");
        assertEquals(p.getNumber2(), "4567");
    }

    @Test
    void testToString() {
        assertEquals(p.toString(), "510-123-4567");
    }
}