import java.util.EnumSet;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two strings: a command word and a second
 * word (for example, if the command was "take map", then the two strings
 * obviously are "take" and "map").
 * 
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the command word is <null>.
 *
 * If the command had only one word, then the second word is <null>.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public abstract class Command {
    protected CommandWord firstWord;
    protected String secondWord;
    public Command(CommandWord firstWord, String secondWord){
        this.firstWord = firstWord;
        this.secondWord = secondWord;
    }
    /**
     * @return The second word of this command. Returns null if there was no
     * second word.
     */
    public String getSecondWord()
    {
        return secondWord;
    }

    public CommandWord getFirstWord() {
        return firstWord;
    }

    public void setSecondWord(String word){
        this.secondWord = word;
    }

    /**
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
    public abstract boolean execute(Player player);
}









