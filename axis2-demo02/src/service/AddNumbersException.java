package service;

public class AddNumbersException extends Exception {
	private static final long serialVersionUID = 1L;
	
	String detail;
    
    public AddNumbersException (String message, String detail) {
        super (message);
        this.detail = detail;
    }
    
    public String getDetail () {
        return detail;
    }
}
