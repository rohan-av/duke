public class DukeException extends Exception{

    private String input;

    DukeException(String input){
        super(input);
        this.input = input;
    }

    public String getMessage() {

        if (input.trim().equals("todo") || input.trim().equals("event") || input.trim().equals("deadline")) {
            return "OOPS!!! The description of a "
                + input.trim()
                + " cannot be empty.";
        } else {
            return "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }
}
