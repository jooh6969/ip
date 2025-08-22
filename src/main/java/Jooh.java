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

        while(true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(line);
                System.out.println("Have a nice day! Goodbye!");
                System.out.println(line);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                IntStream.range(0, task.size())
                        .forEach(i -> System.out.println(i + 1 + ": " + task.get(i)));
                System.out.println(line);
            } else if (input.startsWith("mark ")) {
                String[] parts = input.split(" ");
                int idx = Integer.parseInt(parts[1]) - 1;
                Task t = task.get(idx);
                t.markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(t);
            } else if (input.startsWith("unmark ")){
                String[] parts = input.split(" ");
                int idx = Integer.parseInt(parts[1]) - 1;
                Task t = task.get(idx);
                t.markUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(t);
            } else if (input.toLowerCase().startsWith("todo ")) {
                String desc = input.substring(5).trim(); // after "todo "
                Task t = new Todo(desc);
                task.add(t);
                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + t);
                System.out.println("Now you have " + task.size() + " tasks in the list.");
                System.out.println(line);
            } else if (input.toLowerCase().startsWith("deadline ")) {
                String body = input.substring(9).trim();
                String[] parts = body.split("/by", 2);
                String desc = parts[0].trim();
                String by = parts[1].trim();
                Task t = new Deadline(desc, by);
                task.add(t);
                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + t);
                System.out.println("Now you have " + task.size() + " tasks in the list.");
                System.out.println(line);
            } else if (input.toLowerCase().startsWith("event ")) {
                String body = input.substring(6).trim();
                String[] first = body.split("/from", 2);
                String desc = first[0].trim();
                String[] second = first[1].split("/to", 2);
                String from = second[0].trim();
                String to = second[1].trim();
                Task t = new Event(desc, from, to);
                task.add(t);
                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + t);
                System.out.println("Now you have " + task.size() + " tasks in the list.");
                System.out.println(line);
            }

    }
        sc.close();


    }
}
