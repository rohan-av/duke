import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Duke {
    /**
     * A chat bot cum task management application that can handle events, deadlines and normal to-do tasks,
     * as well as basic exception handling.
     */
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Duke(Path file) {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(file);
        try {
            storage.loadList(tasks);
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    private void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke(Paths.get("data","todo_list.txt")).run();
    }
    
}