public class ByeCommand extends Command {

    private boolean exit = false;

    public void execute(TaskList taskList, Ui ui, Storage storage){
        ui.showByeMessage();
        exit = true;
    }

    public boolean isExit() {
        return exit;
    }

}
