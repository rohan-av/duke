import java.util.ArrayList;
import java.util.Collections;

/**
 * A class that represents the command to delete an item from the task list.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructor for the Command created to delete a task from the TaskList
     *
     * @param message the input message that resulted in the creation of the Command
     * @throws DukeException if an exception occurs in the parsing of the message
     */
    DeleteCommand(String message) throws DukeException {
        this.message = message;
        try {
            index = Integer.parseInt(message.substring(7));
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
     * @throws DukeException if an exception occurs in the parsing of the message or in IO
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskList.getSize() == 0) {
            throw new DukeException("","empty");
        }
        if (index > taskList.getSize() || index < 1) {
            throw new DukeException("","index");
        } else {
            ArrayList<Task> temp = new ArrayList<>(taskList.getTaskList());
            taskList.remove(index - 1);
            try {
                storage.updateFile(taskList);
            } catch (Exception e) {
                throw new DukeException("","io");
            }
            return ui.formatDelete(temp, index);
        }
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
