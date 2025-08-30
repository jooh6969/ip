package Jooh.task;

public class Todo extends Task {
    public Todo(String desc, Boolean isDone) {
        super(desc, isDone);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

}
