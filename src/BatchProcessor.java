import java.io.File;
import java.util.Map;

/**
 * Created by assar on 9/15/2015.
 */
public class BatchProcessor {
    public static void main(String[] args)
    {
        try {
            String filename = null;
            if (args.length < 1) {
                //throw new ProcessException("Batch file not found.");
                System.out.println("No batch file entered");//mdw
                System.exit(0);
            }

            filename = args[0];

            System.out.println("Opening " + filename);
            File f = new File(filename);

            Batch aBatch = BatchParser.buildBatch(f);


            executeBatch(aBatch);
            System.out.println("done");

        } catch (BatchSyntaxException e)
        {System.exit(1);}//mdw
    }

    public static void executeBatch(Batch aBatch) throws BatchSyntaxException{

        for(Map.Entry<Integer,Command> command : aBatch.getCommands().entrySet()){
            System.out.println(command.getValue().describe());

            try {//mdw
                command.getValue().execute(aBatch.getWorkingDir());
            } catch (BatchSyntaxException e) {//mdw
                e.printStackTrace();
                throw new BatchSyntaxException(e.getError());
            }


        }
    }
}
