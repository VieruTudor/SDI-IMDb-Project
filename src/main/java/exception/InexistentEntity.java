package exception;

public class InexistentEntity extends RuntimeException {
    public InexistentEntity(String entity_does_not_exist) {
        super(entity_does_not_exist);
    }
}
