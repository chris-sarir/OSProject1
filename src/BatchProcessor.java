import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by assar on 9/15/2015.
 */
public class BatchProcessor {
    public static void main(String[] args)
    {
        try{
            String filename = null;
            if(args.length < 1){
                throw new ProcessException("Batch file not found.");
            }
            
            filename = args[0];

            System.out.println("Opening " + filename);
            File f = new File(filename);

            Batch aBatch = BatchParser.buildBatch(f);

            executeBatch(aBatch);
            System.out.println("done");

        }
        catch (ProcessException e){
            e.printStackTrace();
        }
    }

    public static void executeBatch(Batch aBatch){

        for(Map.Entry<Integer,Command> command : aBatch.getCommands().entrySet()){
            System.out.println(command.getValue().describe());
            command.getValue().execute(aBatch.getWorkingDir());
        }

    }

}
