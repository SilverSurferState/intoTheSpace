import java.util.HashMap;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords {
    private HashMap<String, CommandWord> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords() {
        validCommands = new HashMap<>();
        validCommands.put(CommandWord.PICKUP.toString(), CommandWord.PICKUP);
        validCommands.put(CommandWord.DROP.toString(), CommandWord.DROP);
        validCommands.put(CommandWord.EAT.toString(), CommandWord.EAT);
        validCommands.put(CommandWord.QUIT.toString(), CommandWord.QUIT);
        validCommands.put(CommandWord.HELP.toString(), CommandWord.HELP);
        validCommands.put(CommandWord.GO.toString(), CommandWord.GO);
        validCommands.put(CommandWord.BACK.toString(), CommandWord.BACK);
        validCommands.put(CommandWord.LOOK.toString(), CommandWord.LOOK);
        validCommands.put(CommandWord.LOAD.toString(), CommandWord.LOAD);
        validCommands.put(CommandWord.FIRE.toString(), CommandWord.FIRE);
        validCommands.put(CommandWord.USE.toString(), CommandWord.USE);
    }


    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString) {
        return validCommands.containsKey(aString);
    }

    public CommandWord getCommand(String aString){
        CommandWord commandWord = validCommands.get(aString);
            return commandWord;
        }

}



