public class FIRECommand extends Command {
    public FIRECommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        if (!player.getItems().containsKey(secondWord)) {
            System.out.println("You dont have this item.");
        } else {
            for (Item item : player.getItems().values()) {
                if (item instanceof RoomObject) {
                    RoomObject roomObject = (RoomObject) item;
                    if (roomObject.getSpecial() != null) {
                        if (roomObject.getSpecial().equals("teleporter")) {
                            player.setCurrentRoom(player.getSavedRoom());
                            System.out.println(player.getCurrentRoom().getLongDescription());
                        } else {
                            System.out.println("Can't use fire with this item.");
                        }
                    } else {
                        System.out.println("Can't use fire with this item.");
                    }
                }

            }

        }
        return false;
    }
}
