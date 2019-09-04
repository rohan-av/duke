class Parser {

    static Command parse(String message) throws DukeException {
        if (message.equals("bye")){
            return new ByeCommand();
        } else if (message.equals("list")) {
            return new ListCommand();
        } else if (message.length() >= 8 && message.substring(0, 6).equals("delete")) {
            return new DeleteCommand(message);
        } else if (message.length() >= 6 && message.substring(0,4).equals("find")) {
            return new FindCommand(message);
        } else if (message.length() >= 6 && message.substring(0, 4).equals("done")) {
            return new DoneCommand(message);
        } else {
            return new AddCommand(message);
        }
    }

}
