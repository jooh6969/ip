import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path file = Paths.get("data", "Jooh.txt");

    private void checkFiles() throws IOException {
        Path parent = file.getParent();
        if (parent != null && Files.notExists(parent)) {
            Files.createDirectories(parent);
        }
        if (Files.notExists(file)) {
            Files.createFile(file);
        }
    }

    public List<Task> load() throws IOException {
        checkFiles();
        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        List<Task> tasks = new ArrayList<>();
        for (String raw : lines) {
            String line = raw.trim();
            if (line.isEmpty()) continue;
            tasks.add(decode(line));
        }
        return tasks;
    }

    public void save(List<Task> tasks) throws IOException {
        checkFiles();
        List<String> out = new ArrayList<>();
        for (Task task : tasks) {
            out.add(encode(task));
        }
        Files.write(file, out, StandardCharsets.UTF_8);
    }

    private static String encode(Task task) {
        String done = task.getIsDone() ? "1" : "0";
        if (task instanceof Todo) {
            return String.join(" | ", "T", done, task.getDesc());
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return String.join(" | ", "D", done, deadline.getDesc(), deadline.getDeadline());
        } else {
            Event event = (Event) task;
            return String.join(" | ", "E", done, event.getDesc(), event.getFrom(), event.getTo());
        }
    }

    //reverts a line saved to a task
    private static Task decode(String task) {
        String[] parsed = task.split("\\s*\\|\\s*");
        if (parsed.length < 3 ) {
            throw new IllegalArgumentException("Line's broken");
        }
        String type = parsed[0];
        Boolean done = "1".equals(parsed[1]);
        String desc = parsed[2];
        Task t;
        switch(type) {
            case "T": {
                t = new Todo(desc, done);
                break;
            }
            case "D": {
                if (parsed.length < 4) {
                    throw new IllegalArgumentException("Line's broken");
                }
                t = new Deadline(desc, parsed[3], done);
                break;
            }
            case "E": {
                if (parsed.length < 5) {
                    throw new IllegalArgumentException("Line's broken");
                }
                t = new Event(desc, parsed[3], parsed[4], done);
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown task type");
            }
        }
        return t;

    }
}
