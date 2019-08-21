import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
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
    private static void handleCommand(){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String command = scanner.nextLine();
            if (command.equals("bye")){
                System.out.println(wrap("Bye. Hope to see you again soon!"));
                break;
            }
            else if (command.equals("list")){
                System.out.println(formatList(list));
            }
            else if (command.length() >= 5 && command.substring(0,4).equals("done")){
                int index = Integer.parseInt(command.substring(5));
                if (list.size() == 0){
                    System.out.println("List is empty! Please try again.");
                }
                if (index > list.size() || index < 1){
                    System.out.println("Invalid index! Please try again.");
                }
                else {
                    list.get(index - 1).setDone();
                    System.out.println(formatDone(list, index));
                }
            }
            else {
                list.add(new Task(command));
                System.out.println(wrap("added: " + command));
            }
        }
    }

    private static String wrap(String content){
        return ("\n_____________________________\n"
                + content
                + "\n_____________________________\n");
    }

    private static String formatList(ArrayList<Task> list){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i<list.size(); i++){
            result.append(i + 1)
                    .append(". [")
                    .append(list.get(i).getStatusIcon())
                    .append("] ")
                    .append(list.get(i).getDescription());
            if (i != list.size()-1){
                result.append("\n");
            }
        }
        return wrap(result.toString());
    }

    private static String formatDone(ArrayList<Task> list, int index){
        String result = "Nice! I've marked this task as done:\n  " +
                "[" +
                list.get(index - 1).getStatusIcon() +
                "] " +
                list.get(index - 1).getDescription() +
                "\n";
        return wrap(result);
    }
}
