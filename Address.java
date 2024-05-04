import java.io.Serializable;

public class Address implements Serializable {
    private String street;
    private String city;
    private String state;
    private String zipCode;


    private String email;

    public Address(){
        this.street="";
        this.city="";
        this.state="";
        this.zipCode="";
        this.email= "";
    }
    public Address(String street, String city, String state, String zipCode, String email){
        this.street=street;
        this.city=city;
        this.state=state;
        this.zipCode=zipCode;
        this.email = email;
    }

    @Override
    public String toString(){
        return street + "," + city + "," + state + "," + zipCode;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}