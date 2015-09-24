import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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

    public String describe()
    {
        return new String(); //Todo: description implementation goes here
    }

    public OutputStream execute(String workingDir) throws InterruptedException, IOException
    {
        List<String> command = new ArrayList<String>();
        command.add(aPath);
        command.add(aArg);

        ProcessBuilder builder = new ProcessBuilder(command);
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

        aId = element.getAttributes().getNamedItem("id").getNodeValue();
        aPath = element.getAttributes().getNamedItem("path").getNodeValue();

        //get the 'args' from the XML line
        aArg = element.getAttributes().getNamedItem("args").getNodeValue();
        //Todo: work on posibility of having more than one Arg


        if(isInput) {
            ioFile = element.getAttributes().getNamedItem("in").getNodeValue();
        }else {
            ioFile = element.getAttributes().getNamedItem("out").getNodeValue();
        }
    }

    public String getIOFileArg (){
        return ioFile;
    }

    public InputStream getInputStream(){
        return is;
    }



}
