public class Task {
    private String desc;
    private Boolean status;

    public Task(String desc) {
        this.desc = desc;
        this.status = false;
    }

    public void markDone() {
        this.status = true;
    }

    public void markUndone() {
        this.status = false;
    }

    @Override
    public String toString() {
        return status ? "[X] " + desc : "[ ] " + desc;
    }
}
