import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String desc, String from, String to, Boolean isDone) {
        super(desc, isDone);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
    }

    private LocalDateTime parseDateTime(String date) {
        String[] dateFormats = {
                "yyyy-MM-dd HHmm",
                "MM/dd/yyyy HHmm",
                "yyyy-MM-dd HH:mm",
                "MM/dd/yyyy HH:mm"
        };
        for (String format :dateFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                return LocalDateTime.parse(date, formatter);
            } catch (DateTimeParseException e) {
                //skip, do nothing here
            }
        }
        throw new IllegalArgumentException("Invalid date format");
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E] " + super.toString() +
                " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    public String getFrom() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return from.format(formatter);
    }

    public String getTo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return to.format(formatter);
    }
}
