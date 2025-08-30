package Jooh.exception;

public class InvalidDeadlineException extends JoohException {
    public InvalidDeadlineException() {
        super("No deadline provided? :(");
    }
}
