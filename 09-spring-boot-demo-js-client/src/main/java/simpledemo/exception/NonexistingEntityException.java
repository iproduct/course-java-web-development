package simpledemo.exception;

public class NonexistingEntityException extends Exception {

	public NonexistingEntityException() {
	}

	public NonexistingEntityException(String arg0) {
		super(arg0);
	}

	public NonexistingEntityException(Throwable arg0) {
		super(arg0);
	}

	public NonexistingEntityException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public NonexistingEntityException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
