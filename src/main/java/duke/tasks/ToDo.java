package duke.tasks;

/**
 * A class inheriting from duke.tasks.Task used to represent tasks that has just a description.
 */
public class ToDo extends Task {

    /**
     * Constructor used to create the duke.tasks.ToDo object, which contains only a description of the task.
     *
     * @param description the description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a String representation of the duke.tasks.ToDo object, displaying its type (duke.tasks.ToDo),
     * and description.
     *
     * @return a String representation of the duke.tasks.ToDo object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}