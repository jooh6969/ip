package Jooh.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void removeTask(int n) {
        tasks.remove(n);
    }

    public Task getTask(int n) {
        return tasks.get(n);
    }

    public List<Task> getTaskList() {
        return tasks;
    }

    public void markTaskDone(int n) {
        tasks.get(n).markDone();
    }

    public void markTaskUndone(int n) {
        tasks.get(n).markUndone();
    }

    public String getTaskAsString(int n) {
        return tasks.get(n).toString();
    }
 }
