/**
 * Custom exception class for all Duke-related exceptions.
 */
public class DukeException extends Exception {

    private String input;
    private String type = "other";

    /**
     * Constructor for DukeException for default type.
     *
     * @param input input message that triggered the exception
     */
    DukeException(String input) {
        super(input);
        this.input = input;
    }

    /**
     * Constructor for DukeException for specific Tasks, in order to provide
     * type-specific error messages.
     *
     * @param input input message that triggered the exception
     * @param type the type of the Task that was attempted to be created which
     *             caused the exception (i.e. ToDo, Event, or Deadline)
     */
    DukeException(String input, String type) {
        super(input);
        this.input = input;
        this.type = type;
    }

    /**
     * Returns the message associated with the type of DukeException that has occurred.
     *
     * @return the error message associated with the exception
     */
    public String getMessage() {

        String message = "An unknown exception has occurred.";
        String word = input.trim().equals("event") ? "an " : "a ";

        if (input.trim().equals("todo") || input.trim().equals("event") || input.trim().equals("deadline")) {
            message = "OOPS!!! The description of "
                    + word
                    + input.trim()
                    + " cannot be empty.";
        } else if (!type.equals("other")) {
            switch (type) {
            case "todo": {
                message = "OOPS!!! I'm sorry, but I don't know what that means :-(";
                break;
            }
            case "event": {
                if (!input.contains("/at")) {
                    message = "OOPS!!! Event is missing a location.";
                }
                break;
            }
            case "deadline": {
                if (!input.contains("/by")) {
                    message = "OOPS!!! Deadline is missing a deadline.";
                }
                break;
            }
            case "io": {
                message = "OOPS!!! An IO exception has occurred.";
                break;
            }
            case "empty": {
                message = "List is empty! Please enter a valid command.";
                break;
            }
            case "index": {
                message = "Invalid index! Please try again.";
                break;
            }
            default: {
                message = "OOPS!!! I'm sorry, but I don't know what that means :-(";
            }
            }
        } else {
            message = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
        return Ui.wrap(message);
        // wrap is called from Ui in order to standardize the formatting of the output
    }
}
