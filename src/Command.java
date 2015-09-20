/**
 * Created by assar on 9/15/2015.
 */
import org.w3c.dom.Element;

import java.util.HashMap;
import java.util.Map;

public abstract class Command {

    public static Map<String, String> commandInfo = new HashMap<String, String>();

    public abstract String describe();

    public abstract void execute(String workingDir);

    public abstract void parse(Element element);
}
