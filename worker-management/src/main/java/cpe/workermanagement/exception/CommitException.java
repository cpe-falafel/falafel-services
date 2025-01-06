package cpe.workermanagement.exception;

public class CommitException extends Exception {

    /**
     * Default constructor.
     */
    public CommitException() {
        super();
    }

    /**
     * Constructor with a custom message.
     *
     * @param message the detail message for the exception.
     */
    public CommitException(String message) {
        super(message);
    }

    /**
     * Constructor with a custom message and a cause.
     *
     * @param message the detail message for the exception.
     * @param cause   the cause of the exception (can be another throwable).
     */
    public CommitException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with a cause.
     *
     * @param cause the cause of the exception (can be another throwable).
     */
    public CommitException(Throwable cause) {
        super(cause);
    }
}
