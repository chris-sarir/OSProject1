import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by assar on 9/15/2015.
 */
public class CmdCommand extends Command
{
    private String id;
    private String path;
    private List<String> cmdArgs = new ArrayList<String>();
    private String in;
    private String out;

	public String describe()
    {
        return "Commmand: " + id; //Todo: description implementation goes here
    }

    public void execute(String workingDir)
    {
        /*todo: execution implementation goes here*/

        //put the 'id' and 'path' into the abstract Command class shared Map
        commandInfo.put(id, path);

        ProcessBuilder builder = new ProcessBuilder();
        for (int i = 0; i < cmdArgs.size(); i++)
        {
            builder.command(cmdArgs.get(i));
        }
       // builder.command(arg);
        builder.directory(new File(workingDir));
        File wd = builder.directory();

        if (in != null) {
            File inFile = new File(wd, commandInfo.get(in));
            builder.redirectInput(inFile);
        }
        if (out != null) {
            File outFile = new File(wd, commandInfo.get(out));
            builder.redirectOutput(outFile);
        }
        Process process = null;
        try {
            process = builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (process != null) {
                process.waitFor();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Executing CmdCommand");//TODO:Remove before delivery
    }

    public void parse(Element element)
    {
        //get the 'id' from the XML line
        id = element.getAttribute("id");
        /*if (id == null || id.isEmpty()){
            //Uncomment this line once we include catching exceptions
            throw new ProcessException("Missing ID in CMD Command");
        }*/
        //get the 'path' from the XML line
        path = element.getAttribute("path");
       /* if (path == null || path.isEmpty()){
            //Uncomment this line once we include catching exceptions
            //throw new Process Exception("Missing PATH in CMD Command");
        }*/
        //put the 'id' and 'path' into the abstract Command class shared Map
      //  commandInfo.put(id, path);

        //get the 'args' from the XML line
        String arg = element.getAttribute("args");
        if (!(arg == null || arg.isEmpty())){
            StringTokenizer st = new StringTokenizer(arg);
            while (st.hasMoreTokens()){
                String tok = st.nextToken();
                cmdArgs.add(tok);
            }
        }
        //get the "in" from the XML line
        in = element.getAttribute("in");
        /*if (!(in == null || in.isEmpty())){
            //print 'in' or process exception
        }*/
        //get the "out" from the XML line
        out = element.getAttribute("out");
       /* if (!(out == null || out.isEmpty())){
            //print 'out' or process exception
        }*/
    }
}
