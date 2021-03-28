package exception;

public class InexistentEntity extends RuntimeException {
    public InexistentEntity(String message) {
        super(message);
    }
}
