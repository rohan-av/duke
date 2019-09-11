public class ListCommand extends Command {

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.formatList(taskList.getTaskList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
