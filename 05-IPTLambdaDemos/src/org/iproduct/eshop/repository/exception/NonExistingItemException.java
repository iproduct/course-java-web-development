package org.iproduct.eshop.repository.exception;

public class NonExistingItemException extends Exception {
	private static final long serialVersionUID = 1L;

	public NonExistingItemException() {
	}

	public NonExistingItemException(String message) {
		super(message);
	}

	public NonExistingItemException(Throwable cause) {
		super(cause);
	}

	public NonExistingItemException(String message, Throwable cause) {
		super(message, cause);
	}

	public NonExistingItemException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
