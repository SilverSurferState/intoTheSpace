import java.util.Scanner;

class USECommand extends Command {
    public USECommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        if(player.getCurrentRoom().getItems().containsKey(secondWord)){
            Item item = player.getCurrentRoom().getItems().get(secondWord);
            if(item instanceof KeyPad){
                KeyPad keypad = (KeyPad) item;
                String combo = keypad.getCombination();
                System.out.println("Please input the correct combination:");
                Scanner scanner = new Scanner(System.in);
                String combination = scanner.nextLine();
                System.out.println(">");
                if(combination.equals(combo)){
                    System.out.println("Access granted.");
                    Room room = player.getCurrentRoom();
                    for(Room nextRoom : room.getAllExits().values()){
                        if (nextRoom instanceof LockedRoom){
                            LockedRoom lockedRoom = (LockedRoom) nextRoom;
                            lockedRoom.setAccess(true);
                            player.setCurrentRoom(nextRoom);
                            System.out.println(player.getCurrentRoom().getLongDescription());
                        }

                    }
                }
                else{
                    System.out.println("Wrong combination. Try again.");
                }



            }
        }

        return false;

    }
}
