package invoicing.exception;

public class NonExistingEntityException extends Exception {

	public NonExistingEntityException() {
	}

	public NonExistingEntityException(String arg0) {
		super(arg0);
	}

	public NonExistingEntityException(Throwable arg0) {
		super(arg0);
	}

	public NonExistingEntityException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public NonExistingEntityException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
