import java.util.Locale;

//class splits the input up and returns me a parsed object, can access the fields for the constructors of task
public class InputParser {
    public enum Type {
        BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE,
    }

    public static final class Parsed {
        public final Type type;
        public final String desc;
        public final String by;
        public final String from;
        public final String to;
        public final Integer index;

        private Parsed(Type type, String desc, String by, String from, String to, Integer index) {
            this.type = type;
            this.desc = desc;
            this.by = by;
            this.from = from;
            this.to = to;
            this.index = index;
        }

        public static Parsed bye() {
            return new Parsed(Type.BYE, null, null, null, null, null);
        }
        public static Parsed list() {
            return new Parsed(Type.LIST, null, null, null, null, null);
        }
        public static Parsed todo(String desc) {
            return new Parsed(Type.TODO, desc, null, null, null, null);
        }
        public static Parsed deadline(String desc, String by) {
            return new Parsed(Type.DEADLINE, desc, by, null, null, null);
        }
        public static Parsed event(String desc, String from, String to) {
            return new Parsed(Type.EVENT, desc, null, from, to, null);
        }
        public static Parsed mark(int i) {
            return new Parsed(Type.MARK, null, null, null, null, i);
        }
        public static Parsed unmark(int i) {
            return new Parsed(Type.UNMARK, null, null, null, null, i);
        }
        public static Parsed delete(int i) {
            return new Parsed(Type.DELETE, null, null, null, null, i);
        }

        //method here breaks the inputs, passes parts to constructor
        public static Parsed parse(String input) throws JoohException {
            if (input == null) input = "";
            input = input.trim();   //trim removes last spacing
            if (input.isEmpty()) throw new JoohException("Please key in something...");

            // \\s+ is the regex for one or more white spaces
            String[] parts = input.split("\\s+", 2);
            String cmd = parts[0].toLowerCase(Locale.ROOT);
            String rest = parts.length > 1 ? parts[1].trim() : "";

            switch(cmd) {
                case "bye": {
                    return Parsed.bye();
                }
                case "list": {
                    return Parsed.list();
                }
                case "todo": {
                    if (rest.isEmpty()) {
                        throw new EmptyDescriptionException("todo");
                    }
                    return Parsed.todo(rest);
                }
                case "deadline": {
                    String[] pair = rest.split("(?i)\\s*/by\\s+", 2);
                    String desc = pair.length > 0 ? pair[0].trim() : "";
                    String by = pair.length > 1 ? pair[1].trim() : "";
                    if (desc.isEmpty()) {
                        throw new EmptyDescriptionException("deadline");
                    }
                    if (by.isEmpty()) {
                        throw new InvalidDeadlineException();
                    }
                    return Parsed.deadline(desc, by);
                }
                case "event": {
                    String[] pair = rest.split("(?i)\\s*/from\\s+", 2);
                    String desc = pair.length > 0 ? pair[0].trim() : "";
                    String remaining = pair.length > 1 ? pair[1] : "";
                    String[] fromAndTo = remaining.split("(?i)\\s*/to\\s+", 2);
                    String from = fromAndTo.length > 0 ? fromAndTo[0].trim() : "";
                    String to = fromAndTo.length > 1 ? fromAndTo[1].trim() : "";
                    if (desc.isEmpty()) {
                        throw new EmptyDescriptionException("event");
                    }
                    if (from.isEmpty() || to.isEmpty()) {
                        throw new InvalidEventTimelineException();
                    }
                    return Parsed.event(desc, from, to);
                }
                case "mark":
                case "unmark":
                case "delete": {
                    if (rest.isEmpty()) {
                        throw new JoohException("Task number must be provided.");
                    }
                    int n;
                    try {
                        n = Integer.parseInt(rest);
                    }
                    catch (NumberFormatException e) {
                        throw new JoohException("Task number must be a positive integer.");
                    }
                    if (n < 1) {
                        throw new JoohException("Task number must be positive.");
                    }
                    if (cmd.equals("mark"))   {
                        return Parsed.mark(n);
                    }
                    if (cmd.equals("unmark")) {
                        return Parsed.unmark(n);
                    }
                    return Parsed.delete(n);
                }
                default: {
                    throw new JoohException("Unknown command: " + cmd);
                }

            }
        }
    }
}
