
import java.util.*;


/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Room {

    public String description;
    private final HashMap<String, Room> exits;
    private final HashMap<String, Item> items;
    private String special;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param description The room's description.
     */
    public Room(String description) {
        this.description = description;
        exits = new HashMap<>();
        items = new HashMap<>();
        special = null;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     */
    public void setExits(String direction, Room neighbor) {
        exits.put(direction, neighbor);

    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public HashMap<String, Room> getAllExits() {
        return this.exits;
    }

    public String getExitString() {
        String returnString = "";
        if (exits.size() == 1) {
            returnString += "There is 1 exit:";
            for (String direction : exits.keySet()) {
                returnString += " " + direction;
            }
        } else {
            returnString = "There are " + exits.size() + " exits:";
            for (String direction : exits.keySet()) {
                returnString += " " + direction;
            }
        }
        return returnString;
    }

    /**
     * @return The description of the room.
     */
    public String getLongDescription() {
        return "You are in " + description + getAllItems() + "\n" + getExitString();
    }

    public void addItem(Item item) {
        items.put(item.getItemName(), item);
    }

    public void removeItem(String itemName) {
        Item item = items.get(itemName);
        items.remove(itemName, item);
    }

    public HashMap<String, Item> getItems() {
        return items;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getSpecial() {
        return this.special;
    }

    public String getAllItems() {
        String itemList = "";
        if (items.isEmpty()) {
            itemList = "\nYou look around, but there's nothing in the room.";
            return itemList;
        } else {
            String returnItem = null;
            if (items.size() > 1) {
                itemList = "\nYou look around and see some items: \n\t";
                for (Item item : items.values()) {
                    if (item != null) {
                        if(item instanceof StaticObject){
                            returnItem = item.getItemName() + " " + item.getItemDescription();
                        }
                        else {
                            returnItem = item.getItemName() + " " + item.getItemDescription() + " with weight of " + item.getItemWeight() + "kg.\n";
                        }
                    }
                    itemList += returnItem + "\t";
                }
            }
                if (items.size() == 1) {
                    for (Item item : items.values()) {
                        itemList = "\nYou look around and see an item: \n\t";
                        if (item != null) {
                            if(item instanceof StaticObject){
                                returnItem = item.getItemName() + " " + item.getItemDescription();
                            }
                            else {
                                returnItem = item.getItemName() + " " + item.getItemDescription() + " with weight of " + item.getItemWeight() + "kg.\n";
                            }
                        }
                        itemList += returnItem + "\t";
                    }
                }
            }
            return itemList;
        }


    }


    class LockedRoom extends Room {
        private boolean access;

        public LockedRoom(String description) {
            super(description);
            this.access = false;
        }

        public boolean checkAccess() {
            return access;
        }

        public void setAccess(boolean access) {
            this.access = access;
        }


    }

    class FireRoom extends Room {
        private boolean access;

        public FireRoom(String description) {
            super(description);
            this.access = false;
        }

        public boolean checkAccess() {
            return access;
        }

        public void setAccess(boolean access) {
            this.access = access;
        }


    }



