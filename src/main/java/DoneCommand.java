public class DoneCommand extends Command {

    private int index;

    DoneCommand(String message) throws DukeException {
        this.message = message;
        try {
            index = Integer.parseInt(message.substring(5));
        } catch (Exception e) {
            throw new DukeException("","other");
        }
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.getSize() == 0) {
            System.out.println("List is empty! Please enter a valid command.");
        }
        if (index > taskList.getSize() || index < 1) {
            System.out.println("Invalid index! Please try again.");
        } else {
            taskList.getTaskList().get(index - 1).setDone();
            try {
                storage.updateFile(taskList);
            } catch (Exception e){
                System.out.println("OOPS!!! An IO exception has occurred.");
            }
            ui.formatDone(taskList.getTaskList(), index);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
