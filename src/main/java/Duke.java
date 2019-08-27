import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * A task management application that can handle events, deadlines and normal to-do tasks,
     * as well as basic exception handling.
     */
    private static ArrayList<Task> list = new ArrayList<>();

    /**
     * main function to execute Duke.
     * @param args standard params
     * @throws DukeException a custom exception class for all Duke-related exceptions
     */
    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + wrap("What can I do for you?\n"));
        handleCommand();
    }

    private static void handleCommand() throws DukeException {
        Scanner scanner = new Scanner(System.in);
        try {
            while (scanner.hasNext()) {
                String command = scanner.nextLine();
                if (command.equals("bye")) {
                    //to terminate the program
                    System.out.println(wrap("Bye. Hope to see you again soon!"));
                    break;
                } else if (command.equals("list")) {
                    //to print out the list
                    System.out.println(formatList(list));
                } else if (command.length() >= 6 && command.substring(0, 4).equals("done")) {
                    //to mark tasks as done
                    int index = Integer.parseInt(command.substring(5));
                    if (list.size() == 0) {
                        System.out.println("List is empty! Please try again.");
                    }
                    if (index > list.size() || index < 1) {
                        System.out.println("Invalid index! Please try again.");
                    } else {
                        list.get(index - 1).setDone();
                        updateFile();
                        System.out.println(formatDone(list, index));
                    }
                } else {
                    //to handle regular commands
                    addTask(command);
                    updateFile();
                }
            }
        } catch (DukeException e) {
            System.out.println(wrap(e.getMessage()));
        }
    }

    private static String wrap(String content) {
        return ("\n_____________________________\n"
                + content
                + "\n_____________________________\n");
    }

    private static String formatList(ArrayList<Task> list) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            result.append(i + 1)
                    .append(". ")
                    .append(list.get(i).toString());
            if (i != list.size() - 1) {
                result.append("\n");
            }
        }
        if (list.size() == 0) {
            result.append("The list is empty!");
        }
        return wrap(result.toString());
    }

    private static String formatDone(ArrayList<Task> list, int index) {
        String result = "Nice! I've marked this task as done:\n "
                + list.get(index - 1).toString()
                + "\n";
        return wrap(result);
    }

    private static String formatAdd(Task task) {
        String word = (list.size() == 1) ? "task" : "tasks";
        String result = "Got it. I've added this task:\n  "
                + task.toString()
                + "\nNow you have "
                + list.size()
                + " "
                + word
                + " in the list.";
        return wrap(result);
    }

    private static ArrayList<String> formatFile(ArrayList<Task> list) {
        ArrayList<String> result = new ArrayList<>();
        for (Task task : list) {
            result.add(task.toString());
        }
        return result;
    }

    private static void writeFile(ArrayList<String> tasks) throws DukeException {
        Path file = Paths.get("data","todo_list.txt");
        try {
            Files.write(file, tasks, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DukeException("","io");
        }
    }

    private static void updateFile() throws DukeException {
        writeFile(formatFile(list));
    }

    private static void addTask(String command) throws DukeException {
        String identifier;
        try {
            identifier = command.substring(0, 4);
        } catch (Exception e) {
            throw new DukeException(command); //length is less than 4
        }
        switch (identifier) {
            case "todo": {
                ToDo todo;
                if (command.length() < 5 || !command.substring(4,5).equals(" ")) {
                    throw new DukeException(command);
                }
                try {
                    todo = new ToDo(command.trim().substring(5));
                    list.add(todo);
                    System.out.println(formatAdd(todo));
                } catch (Exception e) {
                    throw new DukeException(command, "todo"); //empty to-do
                }
                break;
            }
            case "dead": {
                if (command.length() < 9 || !command.substring(4,9).equals("line ")) { //exception if not fully spelt
                    throw new DukeException(command);
                }
                Deadline deadline;
                try {
                    String[] sections = command.substring(9).split(" /by ");
                    deadline = new Deadline(sections[0], sections[1]);
                    list.add(deadline);
                    System.out.println(formatAdd(deadline));
                } catch (Exception e) {
                    throw new DukeException(command,"deadline");
                }
                break;
            }
            case "even": {
                if (command.length() < 6 || !command.substring(4,6).equals("t ")) { //exception if not fully spelt
                    throw new DukeException(command);
                }
                Event event;
                try {
                    String[] sections = command.substring(6).split(" /at ");
                    event = new Event(sections[0], sections[1]);
                    list.add(event);
                    System.out.println(formatAdd(event));
                } catch (Exception e) {
                    throw new DukeException(command, "event");
                }
                break;
            }
            default: {
                throw new DukeException(command);
            }
        }
    }
}