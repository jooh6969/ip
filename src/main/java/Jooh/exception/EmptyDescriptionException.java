package Jooh.exception;

public class EmptyDescriptionException extends JoohException {
    public EmptyDescriptionException(String event) {
        super("The description of a " + event + " cannot be empty");
    }
}
