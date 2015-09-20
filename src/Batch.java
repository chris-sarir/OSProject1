import java.util.HashMap;
import java.util.Map;

/**
 * Created by assar on 9/15/2015.
 */
public class Batch {
    /*Batch implementation goes here*/

    private String workingDir;
    private HashMap<Integer, Command> commands = new HashMap<Integer, Command>();

    public void addCommand(Command aCommand){
        /*Put command stuff here*/
        commands.put(commands.size()+1, aCommand);//todo: review the use of Integer instead of String
    }

    public String getWorkingDir(){
        return workingDir;
    }

    public void setWorkingDir(String aWD){
        workingDir = aWD;
    }

    public Map<Integer, Command> getCommands(){
        return commands;
    }
}
