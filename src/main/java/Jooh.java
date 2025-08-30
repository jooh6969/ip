import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Jooh {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> task = new ArrayList<>();
        Storage storage = new Storage();
        String line = "____________________________________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Jooh, a pre-programmed ChatBot");
        System.out.println("What can I do for you today?");
        System.out.println(line);

        while (true) {
            String raw = sc.nextLine();
            try {
                InputParser.Parsed p = InputParser.Parsed.parse(raw);

                switch (p.type) {
                    case BYE: {
                        System.out.println(line);
                        System.out.println("Have a nice day! Goodbye!");
                        System.out.println(line);
                        sc.close();
                        return; // exit main
                    }

                    case LIST: {
                        System.out.println(line);
                        System.out.println("Here are the tasks in your list:");
                        if (task.isEmpty()) {
                            System.out.println("(no tasks yet)");
                        } else {
                            IntStream.range(0, task.size())
                                    .forEach(i -> System.out.println((i + 1) + ": " + task.get(i)));
                        }
                        System.out.println(line);
                        break;
                    }

                    case TODO: {
                        Task t = new Todo(p.desc, false);
                        task.add(t);
                        System.out.println(line);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + t);
                        System.out.println("Now you have " + task.size() + " tasks in the list.");
                        System.out.println(line);
                        break;
                    }

                    case DEADLINE: {
                        Task t = new Deadline(p.desc, p.by, false);
                        task.add(t);
                        System.out.println(line);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + t);
                        System.out.println("Now you have " + task.size() + " tasks in the list.");
                        System.out.println(line);
                        break;
                    }

                    case EVENT: {
                        Task t = new Event(p.desc, p.from, p.to, false);
                        task.add(t);
                        System.out.println(line);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + t);
                        System.out.println("Now you have " + task.size() + " tasks in the list.");
                        System.out.println(line);
                        break;
                    }

                    case MARK: {
                        int n = p.index;
                        if (n > task.size()) {
                            throw new JoohException("Task doesn't exist...");
                        }

                        Task t = task.get(n - 1);
                        t.markDone();
                        System.out.println(line);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + t);
                        System.out.println(line);
                        break;
                    }

                    case UNMARK: {
                        int n = p.index;
                        if (n > task.size()) {
                            throw new JoohException("Task doesn't exist...");
                        }
                        Task t = task.get(n - 1);
                        t.markUndone();
                        System.out.println(line);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + t);
                        System.out.println(line);
                        break;
                    }

                    case DELETE: {
                        int n = p.index;
                        if (n > task.size()) {
                            throw new JoohException("Task doesn't exist...");
                        }
                        String rmv = task.get(n - 1).toString();
                        task.remove(n - 1);
                        System.out.println(line);
                        System.out.println("Noted. I've removed this task");
                        System.out.println(rmv);
                        System.out.println(line);
                        break;
                    }
                }
            } catch (JoohException e) {
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println(line);
            }
            try {
                storage.save(task);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}

