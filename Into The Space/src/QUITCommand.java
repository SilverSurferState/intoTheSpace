public class QUITCommand extends Command {

    public QUITCommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    public boolean execute(Player player) {
        return true;
    }

}
