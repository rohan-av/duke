public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "v" : "x"); // returns ticks (v) and crosses (x)
    }

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
