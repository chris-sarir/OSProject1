import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by assar on 9/15/2015.
 */
public class PipeCommand  extends Command
{

    public String describe()
    {
        return new String(); //Todo: description implementation goes here
    }

    public void execute(String workingDir)
    {
        /*todo: execution implementation goes here*/
        System.out.println("Executing PipeCommand");//TODO:Remove before delivery

    }

    public void parse(Element element)
    {
        //Todo: All parsing is done here somehow

    }
}
