package be.brusselsbook.sql.exception;

public class AccessException extends RuntimeException {

	private static final long serialVersionUID = 2602879529029224059L;

	public AccessException(String message) {
		super(message);
	}

	public AccessException(Throwable cause) {
		super(cause);
	}

	public AccessException(String message, Throwable cause) {
		super(message, cause);
	}
}
