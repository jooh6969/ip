package Jooh.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String desc, String deadline, Boolean isDone) {
        super(desc, isDone);
        this.deadline = parseDateTime(deadline);
    }

    private LocalDateTime parseDateTime(String deadline) {
        String[] dateFormats = {
                "yyyy-MM-dd HHmm",
                "MM/dd/yyyy HHmm",
                "yyyy-MM-dd HH:mm",
                "MM/dd/yyyy HH:mm"
        };
        for (String format :dateFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                return LocalDateTime.parse(deadline, formatter);
            } catch (DateTimeParseException e) {
                //skip, do nothing here
            }
        }
        throw new IllegalArgumentException("Invalid date format");
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyy HH:mm");
        return "[D] " + super.toString() + " (by: " + deadline.format(formatter) + ")";
    }

    public String getDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return deadline.format(formatter);
    }
}
