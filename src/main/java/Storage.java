import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

class Storage {

    private Path file;

    Storage(Path file) {
        this.file = file;
    }

    private ArrayList<String> formatFile(ArrayList<Task> list) {
        ArrayList<String> result = new ArrayList<>();
        for (Task task : list) {
            result.add(task.toString());
        }
        return result;
    }

    private void writeFile(ArrayList<String> tasks) throws DukeException {
        try {
            Files.write(file, tasks, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DukeException("","io");
        }
    }

    private ArrayList<String> readFile() throws DukeException {
        // reads file and returns an ArrayList of lines
        ArrayList<String> result = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(file)) {
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        } catch (Exception e) {
            throw new DukeException("", "io");
        }
        return result;
    }

    void loadList(TaskList taskList) throws DukeException {
        // loads data into list
        ArrayList<String> data = readFile();
        for (String line: data) {
            convertString(taskList, line);
        }
    }

    private void convertString(TaskList taskList, String s) throws DukeException {
        // converts a line (String) into a task
        try {
            String type = s.substring(1,2); // T, D or E
            boolean isDone = s.substring(4,5).equals("v");
            String description;
            String addendum;
            switch (type) {
            case "T":
                description = s.substring(7);
                ToDo todo = new ToDo(description);
                if (isDone) {
                    todo.setDone();
                }
                taskList.add(todo);
                break;
            case "E": {
                String[] sections = s.substring(7).split("at:");
                description = sections[0].substring(0, sections[0].length() - 2);
                addendum = sections[1].substring(1, sections[1].length() - 1);
                Event event = new Event(description, addendum);
                if (isDone) {
                    event.setDone();
                }
                taskList.add(event);
                break;
            }
            case "D": {
                String[] sections = s.substring(7).split("by:");
                description = sections[0].substring(0, sections[0].length() - 2);
                addendum = sections[1].substring(1, sections[1].length() - 1);
                Deadline deadline = new Deadline(description, addendum);
                if (isDone) {
                    deadline.setDone();
                }
                taskList.add(deadline);
                break;
            }
            default:
                throw new DukeException("","io");
            }

        } catch (Exception e) {
            throw new DukeException("","io");
        }
    }

    void updateFile(TaskList taskList) throws DukeException {
        writeFile(formatFile(taskList.getTaskList()));
    }

}
