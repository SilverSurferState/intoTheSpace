public enum CommandWord {
    GO("go"), QUIT("quit"), LOOK("look"), BACK("back"), EAT("eat"), UNKNOWN("?"), DROP("drop"), PICKUP("pickup"), HELP("help"),
    LOAD("load"), FIRE("fire"), USE("use");

    private final String commandString;

    /**
     * Constructor zonder public -- Enum == instantieloos
     * @param commandString
     */
    CommandWord(String commandString){
        this.commandString = commandString;
    }

    public String toString(){
        return commandString;
    }




}
