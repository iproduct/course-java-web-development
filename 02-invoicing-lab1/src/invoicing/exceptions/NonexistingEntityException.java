package invoicing.exceptions;

public class NonexistingEntityException extends Exception {
	private static final long serialVersionUID = 1L;

	public NonexistingEntityException() {
	}

	public NonexistingEntityException(String message) {
		super(message);
	}

	public NonexistingEntityException(Throwable cause) {
		super(cause);
	}

	public NonexistingEntityException(String message, Throwable cause) {
		super(message, cause);
	}

	public NonexistingEntityException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
