package invoicing.exceptions;

public class EntityDoesNotExistException extends Exception{

	public EntityDoesNotExistException() {
	}

	public EntityDoesNotExistException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public EntityDoesNotExistException(String arg0) {
		super(arg0);
	}

	public EntityDoesNotExistException(Throwable arg0) {
		super(arg0);
	}

}
