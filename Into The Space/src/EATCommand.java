class EATCommand extends Command {


    public EATCommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        String print = "";
        Item item = player.eat(getSecondWord());
        if (item == null) {
            print += "You can't eat what you don't have...";
        }
        else if (item instanceof Edible) {
            Edible food = (Edible) item;
            if (player.getHealth() == 100) {
                print += "You're already at full health.";
            }
            if (player.getHealth() < 100) {
                player.increaseHealth(food.getValue());
                print += "Your health is now: " + player.getHealth();
                player.getItems().remove(secondWord);
            }
            if (food.checkSpecial() != null) {
                if (food.checkSpecial().equals("mw+5")) {
                    player.increaseWeightCap(5);
                    print += "\nYour carrying capacity has increased!";
                }
                else if(food.checkSpecial().equals("mw-5")){
                    player.increaseWeightCap(-5);
                    print += "\nOh my.. your carrying capacity seems to have decreased!";
                }
            }

        } else {
            print += "That is not edible!";
        }
        System.out.println(print);
        return false;
    }
}
