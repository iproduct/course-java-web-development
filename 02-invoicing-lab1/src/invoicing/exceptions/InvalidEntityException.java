package invoicing.exceptions;

public class InvalidEntityException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidEntityException() {
	}

	public InvalidEntityException(String message) {
		super(message);
	}

	public InvalidEntityException(Throwable cause) {
		super(cause);
	}

	public InvalidEntityException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidEntityException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
