public class AddCommand extends Command {

    AddCommand(String message) {
        this.message = message;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String identifier;
        try {
            identifier = message.substring(0, 4);
        } catch (Exception e) {
            throw new DukeException("","other");
        }
        switch (identifier) {
            case "todo": {
                ToDo todo;
                if (message.length() < 5 || !message.substring(4,5).equals(" ")) {
                    throw new DukeException(message);
                }
                try {
                    todo = new ToDo(message.trim().substring(5));
                    taskList.add(todo);
                    storage.updateFile(taskList);
                    return ui.formatAdd(taskList.getTaskList(), todo);
                } catch (Exception e) {
                    throw new DukeException(message, "todo"); //empty to-do
                }
            }
            case "dead": {
                if (message.length() < 9 || !message.substring(4,9).equals("line ")) { //exception if not fully spelt
                    throw new DukeException(message);
                }
                Deadline deadline;
                try {
                    String[] sections = message.substring(9).split(" /by ");
                    deadline = new Deadline(sections[0], sections[1]);
                    taskList.add(deadline);
                    storage.updateFile(taskList);
                    return ui.formatAdd(taskList.getTaskList(), deadline);
                } catch (Exception e) {
                    throw new DukeException(message,"deadline");
                }
            }
            case "even": {
                if (message.length() < 6 || !message.substring(4,6).equals("t ")) { //exception if not fully spelt
                    throw new DukeException(message);
                }
                Event event;
                try {
                    String[] sections = message.substring(6).split(" /at ");
                    event = new Event(sections[0], sections[1]);
                    taskList.add(event);
                    storage.updateFile(taskList);
                    return ui.formatAdd(taskList.getTaskList(), event);
                } catch (Exception e) {
                    throw new DukeException(message, "event");
                }
            }
            default: {
                throw new DukeException(message);
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
