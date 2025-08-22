import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Jooh {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> task = new ArrayList<>();
        String line = "____________________________________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Jooh, a pre-programmed ChatBot");
        System.out.println("What can I do for you today?");
        System.out.println(line);

        while (true) {
            String raw = sc.nextLine();
            String input = raw == null ? "" : raw.trim();

            int sp = input.indexOf(' ');
            String cmd = (sp == -1 ? input : input.substring(0, sp)).toLowerCase();
            String arg = (sp == -1 ? ""    : input.substring(sp + 1).trim());
            try {
                if (cmd.equals("bye")) {
                    System.out.println(line);
                    System.out.println("Have a nice day! Goodbye!");
                    System.out.println(line);
                    break;
                } else if (cmd.equals("list")) {
                    System.out.println(line);
                    System.out.println("Here are the tasks in your list:");
                    if (task.isEmpty()) {
                        System.out.println(" (no tasks yet)");
                    } else {
                        IntStream.range(0, task.size())
                                .forEach(i -> System.out.println((i + 1) + ": " + task.get(i)));
                    }
                    System.out.println(line);
                } else if (cmd.equals("mark")) {
                    if (arg.isEmpty()) {
                        throw new JoohException("Task number must be provided.");
                    }
                    int n;
                    try {
                        n = Integer.parseInt(arg);
                    } catch (NumberFormatException e) {
                        throw new JoohException("Task number must be a positive integer.");
                    }
                    if (n < 1 || n > task.size()) {
                        throw new JoohException("No task #" + n + " exists.");
                    }
                    Task t = task.get(n - 1);
                    t.markDone();
                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + t);
                    System.out.println(line);
                } else if (cmd.equals("unmark")) {
                    if (arg.isEmpty()) {
                        throw new JoohException("Task number must be provided.");
                    }
                    int n;
                    try {
                        n = Integer.parseInt(arg);
                    } catch (NumberFormatException e) {
                        throw new JoohException("Task number must be a positive integer.");
                    }
                    if (n < 1 || n > task.size()) {
                        throw new JoohException("No task #" + n + " exists.");
                    }
                    Task t = task.get(n - 1);
                    t.markUndone();
                    System.out.println(line);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + t);
                    System.out.println(line);
                } else if (cmd.equals("todo")) {
                    String desc = arg;
                    if (desc.isEmpty()) {
                        throw new JoohException("The description of a todo cannot be empty.");
                    }
                    Task t = new Todo(desc);
                    task.add(t);
                    System.out.println(line);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + t);
                    System.out.println("Now you have " + task.size() + " tasks in the list.");
                    System.out.println(line);
                } else if (cmd.equals("deadline")) {
                    String[] parts = arg.split("/by", 2);
                    String desc = (parts.length > 0 ? parts[0].trim() : "");
                    String by   = (parts.length > 1 ? parts[1].trim() : "");
                    if (desc.isEmpty()) {
                        throw new JoohException("The description of a deadline cannot be empty.");
                    }
                    if (by.isEmpty())   {
                        throw new JoohException("The /by of a deadline cannot be empty.");
                    }
                    Task t = new Deadline(desc, by);
                    task.add(t);
                    System.out.println(line);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + t);
                    System.out.println("Now you have " + task.size() + " tasks in the list.");
                    System.out.println(line);
                } else if (cmd.equals("event")) {
                    String[] first  = arg.split("/from", 2);
                    String desc = (first.length > 0 ? first[0].trim() : "");
                    String[] second = (first.length > 1 ? first[1].split("/to", 2) : new String[0]);
                    String from = (second.length > 0 ? second[0].trim() : "");
                    String to = (second.length > 1 ? second[1].trim() : "");
                    if (desc.isEmpty()) {
                        throw new JoohException("The description of an event cannot be empty.");
                    }
                    if (from.isEmpty() || to.isEmpty()) {
                        throw new JoohException("The /from and /to of an event cannot be empty.");
                    }
                    Task t = new Event(desc, from, to);
                    task.add(t);
                    System.out.println(line);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + t);
                    System.out.println("Now you have " + task.size() + " tasks in the list.");
                    System.out.println(line);
                }
            } catch (JoohException e) {
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println(line);
            }
        }
        sc.close();
    }
}

