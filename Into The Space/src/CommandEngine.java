public class CommandEngine {


    public CommandEngine(){

    }

    public Command getCommand(CommandWord command, String secondWord){
        switch(command){
            case DROP:
                return new DROPCommand(command, secondWord);
            case PICKUP:
                return new PICKUPCommand(command, secondWord);
            case EAT:
                return new EATCommand(command, secondWord);
            case GO:
                return new GOCommand(command, secondWord);
            case QUIT:
                return new QUITCommand(command, secondWord);
            case LOOK:
                return new LOOKCommand(command, secondWord);
            case BACK:
                return new BACKCommand(command, secondWord);
            case HELP:
                return new HELPCommand(command, secondWord);
            case LOAD:
                return new LOADCommand(command, secondWord);
            case FIRE:
                return new FIRECommand(command, secondWord);
            case USE:
                return new USECommand(command, secondWord);
        }
        return null;
    }
}
