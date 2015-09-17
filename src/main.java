import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by assar on 9/15/2015.
 */
public class main {
    public static void main (String[] args)
    {
        try{
            String filename = null;
            if(args.length > 0){
                throw new ProcessException("Error");
            }
            filename = args[0];

            System.out.println("Opening " + filename);
            File f = new File(filename);

            Batch aBatch = BatchParser.buildBatch(f);

            executeBatch(aBatch);

        }
        catch (ProcessException e){
            /*To be sent to our own exception handler*/
        }
    }

    public static void executeBatch(Batch aBatch){

        for(Map.Entry<String,Command> command : aBatch.getCommands().entrySet()){
            command.getValue().describe();
            command.getValue().execute(aBatch.getWorkingDir());
        }

    }

}
