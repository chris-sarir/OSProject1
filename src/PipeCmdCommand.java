import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by assar on 9/15/2015.
 */
public class PipeCmdCommand
{

    private String aId;
    private String aArg;
    private String aPath;
    private String ioFile;
    private InputStream is;
    private List<String> processCommands = new ArrayList<>();
    
    public String describe()
    {
        return new String(""); //Todo: description implementation goes here
    }

    public OutputStream execute(String workingDir) throws InterruptedException, IOException
    {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command(this.processCommands);
        builder.directory(new File(workingDir));
        
        File wd = builder.directory();

        final Process process = builder.start();

        OutputStream os = process.getOutputStream();

        /*todo: execution implementation goes here*/
        System.out.println("Executing PipeCmdCommand");//TODO:Remove before delivery

        is = process.getInputStream();
        
        return os;
    }

    public void parse(Element element, boolean isInput)
    {
        //Todo: All parsing is done here somehow

        aId = element.getAttribute("id");
        aPath = element.getAttribute("path");

        //get the 'args' from the XML line
        aArg = element.getAttribute("args");

        this.processCommands.add(aPath);
        
        if (!aArg.isEmpty()){
            StringTokenizer st = new StringTokenizer(aArg);
            while (st.hasMoreTokens()){
                String tok = st.nextToken();
                this.processCommands.add(tok);
            }
        }
        
        if(isInput) {
            ioFile = element.getAttribute("in");
        }else {
            ioFile = element.getAttribute("out");
        }
        
    }

    public String getIOFileArg (){
        return ioFile;
    }

    public InputStream getInputStream(){
        return is;
    }
}
