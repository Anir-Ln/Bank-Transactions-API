package ma.octo.assignement.exceptions;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException() {
        super("not found");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
