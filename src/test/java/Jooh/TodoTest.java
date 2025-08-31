package Jooh;

import Jooh.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todo_toString_correctFormat() {
        Todo todo = new Todo("buy milk", false);
        assertEquals("[T] [ ] buy milk", todo.toString());
    }
}
