import java.io.BufferedReader;
import java.io.IOException;
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

    private static void handleCommand() {
        Scanner scanner = new Scanner(System.in);
        try {
            loadList();
        } catch (DukeException e) {
            System.out.println(wrap(e.getMessage()));
        }
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
                    try {
                        updateFile();
                    } catch (Exception e){
                        System.out.println("OOPS!!! An IO exception has occurred.");
                    }
                    System.out.println(formatDone(list, index));
                }
            } else {
                //to handle regular commands
                try {
                    addTask(command);
                    updateFile();
                } catch (DukeException e){
                    System.out.println(wrap(e.getMessage()));
                }
            }
        }
    }

    private static String wrap(String content) {
        return ("\n__________________________________\n"
                + content
                + "\n__________________________________\n");
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

    private static ArrayList<String> readFile() throws DukeException {
        // reads file and returns an ArrayList of lines
        ArrayList<String> result = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get("data", "todo_list.txt"))) {
            String line;
            while ((line = br.readLine()) != null){
                result.add(line);
            }
        } catch (Exception e) {
            throw new DukeException("", "io");
        }
        return result;
    }

    private static void loadList() throws DukeException {
        // loads data into list
        ArrayList<String> data = readFile();
        for (String line: data) convertString(line);
    }

    private static void convertString(String s) throws DukeException {
        // converts a line (String) into a task
        try {
            String type = s.substring(1,2); // T, D or E
            boolean isDone = s.substring(4,5).equals("v");
            String description;
            String addendum;
            switch (type) {
                case "T":
                    description = s.substring(7);
                    ToDo todo = new ToDo(description);
                    if (isDone) todo.setDone();
                    list.add(todo);
                    break;
                case "E": {
                    String[] sections = s.substring(7).split("at:");
                    description = sections[0].substring(0, sections[0].length() - 2);
                    addendum = sections[1].substring(1, sections[1].length() - 1);
                    Event event = new Event(description, addendum);
                    if (isDone) event.setDone();
                    list.add(event);
                    break;
                }
                case "D": {
                    String[] sections = s.substring(7).split("by:");
                    description = sections[0].substring(0, sections[0].length() - 2);
                    addendum = sections[1].substring(1, sections[1].length() - 1);
                    Deadline deadline = new Deadline(description, addendum);
                    if (isDone) deadline.setDone();
                    list.add(deadline);
                    break;
                }
                default:
                    throw new DukeException("","io");
            }

        } catch (Exception e) {
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