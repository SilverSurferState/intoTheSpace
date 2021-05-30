public class UNKNOWNCommand extends Command {
    public UNKNOWNCommand(CommandWord firstWord, String secondWord){
        super(firstWord, secondWord);
    }

    public boolean execute(Player player) {
        System.out.println("I don't know what you mean...");
        return false;
    }

}
