public class LOADCommand extends Command{
    public LOADCommand(CommandWord firstWord, String secondWord){
        super(firstWord, secondWord);
    }

    public boolean execute(Player player) {
        if (!player.getItems().containsKey(secondWord)) {
            System.out.println("You dont have this item.");
        } else {
            for (Item item : player.getItems().values()) {
                if (item instanceof RoomObject) {
                    RoomObject roomObject = (RoomObject) item;
                    if (roomObject.getSpecial() != null) {
                        if (roomObject.getSpecial().equals("teleporter")) {
                            player.setSavedRoom(player.getCurrentRoom());
                            System.out.println("This location is now saved!");
                        } else {
                            System.out.println("Can't use load with this item.");
                        }
                    } else {
                        System.out.println("Can't use load with this item.");
                    }
                }

            }

        }
        return false;
    }
}

