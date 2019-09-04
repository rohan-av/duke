import java.util.ArrayList;

public class FindCommand extends Command {

    private String query;

    FindCommand(String message) throws DukeException {
        try {
            this.query = message.substring(5);
        } catch (Exception e) {
            throw new DukeException("","other");
        }
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> res = taskList.findTask(query);
        ui.formatFind(res);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
