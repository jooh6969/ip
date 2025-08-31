package Jooh;

import Jooh.ui.Ui;
import Jooh.storage.Storage;
import Jooh.parser.Parser;
import Jooh.task.Task;
import Jooh.task.TaskList;
import Jooh.task.Todo;
import Jooh.task.Deadline;
import Jooh.task.Event;
import Jooh.exception.JoohException;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
// Code follows SE-EDU Java coding standards

/**
 * Entry point for the Jooh chatbot application.
 * Initializes the user interface, storage, and task list,
 * then runs an interactive loop to process user commands until exit.
 */
public class Jooh {

    /**
     * Launches the Jooh chatbot.
     * Sets up storage, loads previously saved tasks, and enters
     * a command-processing loop that responds to user input.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();
        Storage storage = new Storage();
        Ui ui = new Ui();
        ui.welcomeMsg();

        try {
            List<Task> loaded = storage.load();
            loaded.forEach(taskList::addTask);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        while (true) {
            String raw = sc.nextLine();
            try {
                Parser.Parsed p = Parser.Parsed.parse(raw);
                switch (p.type) {
                    case BYE: {
                        ui.goodbyeMsg();
                        sc.close();
                        return;
                    }
                    case LIST: {
                        ui.listTasksMsg(taskList.getTaskList());
                        break;
                    }
                    case TODO: {
                        Task t = new Todo(p.desc, false);
                        taskList.addTask(t);
                        ui.addTaskMsg(t, taskList.getSize());
                        break;
                    }
                    case DEADLINE: {
                        Task t = new Deadline(p.desc, p.by, false);
                        taskList.addTask(t);
                        ui.addTaskMsg(t, taskList.getSize());
                        break;
                    }
                    case EVENT: {
                        Task t = new Event(p.desc, p.from, p.to, false);
                        taskList.addTask(t);
                        ui.addTaskMsg(t, taskList.getSize());
                        break;
                    }
                    case MARK: {
                        int n = p.index;
                        if (n > taskList.getSize()) {
                            throw new JoohException("Task doesn't exist...");
                        }
                        Task t = taskList.getTask(n - 1);
                        taskList.markTaskDone(n - 1);
                        ui.taskMarkedDoneMsg(t);
                        break;
                    }
                    case UNMARK: {
                        int n = p.index;
                        if (n > taskList.getSize()) {
                            throw new JoohException("Task doesn't exist...");
                        }
                        Task t = taskList.getTask(n - 1);
                        taskList.markTaskUndone(n - 1);
                        ui.taskMarkedUndoneMsg(t);
                        break;
                    }
                    case DELETE: {
                        int n = p.index;
                        if (n > taskList.getSize()) {
                            throw new JoohException("Task doesn't exist...");
                        }
                        String rmv = taskList.getTaskAsString(n - 1);
                        taskList.removeTask(n - 1);
                        ui.taskRemovedMsg(rmv);
                        break;
                    }
                    case FIND: {
                        List<Task> matches = taskList.findTasks(p.desc);
                        ui.findTasksMsg(matches);
                        break;
                    }
                }
            } catch (JoohException e) {
                System.out.println(e.getMessage());
            }
            try {
                storage.save(taskList.getTaskList());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}


