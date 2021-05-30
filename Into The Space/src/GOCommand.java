import java.util.Locale;
import java.util.Scanner;

public class GOCommand extends Command {
    public GOCommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        boolean endReached = false;
        if (!hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return endReached;
        }
        String direction = secondWord;
        Room currentRoom = player.getCurrentRoom();

        Room nextRoom;
        nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("Hmmm.. I don't think that's right. Try again.");
            return false;
        }
        else if (nextRoom.getSpecial() != null) { //there is a nextRoom, does the next room have a special setting?
            for(Item item : player.getItems().values()){
                if (item instanceof RoomObject) { //is there a roomobject?
                    RoomObject roomObject = (RoomObject) item;
                    String special = roomObject.getSpecial();
                    if (special != null) {
                        if (special.equals(nextRoom.getSpecial())) {
                            if (special.equals("fire suit")) {
                                System.out.println("You're protected from the fire and can enter the room safely.");
                                goTrough(player);
                                return false;
                            }

                            if (special.equals("gas")) {
                                System.out.println("You're protected from the gas and can enter the room safely.");
                                goTrough(player);
                                return false;
                            }
                            if (special.equals("elevator low card")) {
                                System.out.println("Access granted to basement. Proceed.");
                                goTrough(player);
                                return false;
                            }
                            if (special.equals("engine room card")) {
                                System.out.println("Access granted to engine room. Proceed.");
                                System.out.println("The ship's engines start up and you will survive!!");
                                System.out.println("Congratulations, you've made it until the end...");
                                return true;
                            }
                        }
                    }
                }
            }
            if(nextRoom instanceof FireRoom) {
                System.out.println("\n\nBe warned, the next room is on fire." +
                        "\nYou can enter, but you will take damage." +
                        "\nTo prevent this, try looking for protection from the fire." +
                        "\nDo you wish to proceed? Type y or n ");
                System.out.print(">");
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                input = input.toLowerCase(Locale.ROOT);
                if (input.equals("y")) {
                    goTrough(player);
                    int time = 20;
                    while(time > 0 && player.getHealth() > 0) {
                        try {
                            Thread.sleep(1000);
                            time -= 5;
                            player.increaseHealth(-30);
                            System.out.println("~^~^~~^~^~~^~^~~^~^~~^~^~~^~^~~^~^~\nYou are taking burn damage." +
                                    "\nYour health is now: "+player.getHealth());

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Parser parser = new Parser();
                        Command command = parser.getCommand();
                        if((command.getFirstWord().toString().equals("go")) && currentRoom.getAllExits().containsKey(command.getSecondWord()) ) {
                            command.execute(player);
                            time -= 20;
                            return false;
                        }else{
                            command.execute(player);
                        }
                        if(player.getHealth() <= 0){
                            return true;
                        }
                    }
                    if(player.getHealth()<=0){
                        return true;
                    }
                }else{
                    return false;
                }
            }




            if(player.getItems().isEmpty()){ //player has no items, advice on specific item
                System.out.println("You need the correct key or tool to go through here: " + nextRoom.getSpecial());
                return false;
            }
        }






            if(nextRoom instanceof LockedRoom){ //nextRoom is a lockedRoom, requires access code
                LockedRoom lockedRoom = (LockedRoom) nextRoom;
                if(lockedRoom.checkAccess()){ // checks room if access is true enabled
                    goTrough(player); // player goes trough
                }else // player needs to enter keycode in keypad in currentroom
                System.out.println("You need to enter the keycode into \nthe keypad in the room to proceed.(Try: 'use keypad').");
                return false;
            }

            else { // if none of the above apply, player has no restrictions and can enter
                goTrough(player);
            }
            return false;
        }




    //method for continuing through
    private void goTrough(Player player){
        player.getRoute().push(player.getCurrentRoom());
        player.setCurrentRoom(player.getCurrentRoom().getExit(secondWord));
        System.out.println(player.getCurrentRoom().getLongDescription());

    }


}
