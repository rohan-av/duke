import java.util.ArrayList;

public class DeleteCommand extends Command {

    private int index;

    DeleteCommand(String message) throws DukeException {
        this.message = message;
        try {
            index = Integer.parseInt(message.substring(7));
        } catch (Exception e) {
            throw new DukeException("","other");
        }
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskList.getSize() == 0) {
            throw new DukeException("","empty");
        }
        if (index > taskList.getSize() || index < 1) {
            throw new DukeException("","index");
        } else {
            ArrayList<Task> temp = taskList.getTaskList();
            taskList.remove(index - 1);
            try {
                storage.updateFile(taskList);
            } catch (Exception e) {
                throw new DukeException("","io");
            }
            return ui.formatDelete(temp, index);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
