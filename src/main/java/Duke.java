import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + wrap("What can I do for you?\n"));
        echo();
    }
    private static void echo(){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String command = scanner.nextLine();
            if (command.equals("bye")){
                System.out.println(wrap("Bye. Hope to see you again soon!"));
                break;
            }
            System.out.println(wrap(command));
        }
    }

    private static String wrap(String content){
        return ("\n__________________________\n"
                + content
                + "\n__________________________\n");
    }
}
