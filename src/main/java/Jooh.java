import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Jooh {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> str = new ArrayList<>();
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
                IntStream.range(0, str.size())
                        .forEach(i -> System.out.println(i + 1 + ": " + str.get(i)));
                System.out.println(line);
            } else {
                str.add(input);
                System.out.println(line);
                System.out.println("added: " + input);
                System.out.println(line);
            }
        }
        sc.close();


    }
}
