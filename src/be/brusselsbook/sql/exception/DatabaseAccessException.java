package be.brusselsbook.sql.exception;

public class DatabaseAccessException extends RuntimeException {

	private static final long serialVersionUID = 2602879529029224059L;

	public DatabaseAccessException(String message) {
		super(message);
	}

	public DatabaseAccessException(Throwable cause) {
		super(cause);
	}

	public DatabaseAccessException(String message, Throwable cause) {
		super(message, cause);
	}
}
