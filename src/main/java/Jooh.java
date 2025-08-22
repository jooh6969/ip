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
                t.mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(t);
            } else if (input.startsWith("unmark ")){
                String[] parts = input.split(" ");
                int idx = Integer.parseInt(parts[1]) - 1;
                Task t = task.get(idx);
                t.mark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(t);
            } else {
                task.add(new Task(input));
                System.out.println(line);
                System.out.println("added: " + input);
                System.out.println(line);
            }
        }
        sc.close();


    }
}
