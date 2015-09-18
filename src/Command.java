/**
 * Created by assar on 9/15/2015.
 */
import org.w3c.dom.Element;

public abstract class Command {

    public abstract String describe();

    public abstract void execute(String workingDir);

    public abstract void parse(Element element);
}
