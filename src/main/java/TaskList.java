import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list = new ArrayList<>();

    void remove(int index) {
        list.remove(index);
    }

    void add(Task t) {
        list.add(t);
    }

    ArrayList<Task> findTask(String query){
        ArrayList<Task> result = new ArrayList<>();
        for (Task t: list){
            if (t.description.contains(query)) result.add(t);
        }
        return result;
    }

    int getSize(){
        return list.size();
    }

    ArrayList<Task> getTaskList() {
        return list;
    }

}
