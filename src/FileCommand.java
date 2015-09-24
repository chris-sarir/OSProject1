import org.w3c.dom.Element;

/**
 * Created by assar on 9/15/2015.
 */
public class FileCommand extends Command
{
    private String id;
    private String path;

	public String describe()
    {
        return new String("File Command on file: " + this.path); //Todo: description implementation goes here
    }

    public void execute(String workingDir)
    {
        /*todo: execution implementation goes here*/
        System.out.println("Executing FileCommand");//TODO:Remove before delivery

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
        
        System.out.println("******************** File Command parsed INFO******************");
        System.out.println("id: " + id);
        System.out.println("path: " + path);
        System.out.println("*************************************************************");
        System.out.println("\n\nTesting Contents in commandInfo\n" + commandInfo + "\n\n");
        System.out.println("*************************************************************");
    }
}
