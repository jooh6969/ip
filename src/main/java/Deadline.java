public class Deadline extends Task {
    private String deadline;

    public Deadline(String desc, String deadline, Boolean isDone) {
        super(desc, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + deadline + ")";
    }

    public String getDeadline() {
        return this.deadline;
    }
}
