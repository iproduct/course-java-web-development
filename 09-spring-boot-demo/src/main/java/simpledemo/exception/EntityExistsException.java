package simpledemo.exception;

public class EntityExistsException extends Exception {

	public EntityExistsException() {
	}

	public EntityExistsException(String message) {
		super(message);
	}

	public EntityExistsException(Throwable cause) {
		super(cause);
	}

	public EntityExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
