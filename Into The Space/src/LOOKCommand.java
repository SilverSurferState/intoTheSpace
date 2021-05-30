import java.sql.SQLOutput;

public class LOOKCommand extends Command {

    public LOOKCommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    public boolean execute(Player player) {
        if (!hasSecondWord()) {
            System.out.println(player.getCurrentRoom().getLongDescription());
            player.printBackPackContents();
        } else if (getSecondWord().equals("backpack")) {
            player.printBackPackContents();
        } else if (getSecondWord().equals("room")) {
            System.out.println(player.getCurrentRoom().getLongDescription());
        } else lookAtItem(player,getSecondWord());
        return false;
    }


    private boolean lookAtItem(Player player, String itemName) {
        String print = "";
        Item item = player.getItems().get(itemName);
        if (item != null) {
            print += item.getItemDescription();
            if (item instanceof RoomObject) {
                RoomObject roomObject = (RoomObject) item;
                print += "\n" + roomObject.getInfo();
            }
            System.out.println(print);
            return true;
        } else {
            System.out.println("I'm sorry, i don't know what you mean.");
            return false;
        }

    }


}
