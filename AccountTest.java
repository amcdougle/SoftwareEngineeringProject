import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    Account acc = new Account();

    @Test
    void getAccountType() {
        assertEquals(acc.getAccountType(),"1");
    }

    @Test
    void getDisplayAccounts() {
        assertEquals(acc.getDisplayAccounts(), "Checking");
    }

    @Test
    void getAccountNo() {
        assertEquals(acc.getAccountNo(),1);
    }

}