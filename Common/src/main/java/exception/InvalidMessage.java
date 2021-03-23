package exception;

public class InvalidMessage extends RuntimeException {
    public InvalidMessage(String message) {
        super(message);
    }
}
