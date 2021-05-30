import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

abstract class  Item {
    protected String description;
    protected String name;
    protected double weight;

    public Item(String name, String description, double weight){
        this.name = name;
        this.description = description;
        this.weight = weight;
    }
    

    public String getItemName(){
        return this.name;
    }

    public String getItemDescription(){
        return this.description;
    }

    public double getItemWeight(){
        return this.weight;
    }

    public void itemLongDescription(){
        System.out.println("\t" + getItemName() + " " + getItemDescription() + " with weight of " + getItemWeight() + "kg.");
    }
}

class Edible extends Item {
    private int value;
    private String special;

    public Edible(String name, String description, int value, String special) {
        super(name, description, 0.2);
        this.value = value;
        this.special = special;

    }

    public int getValue() {
        return value;
    }

    public String checkSpecial() {
        return special;
    }
}
    class Container extends Item {
        protected ArrayList<Item> items = new ArrayList<>();

        public Container(String name, String description, double weight, Item item) {
            super(name, description, weight);
            items.add(item);
        }
        public Item getItem() {
            return items.get(0);
        }
    }

    class RoomObject extends Item {
        private String special;
        private String info;


    public RoomObject(String name,String description,double weight, String info, String special){
        super(name,description,weight);
        this.info = info;
        this.special = special;
    }
    public boolean removeable(){
        return true;
    }

    public String getInfo(){
        return info;
    }

    public String getSpecial(){
        return special;
    }
}

class StaticObject extends RoomObject{
    public StaticObject(String name,String description, String info, String special){
        super(name, description,0, info, special);
    }

    @Override
    public boolean removeable() {
        return false;
    }
}

class KeyPad extends StaticObject{
    private String combination;
    public KeyPad(){
        super("keypad", "(A 10-digit numeral keypad.)", "You can make any combination of 4 numbers.", null);
        Random r = new Random();
        int number = r.nextInt(9999-1000)+1000;
        this.combination = Integer.toString(number);
    }

    public String getCombination(){
        return this.combination;
    }

    @Override
    public void itemLongDescription() {
        System.out.println("\t" + getItemName() + " " + getItemDescription());
    }
}






