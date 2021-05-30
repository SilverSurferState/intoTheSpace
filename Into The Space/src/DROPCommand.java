public class DROPCommand extends Command {
    public DROPCommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    public boolean execute(Player player) {
        if (!hasSecondWord()) {
            System.out.println("Drop what?");
        }
        Item item = player.drop(getSecondWord());
        if (item != null) {
            Room room = player.getCurrentRoom();
            room.addItem(item);
            player.getItems().remove(item.getItemName());
        } else {
            System.out.println("Drop what?");
        }
        return false; 
    }
}
