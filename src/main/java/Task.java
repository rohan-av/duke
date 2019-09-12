/**
 * Task class used in Duke. Extended by Event, ToDo, and Deadline.
 */
public class Task {

    String description;
    private boolean isDone;

    /**
     * Constructor for the Task object, which is not used due to the further categorization
     * of Task objects into the inherited ToDo, Event and Deadline objects that extend the ToDo Object.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    /**
     * Returns the icon of the task that represents whether the task is done or not.
     * v represents the task being done.
     * x represents the task being not done.
     *
     * @return the status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "v" : "x"); // returns ticks (v) and crosses (x)
    }

    /**
     * Return a String representation of the Task, as displayed
     * on the command line / in todo_list.txt
     *
     * @return a String representation of the Task object
     */
    public String toString() {
        return "["
                + this.getStatusIcon()
                + "] "
                + this.description;
    }

    /**
     * Sets the task as done. Note that conversion back to an un-done state is perceived
     * to be unnecessary as it does not make sense for done tasks to be un-done.
     */
    public void setDone() {
        this.isDone = true;
    }

}
