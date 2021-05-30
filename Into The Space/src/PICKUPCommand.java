public class PICKUPCommand extends Command {
    public PICKUPCommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    public boolean execute(Player player) {
        if (!hasSecondWord()) {
            System.out.println("Pick up what?");
        } else {
            Room currentRoom = player.getCurrentRoom();
            if (currentRoom.getItems().containsKey(getSecondWord())) {
                Item item = currentRoom.getItems().get(getSecondWord());
                if (item instanceof Container) {
                    Container container = (Container) item;
                    if (container.getItem() instanceof RoomObject) {
                        RoomObject roomObject = (RoomObject) container.getItem();
                        System.out.println("Inside was a " + container.getItem().getItemName());
                        System.out.println(roomObject.getInfo());
                        System.out.println(container.getItem().getItemName() + " has been added to your backpack.");
                        player.addItem(container.getItem());
                        player.getCurrentRoom().getItems().remove(secondWord);
                        return false;
                        }

                }else if(item instanceof RoomObject){
                    if(item instanceof StaticObject){
                        StaticObject staticObject = (StaticObject) item;
                        if(!staticObject.removeable()){
                            System.out.println("I can't pick that up.");
                        }
                    }
                    else {
                        RoomObject roomObject = (RoomObject) item;
                        System.out.println(roomObject.getInfo());
                        System.out.println((item.getItemName() + " has been added to your backpack."));
                        player.addItem(roomObject);
                        player.getCurrentRoom().removeItem(roomObject.getItemName());
                    }
                }

                else {
                    System.out.println((item.getItemName() + " has been added to your backpack."));
                    player.addItem(item);
                    currentRoom.removeItem(item.getItemName());
                }
            }

        }
        return false;
    }
}



