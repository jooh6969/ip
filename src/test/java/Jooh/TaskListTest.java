package Jooh;

import Jooh.task.TaskList;
import Jooh.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskListTest {
    @Test
    public void taskList_addTask_increasesSize() {
        TaskList list = new TaskList();
        list.addTask(new Todo("read book", false));
        assertEquals(1, list.getSize());
    }
}