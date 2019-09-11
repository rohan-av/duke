abstract class Command {

    String message;

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
    /*
    Types of Commands:
        addCommand
        deleteCommand
        listCommand
        doneCommand
        findCommand
        byeCommand
    */

}
