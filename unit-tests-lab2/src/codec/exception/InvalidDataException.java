package codec.exception;

public class InvalidDataException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidDataException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidDataException(String arg0) {
		super(arg0);
	}

	public InvalidDataException(Throwable arg0) {
		super(arg0);
	}

	public InvalidDataException() {
	}

}
