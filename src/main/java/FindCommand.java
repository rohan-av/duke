import java.util.ArrayList;

/**
 * A class representing the command used to find all tasks in the task list that contain the
 * inputted query.
 */
public class FindCommand extends Command {

    private String query;

    /**
     * Constructor for the Command to find a task based on inputted query.
     *
     * @param message the input message that resulted in the creation of the Command
     * @throws DukeException if an exception occurs in the parsing of the message
     */
    FindCommand(String message) throws DukeException {
        try {
            this.query = message.substring(5);
        } catch (Exception e) {
            throw new DukeException("","other");
        }
    }

    /**
     * Modifies the task list in use and returns the messages intended to be displayed.
     *
     * @param taskList the TaskList object that contains the task list
     * @param ui the Ui object that determines the displayed output of Duke
     * @param storage the storage
     * @return the string to be displayed in Duke
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> res = taskList.findTask(query);
        return ui.formatFind(res);
    }

    /**
     * Returns a boolean value representing whether the program will terminate or not, used in
     * Duke to reassign a boolean variable checked at each iteration of a while loop.
     *
     * @return a boolean value that represents whether the program will terminate after the command
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
