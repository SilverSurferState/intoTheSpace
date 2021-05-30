import java.util.EnumSet;

public class HELPCommand extends Command {

    public HELPCommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    public boolean execute(Player player) {
        System.out.println("The onboard computer suddenly speaks to you:");
        System.out.println("Captain, use one of these commands to progress trough the ship: ");
        for (CommandWord commandWord : EnumSet.allOf(CommandWord.class)) {
            System.out.print(commandWord + " | ");

        }
        System.out.println();
        return false;
    }


}
