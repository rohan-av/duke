public class Task {

    /**
     * Task class used in Duke. Extended by Event, To-Do, and Deadline
     */

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "v" : "x"); // returns ticks (v) and crosses (x)
    }

    /**
     * method used to return a String representation of the Task, as displayed
     * on the command line / in todo_list.txt
     */
    public String toString() {
        return "["
                + this.getStatusIcon()
                + "] "
                + this.description;
    }

    public void setDone() {
        this.isDone = true;
    }

}
