import org.w3c.dom.Element;

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
    private String arg;
    private String in;
    private String out;

	public String describe()
    {
        return new String("Commmand: " + id); //Todo: description implementation goes here
    }

    public void execute(String workingDir)
    {
        /*todo: execution implementation goes here*/
        System.out.println("Executing CmdCommand");//TODO:Remove before delivery
    }

    public void parse(Element element)
    {
        //get the 'id' from the XML line
        id = element.getAttribute("id");
        if (id == null || id.isEmpty()){
            //Uncomment this line once we include catching exceptions
            //throw new ProcessException("Missing ID in CMD Command");
        }
        //get the 'path' from the XML line
        path = element.getAttribute("path");
        if (path == null || path.isEmpty()){
            //Uncomment this line once we include catching exceptions
            //throw new Process Exception("Missing PATH in CMD Command");
        }
        //put the 'id' and 'path' into the abstract Command class shared Map
        commandInfo.put(id, path);

        //get the 'args' from the XML line
        arg = element.getAttribute("args");
        if (!(arg == null || arg.isEmpty())){
            StringTokenizer st = new StringTokenizer(arg);
            while (st.hasMoreTokens()){
                String tok = st.nextToken();
                cmdArgs.add(tok);
            }
        }
        //get the "in" from the XML line
        in = element.getAttribute("in");
        if (!(in == null || in.isEmpty())){
            //print 'in' or process exception
        }
        //get the "out" from the XML line
        out = element.getAttribute("out");
        if (!(out == null || out.isEmpty())){
            //print 'out' or process exception
        }
    }
}
