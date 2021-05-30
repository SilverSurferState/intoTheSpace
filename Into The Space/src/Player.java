import java.util.*;

public class Player {
    private int health;
    private double maxWeight = 10;
    private HashMap<String,Item> items;
    private String name;
    private Room currentRoom;
    private Stack<Room> route;
    private Room savedRoom;

    /**
     * creates new player object with a name and starting location
     *
     * @param name
     * @param startingRoom
     */
    public Player(String name, Room startingRoom) {
        this.health = 100;
        this.name = name;
        items = new HashMap<>();
        currentRoom = startingRoom;
        route = new Stack<>();

    }


    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public int getHealth(){
        return this.health;
    }

    public void increaseHealth(int amount){
        this.health += amount;
    }

    public Stack<Room> getRoute(){
        return route;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room){
        this.currentRoom = room;
    }

    public void setSavedRoom(Room room) {
        this.savedRoom = room;
    }

    public Room getSavedRoom(){
        return this.savedRoom;
    }

    public HashMap<String, Item> getItems(){
        return this.items;
    }

    public void addItem(Item item){
        items.put(item.getItemName(), item);
    }


    public void increaseWeightCap(double increase) {
        maxWeight += increase;
    }


    public void printBackPackContents() {
        int currentWeight = 0;
        if (items.isEmpty()) {
            System.out.println(getName() + " has no items in his backpack.");
        } else {
            System.out.println(getName() + " has the following items in his backpack:");
            for (Item item : items.values()) {
                currentWeight += item.getItemWeight();
                item.itemLongDescription();
            }
            System.out.println("The total weight of your backpack is: " + currentWeight + "kg out of max " + maxWeight + "kg.");


        }
    }

    public Item eat(String itemName) {
        Item item = null;
            if(items.containsKey(itemName)) {
                item = items.get(itemName);
            }
            return item;
    }

    public Item drop(String itemName) {
        Item item = null;
        String print = "";
       if(items.containsKey(itemName)){
           item = items.get(itemName);
           print += itemName + " has been removed from your backpack.";
       }
       else{
           print += "You don't have that item.";
       }
        System.out.println(print);
        return item;
    }


}
