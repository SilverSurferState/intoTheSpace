public class BACKCommand extends Command {

    public BACKCommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    public boolean execute(Player player) {
        if (!player.getRoute().empty()) {
            player.setCurrentRoom(player.getRoute().pop());
            System.out.println(player.getCurrentRoom().getLongDescription());
        }
        return false;
    }


}
