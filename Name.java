import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.HashMap;

public class Name implements Serializable{
    private String last="";
    private String first="";
    private static HashMap<String,Integer> customerContactInfo;
    private static File myFile;
    private FileWriter myWriter;

    public Name(String first, String last) {
        this.last = last;
        this.first = first;
    }

    public Name() {
        this.last = "";
        this.first = "";
    }

    public String getLast() {
        return last;
    }

    public String getFirst() {
        return first;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    @Override
    public String toString(){
        return first + " " + last;
    }
}