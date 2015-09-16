import java.util.Map;

/**
 * Created by assar on 9/15/2015.
 */
public class Batch {
    /*Batch implementation goes here*/

    private String workingDir;
    private Map<String, Command> commands;

    public void addCommand(Command aCommand){
        /*Put command stuff here*/
        commands.put(aCommand.describe(), aCommand);//todo: not sure if description is what is used for the String here
    }

    public String getWorkingDir(){
        return workingDir;
    }

    public Map<String, Command> getCommands(){
        return commands;
    }
}
