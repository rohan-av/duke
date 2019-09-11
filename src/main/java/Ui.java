import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private Scanner scanner = new Scanner(System.in);

    String readCommand() {
        return scanner.nextLine();
    }


    String showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo + wrap("What can I do for you?\n");
    }

    String showByeMessage() {
        return wrap("Bye. Hope to see you again soon!");
    }

    String showError(DukeException e) {
        return e.getMessage();
    }

    static String wrap(String content) {
        return ("\n__________________________________\n"
                + content
                + "\n__________________________________\n");
    }

    String formatFind(ArrayList<Task> list) {
        StringBuilder result = new StringBuilder();
        if (list.size() == 0) {
            result.append("No such results!");
        } else {
            result.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < list.size(); i++) {
                result.append(i + 1)
                        .append(". ")
                        .append(list.get(i).toString());
                if (i != list.size() - 1) {
                    result.append("\n");
                }
            }
        }
        return wrap(result.toString());
    }

    String formatList(ArrayList<Task> list) {
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

    String formatDone(ArrayList<Task> list, int index) {
        String result = "Nice! I've marked this task as done:\n "
                + list.get(index - 1).toString()
                + "\n";
        return wrap(result);
    }

    String formatDelete(ArrayList<Task> list, int index) {
        String word = (list.size() == 2) ? "task" : "tasks";
        String result = "Noted! I've removed this task:\n "
                + list.get(index - 1).toString()
                + "\n"
                + "Now you have "
                + (list.size() - 1)
                + " "
                + word
                + " in the list.";
        return wrap(result);
    }

    String formatAdd(ArrayList<Task> list, Task task) {
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

}
