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

    	System.out.println("Executing CmdCommand");
    	
    	//Create the Process builder
        ProcessBuilder builder = new ProcessBuilder();
        
        
        //Sets the builders working directory
        builder.directory(new File(workingDir));
        
        //Creates a file of the builders directory
        File wd = builder.directory();
        
        //If the command has a file that is directed into the stdIn 
        //redirect it to the builders input
        if (in != null) {
        	System.out.println("Redirecting input to " + commandInfo.get(in));
            File inFile = new File(wd, commandInfo.get(in));
            builder.redirectInput(inFile);
        }
        
        //If the command has a file that is directed out of the stdOut 
        //redirect it to the builders output
        if (out != null) {
        	System.out.println("Redirecting output to " + commandInfo.get(out));
            File outFile = new File(wd, commandInfo.get(out));
            builder.redirectOutput(outFile);
        }
        
        builder.command(cmdArgs);
        
        
        Process process = null;
        
        try {
        	
            process = builder.start();
            
        } catch (IOException e) {
        	System.out.println("** The process is failing to start **");
        	
            e.printStackTrace();
            
        }
        
        try {
        	
            if (process != null) {
                process.waitFor();
                process.exitValue();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        
        cmdArgs.add(path);
        //get the 'args' from the XML line
        //and places each token in a ArrayList
        String arg = element.getAttribute("args");
        if (!arg.isEmpty()){
            StringTokenizer st = new StringTokenizer(arg);
            while (st.hasMoreTokens()){
                String tok = st.nextToken();
                cmdArgs.add(tok);
            }
        }
        
        //get the "in" from the XML line
        if (!element.getAttribute("in").isEmpty()){
            //print 'in' or process exception
        	in = element.getAttribute("in");
        }
        //get the "out" from the XML line
        
        if (!element.getAttribute("out").isEmpty()){
            //print 'out' or process exception
        	out = element.getAttribute("out");
        }
        
        commandInfo.put(id, path);
        
        System.out.println("********************Cmd Command Parsed INFO******************");
        System.out.println("id: " + this.id);
        System.out.println("path: " + this.path);
        System.out.println("in: " + this.in);
        System.out.println("out: " + this.out);
        

        
    }
}
