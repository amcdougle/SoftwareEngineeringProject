import java.io.Serializable;

public class Phone implements Serializable{
    private String areaCode;
    private String number1;
    private String number2;


    public Phone(String areaCode, String number1, String number2) {
        this.areaCode = areaCode;
        this.number1 = number1;
        this.number2 = number2;
    }

    public Phone() {
        this.areaCode="";
        this.number1="";
        this.number2="";
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getNumber1() {
        return number1;
    }

    public void setNumber1(String number1) {
        this.number1 = number1;
    }

    public String getNumber2() {
        return number2;
    }

    public void setNumber2(String number2) {
        this.number2 = number2;
    }

    @Override
    public String toString(){
        return areaCode + "-" + number1 + "-" + number2;
    }

}