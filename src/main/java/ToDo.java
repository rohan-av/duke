/**
 * A class inheriting from Task used to represent tasks that has just a description.
 */
public class ToDo extends Task {

    /**
     * Constructor used to create the ToDo object, which contains only a description of the task.
     *
     * @param description the description of the task
     */
    ToDo(String description) {
        super(description);
    }

    /**
     * Returns a String representation of the ToDo object, displaying its type (ToDo),
     * and description.
     *
     * @return a String representation of the ToDo object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}