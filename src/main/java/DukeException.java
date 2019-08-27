public class DukeException extends Exception {
    /**
     * Custom exception class for all Duke-related exceptions.
     */
    private String input;
    private String type = "other";

    DukeException(String input) {
        super(input);
        this.input = input;
    }

    DukeException(String input, String type) {
        super(input);
        this.input = input;
        this.type = type;
    }

    /**
     * code logic to determine the displayed message.
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
                default: {
                    message = "OOPS!!! I'm sorry, but I don't know what that means :-(";
                }
            }
        } else {
            message = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
        return message;
    }
}
