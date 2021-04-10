package exceptions;

public class InexistentEntity extends RuntimeException {
    public InexistentEntity(String message) {
        super(message);
    }
}
