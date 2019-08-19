import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> list = new ArrayList<>();

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
            if (command.equals("list")){
                System.out.println(wrap(formatList(list)));
            }
            else {
                list.add(command);
                System.out.println(wrap("added: " + command));
            }
        }
    }

    private static String wrap(String content){
        return ("\n_____________________________\n"
                + content
                + "\n_____________________________\n");
    }

    private static String formatList(ArrayList<String> list){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i<list.size(); i++){
            result.append(i + 1).append(". ").append(list.get(i));
            if (i != list.size()-1){
                result.append("\n");
            }
        }
        return result.toString();
    }
}
