import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class RoomRandomizer {
    private ArrayList<Room> rooms;
    private HashSet<Item> items;

    public RoomRandomizer() {
        rooms = new ArrayList<>();
        items = new HashSet<>();

    }


    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public HashSet<Item> getItems() {
        return items;
    }

    public void setItems(HashSet<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {

    }

    public void randomize() {
        //select random room
        //get an item
        //put item in room
        //select other room
        //repeat until all items are spent
        //give some rooms specials (gas, no air, etc)
        Random random = new Random();
        for(Item item: items){
            HashSet<Item> usedItems = new HashSet<>();
            int r = random.nextInt(rooms.size());
            Room room = rooms.get(r);
            if(!usedItems.contains(item)) {
                room.addItem(item);
                usedItems.add(item);
            }
            }

        }

    }


