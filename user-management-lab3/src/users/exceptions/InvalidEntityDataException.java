package users.exceptions;

public class InvalidEntityDataException extends Exception {

	public InvalidEntityDataException() {
	}

	public InvalidEntityDataException(String message) {
		super(message);
	}

	public InvalidEntityDataException(Throwable cause) {
		super(cause);
	}

	public InvalidEntityDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidEntityDataException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
