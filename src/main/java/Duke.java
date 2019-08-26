import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * A task management application that can handle events, deadlines and normal to-do tasks,
     * as well as basic exception handling.
     */
    private static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
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
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println(wrap("Bye. Hope to see you again soon!"));
                break;
            } else if (command.equals("list")) {
                System.out.println(formatList(list));
            } else if (command.length() >= 6 && command.substring(0, 4).equals("done")) {
                int index = Integer.parseInt(command.substring(5));
                if (list.size() == 0) {
                    System.out.println("List is empty! Please try again.");
                }
                if (index > list.size() || index < 1) {
                    System.out.println("Invalid index! Please try again.");
                } else {
                    list.get(index - 1).setDone();
                    System.out.println(formatDone(list, index));
                }
            } else {
                try {
                    addTask(command);
                } catch (DukeException e) {
                    System.out.println(wrap(e.getMessage()));
                }
            }
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