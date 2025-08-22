public class Task {
    private String desc;
    private Boolean status;

    public Task(String desc) {
        this.desc = desc;
        this.status = false;
    }

    public void mark() {
        this.status = !status;
    }

    @Override
    public String toString() {
        return status ? "[X] " + desc : "[ ]" + desc;
    }
}
