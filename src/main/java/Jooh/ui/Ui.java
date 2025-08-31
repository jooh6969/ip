package Jooh.ui;

import Jooh.task.Task;
import java.util.stream.IntStream;
import java.util.List;

/**
 * Handles all user interface interactions for Jooh.
 * Provides methods to print greetings, farewells, and status updates
 * about tasks to the console in a consistent format.
 */
public class Ui {
    private final String line = "____________________________________________________________________________________";

    /**
     * Prints the welcome message shown when the application starts.
     */
    public void welcomeMsg() {
        System.out.println(line);
        System.out.println("Hello! I'm Jooh, a pre-programmed ChatBot");
        System.out.println("What can I do for you today?");
        System.out.println(line);
    }

    /**
     * Prints the goodbye message shown when the application exits.
     */
    public void goodbyeMsg() {
        System.out.println(line);
        System.out.println("Have a nice day! Goodbye!");
        System.out.println(line);
    }

    /**
     * Prints a formatted list of all tasks in the current task list.
     *
     * @param task The list of tasks to display.
     */
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

    /**
     * Prints a confirmation message that a task was added,
     * including the updated total number of tasks.
     *
     * @param task The task that was added.
     * @param size The updated number of tasks in the list.
     */
    public void addTaskMsg(Task task, int size) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(line);
    }

    /**
     * Prints a confirmation message that a task was marked as completed.
     *
     * @param task The task that was marked done.
     */
    public void taskMarkedDoneMsg(Task task) {
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        System.out.println(line);
    }

    /**
     * Prints a confirmation message that a task was marked as not completed.
     *
     * @param task The task that was marked undone.
     */
    public void taskMarkedUndoneMsg(Task task) {
        System.out.println(line);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        System.out.println(line);
    }

    /**
     * Prints a confirmation message that a task was removed from the list.
     *
     * @param rmv The string representation of the removed task.
     */
    public void taskRemovedMsg(String rmv) {
        System.out.println(line);
        System.out.println("Noted. I've removed this task");
        System.out.println(rmv);
        System.out.println(line);
    }
}

