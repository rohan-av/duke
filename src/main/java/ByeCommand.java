public class ByeCommand extends Command {

    private boolean exit = false;

    public String execute(TaskList taskList, Ui ui, Storage storage){
        exit = true;
        return ui.showByeMessage();
    }

    public boolean isExit() {
        return exit;
    }

}
