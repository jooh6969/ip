package Jooh.ui;

import Jooh.task.Task;
import java.util.stream.IntStream;
import java.util.List;

public class Ui {
    private final String line = "____________________________________________________________________________________";
    public void welcomeMsg() {
        System.out.println(line);
        System.out.println("Hello! I'm Jooh, a pre-programmed ChatBot");
        System.out.println("What can I do for you today?");
        System.out.println(line);
    }
    public void goodbyeMsg() {
        System.out.println(line);
        System.out.println("Have a nice day! Goodbye!");
        System.out.println(line);
    }
    public void listTasksMsg(List<Task> task) {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        if (task.isEmpty()) {
            System.out.println("(no tasks yet)");
        } else {
            IntStream.range(0, task.size())
                    .forEach(i -> System.out.println((i + 1) + ": " + task.get(i)));
        }
        System.out.println(line);
    }
    public void addTaskMsg(Task task, int size) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(line);
    }
    public void taskMarkedDoneMsg(Task task) {
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        System.out.println(line);
    }
    public void taskMarkedUndoneMsg(Task task) {
        System.out.println(line);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        System.out.println(line);
    }
    public void taskRemovedMsg(String rmv) {
        System.out.println(line);
        System.out.println("Noted. I've removed this task");
        System.out.println(rmv);
        System.out.println(line);
    }

}
