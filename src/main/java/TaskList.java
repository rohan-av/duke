import java.util.ArrayList;

/**
 * Class used to store the task list and perform necessary manipulations to the task list such as
 * adding tasks, removing tasks and finding tasks based on keywords, as well as obtaining the size
 * of the task list.
 */
public class TaskList {

    private ArrayList<Task> list = new ArrayList<>();

    /**
     * Removes an element from the task list.
     *
     * @param index the index of the Task in the task list that is to be removed
     */
    void remove(int index) {
        list.remove(index);
    }

    /**
     * Adds an element to the task list.
     *
     * @param t the Task object to be added to the task list.
     */
    void add(Task t) {
        list.add(t);
    }

    /**
     * Returns a subset of the task list (implemented as an ArrayList of Task objects) that contains
     * the query specified in the argument.
     *
     * @param query the search query to be obtained from the input command
     * @return the ArrayList of Task objects whose description contained the query
     */
    ArrayList<Task> findTask(String query) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task t: list) {
            if (t.description.contains(query)) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * Returns the current size of the task list.
     *
     * @return the current size of the task list.
     */
    int getSize() {
        return list.size();
    }

    /**
     * Returns the task list for Duke, which is implemented as an ArrayList of Task objects.
     *
     * @return the task list
     */
    ArrayList<Task> getTaskList() {
        return list;
    }

}
