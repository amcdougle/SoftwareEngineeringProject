import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class SavingTest {
    Saving save = new Saving(1);
    @Test
    void getType() {
        assert save.getType() == "saving";

    }

    @Test
    void setRoutingNumber() {
        save.setRoutingNumber(1234);
        assertEquals(save.getRoutingNumber(),1234);

    }

    @Test
    void getBalance() {
        assertEquals(save.getBalance(), 0);

    }

    @Test
    void getParseTransaction() {
        assertEquals(save.getParseTransaction(), null);

    }

    @Test
    void displayTransaction() {
        assertEquals(save.displayTransaction(), save.getParseTransaction());


    }

    @Test
    void parseLine() {
        String s = "Hi,my,name,is,bob,";
        String [] a = {"Hi","my","name","is","bob"};
        String c ="";
        String t = "";
        for (int i=0 ; i < save.parseLine(s,1).length; i++){
            c += save.parseLine(s,1)[i];
            t += a[i];

        }
        assertEquals(c,t);

    }

    @Test
    void appendTransaction() throws Exception {
        save.DepositMoney(2000,1);
        save.appendTransaction(2000,1,"Deposit",0);
        assertEquals(2030,save.getBalance());
    }

    @Test
    void getaccountNumber() {
        assertEquals(save.getaccountNumber(),1);
    }

    @Test
    void routingNumber() {
        assertEquals(save.routingNumber(),0);
    }

    @Test
    void getmySavingFile() {
        String path = "C:\\Users\\almcd\\IdeaProjects\\BankProjectUnittest\\1-Saving.txt";

        File file = new File(path);
        String absolutePath = file.getAbsolutePath();

        System.out.println(absolutePath);

        assertTrue(absolutePath.endsWith("C:\\Users\\almcd\\IdeaProjects\\BankProjectUnittest\\1-Saving.txt"));    }

    @Test
    void withdrawMoney() throws Exception {
        save.DepositMoney(3000,1);
        save.withdrawMoney(1500);
        assertEquals(1545,save.getBalance());
    }

    @Test
    void DepositMoney() throws Exception {
        save.DepositMoney(500,1);
        assertEquals(507.5,save.getBalance());
    }

    @Test
    void getRoutingNumber() {
        assertEquals(save.getRoutingNumber(),0);
    }

    @Test
    void getSavingNumber() {
        assertEquals(save.getSavingNumber(),1);
    }
}