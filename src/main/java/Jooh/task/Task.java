package Jooh.task;

public class Task {
    private String desc;
    private Boolean isDone;

    public Task(String desc, Boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String getDesc() {
        return desc;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return this.isDone ? "[X] " + desc : "[ ] " + desc;
    }
}
