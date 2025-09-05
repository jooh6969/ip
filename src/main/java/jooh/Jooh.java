package jooh;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import jooh.exception.JoohException;
import jooh.parser.Parser;
import jooh.storage.Storage;
import jooh.task.Deadline;
import jooh.task.Event;
import jooh.task.Task;
import jooh.task.TaskList;
import jooh.task.Todo;
import jooh.ui.Ui;

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
                    case BYE:
                        ui.goodbyeMsg();
                        sc.close();
                        return;

                    case LIST:
                        ui.listTasksMsg(taskList.getTaskList());
                        break;

                    case TODO:
                        Task t1 = new Todo(p.desc, false);
                        taskList.addTask(t1);
                        ui.addTaskMsg(t1, taskList.getSize());
                        break;

                    case DEADLINE:
                        Task t2 = new Deadline(p.desc, p.by, false);
                        taskList.addTask(t2);
                        ui.addTaskMsg(t2, taskList.getSize());
                        break;

                    case EVENT:
                        Task t3 = new Event(p.desc, p.from, p.to, false);
                        taskList.addTask(t3);
                        ui.addTaskMsg(t3, taskList.getSize());
                        break;

                    case MARK: {
                        int n = p.index;
                        if (n > taskList.getSize()) {
                            throw new JoohException("Task doesn't exist...");
                        }
                        Task t4 = taskList.getTask(n - 1);
                        taskList.markTaskDone(n - 1);
                        ui.taskMarkedDoneMsg(t4);
                        break;
                    }

                    case UNMARK: {
                        int n = p.index;
                        if (n > taskList.getSize()) {
                            throw new JoohException("Task doesn't exist...");
                        }
                        Task t5 = taskList.getTask(n - 1);
                        taskList.markTaskUndone(n - 1);
                        ui.taskMarkedUndoneMsg(t5);
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

                    case FIND:
                        List<Task> matches = taskList.findTasks(p.desc);
                        ui.findTasksMsg(matches);
                        break;

                    default:
                        throw new JoohException("Unknown command type: " + p.type);
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


