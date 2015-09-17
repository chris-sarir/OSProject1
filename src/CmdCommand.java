import org.w3c.dom.Element;

/**
 * Created by assar on 9/15/2015.
 */
public class CmdCommand extends Command {
	@Override
	public String describe(){
        return new String(); //Todo: description implementation goes here
    }

	@Override
    public void execute(String workingDir){
        /*todo: execution implementation goes here*/
    }

	@Override
    public static Command parse(Element aElement){
        return new Command(); //Todo: All parsing is done here somehow
    }
}
