import java.util.*;

/**
 *  This class is the main class of the "World of Zuul" application.
 *  "World of Zuul" is a very simple, text based adventure game.  Users
 *  can walk around some scenery. That's all. It should really be extended
 *  to make it more interesting!
 *
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 *
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *
 * @author  Umberto Righetto
 * @version 2021.05.24
 */

public class Game {
    private final Parser parser;
    private Player player;
    private int timeCounter = 0;
    private int currentCounter = 0;


    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createRooms();
        parser = new Parser();
    }

    public enum difficulty{
        EASY,
        MEDIUM,
        HARD
    }

    /**
     * sets the game difficulty with amount of steps to take
     * @param difficulty
     */
    public void setDifficulty(difficulty difficulty){
        difficulty myDif = difficulty;
        switch(myDif){
            case EASY:
                timeCounter = 50;
                break;
            case HARD:
                timeCounter = 30;
                break;
            case MEDIUM:
                timeCounter = 40;
                break;
        }
    }

    /**
     * Create all the rooms and link their exits together.
     * also initiates all items and distributes them if needed
     */
    private void createRooms() {
        Room mainCommandModule, elevatorTop, messHall, kitchen, sleepingQuarters, elevatorMid, library,
                bar, entertainmentRoom, freezerRoom, supplyRoom, elevatorLow, engineRoom, officersLounge;

        // create the rooms
        mainCommandModule = new Room("the main command center of the ship.(There is a staircase leading down.)");
        elevatorTop = new Room("an elevator room that can go down. (You're on the top floor.)");
        elevatorMid = new Room("an elevator room that can go up or down. (You're on the first floor.)");
        elevatorLow = new Room("an elevator room that can go up. (You're in the basement.)");
        messHall = new Room("the mess hall. This is where everyone eats, except the officers.");
        kitchen = new Room("the kitchen.");
        sleepingQuarters = new Room("in the sleeping quarters.");
        library = new FireRoom("the ship's library.");
        bar = new Room("the bar.");
        entertainmentRoom = new Room("the entertainment room.");
        freezerRoom = new LockedRoom("the freezer room.");
        supplyRoom = new Room("the supply room.");
        engineRoom = new Room("the engine room.");
        officersLounge = new LockedRoom("the officer's lounge.");
        Room[] allRooms = new Room[]{messHall, kitchen, sleepingQuarters, library,
                bar, entertainmentRoom, freezerRoom, supplyRoom, officersLounge};
        ArrayList<Room> allRoomsArray = new ArrayList<>();
        allRoomsArray.addAll(Arrays.asList(allRooms));

        //group 4 rooms for initial note
        Room[] noteRoomsArray = new Room[]{messHall, kitchen, elevatorMid, sleepingQuarters,bar,entertainmentRoom};
        ArrayList<Room> noteRooms = new ArrayList<>();
        noteRooms.addAll(Arrays.asList(noteRoomsArray));


        // initialise room exits
        mainCommandModule.setExits("stairs", elevatorTop);
        elevatorTop.setExits("down", elevatorMid);
        elevatorTop.setExits("stairs", mainCommandModule);
        elevatorMid.setExits("up", elevatorTop);
        elevatorTop.setExits("left", officersLounge);
        officersLounge.setExits("right", elevatorTop);
        kitchen.setExits("left", messHall);
        elevatorTop.setExits("right", messHall);
        messHall.setExits("left", elevatorTop);
        messHall.setExits("right", kitchen);
        elevatorMid.setExits("left", sleepingQuarters);
        elevatorMid.setExits("right", library);
        elevatorMid.setExits("down", elevatorLow);
        sleepingQuarters.setExits("stairs", bar);
        sleepingQuarters.setExits("right", elevatorMid);
        bar.setExits("stairs", sleepingQuarters);
        bar.setExits("right", entertainmentRoom);
        entertainmentRoom.setExits("left", bar);
        entertainmentRoom.setExits("stairs", library);
        library.setExits("stairs", entertainmentRoom);
        library.setExits("left", elevatorMid);
        elevatorLow.setExits("left", supplyRoom);
        elevatorLow.setExits("right", freezerRoom);
        elevatorLow.setExits("up",elevatorMid);
        elevatorLow.setExits("vault", engineRoom);
        freezerRoom.setExits("left", elevatorLow);
        supplyRoom.setExits("right", elevatorLow);

        //set certain room specials, access only if item present in player bag
        engineRoom.setSpecial("engine room card");
        elevatorLow.setSpecial("elevator low card");
        library.setSpecial("fire suit");


        // initialise items
        //init Edibles
        Edible powerbar = new Edible("power bar", "(An edible protein bar.)", 5, null);
        Edible powerbar1 = new Edible("super bar", "(An edible protein bar.)", 4, null);
        Edible juice = new Edible("berry juice", "(A juice with lots of vitamins.)", 6, "mw+5");
        Edible spacebeer = new Edible("stellar", "(An alcoholic beverage...use at your own risk.)", 5, "mw-5");
        Edible spacepie = new Edible("space cake", "(It's edible, but smell funky.)", 10, "mw+5");
        Edible powerbar2 = new Edible("strawberry bar", "(An edible protein bar.)", 7, null);
        Edible juice2 = new Edible("banana juice", "(A juice with lots of vitamins.)", 12, null);

        //init other items
        KeyPad keyPad1 = new KeyPad();
        KeyPad keyPad = new KeyPad();
        RoomObject elevatorLowCard = new RoomObject("card201", "A thin yellow key card", 0.5, "It says: access to basement elevator.", "elevator low card");
        RoomObject engineRoomCard = new RoomObject("card001", "A thin red key card", 0.5, "It says: access to engine room.", "engine room card");
        RoomObject fireSuit = new RoomObject("fire suit", "(A fire protective suit)", 2, "The label says: 'Cannot be burned'. Made in China.", "fire suit");
        RoomObject note1 = new RoomObject("note", "(A small note)", 0, "It says: 'Officer's lounge access code: " + keyPad1.getCombination() + "'.", null);
        RoomObject book = new RoomObject("book", "(A little black book)", 2, "On page 8 there is some writing: " + keyPad.getCombination(), null);
        RoomObject teleporter = new RoomObject("teleporter", "(A teleportation device)", 2,
                "Can be used to teleport to other rooms you've been in.\nUse 'load teleporter' to save a location" +
                        "\nor 'fire teleporter' to teleport back to the room you saved.", "teleporter");


        //init containers
        Container box1 = new Container("toolbox", "(A metal toolbox)", 6, note1);
        Container box2 = new Container("pouch", "(A brown pouch with a logo on it)", 2.5, teleporter);
        Container box3 = new Container("wallet", "(A leather wallet)", 2, elevatorLowCard);

        //add critical items to locations for gameplay progress
        elevatorTop.addItem(keyPad1);
        elevatorLow.addItem(keyPad);
        officersLounge.addItem(box3);
        supplyRoom.addItem(fireSuit);
        library.addItem(book);
        freezerRoom.addItem(engineRoomCard);

        //randomize items across rooms
        RoomRandomizer randomizer = new RoomRandomizer();

        //distribute Edibles across all floors
        HashSet<Item> edibles = new HashSet<>();
        Edible[] itemsArray = new Edible[]{powerbar, powerbar1, powerbar2, juice, juice2, spacebeer, spacepie};
        edibles.addAll(Arrays.asList(itemsArray));
        randomizer.setRooms(allRoomsArray);
        randomizer.setItems(edibles);
        randomizer.randomize();

        //distribute note(roomobjects) across some floor
        HashSet<Item> roomObjects = new HashSet<>();
        Item[] objectsArray = new Item[]{box1, box2};
        roomObjects.addAll(Arrays.asList(objectsArray));
        randomizer.setRooms(noteRooms);
        randomizer.setItems(roomObjects);
        randomizer.randomize();


        player = new Player("player1", mainCommandModule);
    }
    /**
     * Main play routine.  Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        Command command;
        while (!finished) {
            command = parser.getCommand();
            currentCounter++;
            finished = (command.execute(player) || currentCounter == timeCounter);
        }if(currentCounter == timeCounter){
            System.out.println("You're time is up! The air has left the ship and you died.");
        }

        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to 'Into The Space'!");
        System.out.println("Into the Space is a text-based adventure game.");
        System.out.println("Your spaceship has just crash-landed on a deserted planet!");
        System.out.println("Your entire crew is dead!");
        System.out.println("Air is running out and you need to restart to engines in the engine room to survive!");
        System.out.println("Beware, time is not your only enemy. The crash has damaged a lot of security systems on the ship.");
        System.out.println("So proceed with caution...");
        System.out.println("\nType " + CommandWord.HELP +" if you need help.");
        System.out.println("To get started, please type your character name:");
        System.out.print(">  ");
        Scanner scanner = new Scanner(System.in);
        String playerName = scanner.nextLine();
        player.setName(playerName);
        System.out.println();
        System.out.println("Please select a difficulty: easy, medium or hard. This will give you more or less time to complete the game.");
        System.out.print("> ");
        boolean answer = false;
        while (!answer){
            String diff = scanner.nextLine();
            switch(diff){
            case "easy":
                setDifficulty(difficulty.EASY);
                answer = true;
                break;
            case "medium":
                setDifficulty(difficulty.MEDIUM);
                answer = true;
                break;
            case "hard":
                setDifficulty(difficulty.HARD);
                answer = true;
                break;
            default: answer = false;
        }
        }
        System.out.println();

        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    public static void main (String[]args){
        Game game = new Game();
        game.play();
    }
}

