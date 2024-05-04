import java.io.Serializable;
import java.lang.IllegalArgumentException;
public class Message implements Serializable {
	private static final long serialVersionUID = 2L;
	protected String type;
    protected String status;
    protected String text;
    public Message(){
        this.type = "undefined";
        this.status = "Undefined";
        this.text = "Undefined";
    }
    public Message(String type, String status, String text){
    	type = type.toUpperCase();
    	setType(Type.valueOf(type));
        setStatus(status);
        setText(text);
    }
    private void setType(Type type){
    	try {
    	this.type = type.getType();
    	}catch(IllegalArgumentException e) {
    		System.out.println("ERROR!!\n" + type.typeValue + " isn't valid");
    		e.printStackTrace();
    	}
    	
    }
    public void setStatus(String status){
    	this.status = status;
    }
    public void setText(String text){
    	this.text = text;
    }
    public String getType(){
    	return this.type;
    }
    public String getStatus(){
    	return this.status;
    }
    public String getText(){
    	return this.text;
    }
}