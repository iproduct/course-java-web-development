package users.exceptions;

public class PropertyChangeNotAllowedException extends Exception {

	public PropertyChangeNotAllowedException() {
	}

	public PropertyChangeNotAllowedException(String message) {
		super(message);
	}

	public PropertyChangeNotAllowedException(Throwable cause) {
		super(cause);
	}

	public PropertyChangeNotAllowedException(String message, Throwable cause) {
		super(message, cause);
	}

	public PropertyChangeNotAllowedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
