public class NegativeNumberOfSecondsException extends Exception {
    public NegativeNumberOfSecondsException() {
    }

    public NegativeNumberOfSecondsException(String message) {
        super(message);
    }

    public NegativeNumberOfSecondsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NegativeNumberOfSecondsException(Throwable cause) {
        super(cause);
    }
}
