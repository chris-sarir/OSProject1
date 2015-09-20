import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by assar on 9/15/2015.
 */
public class PipeCommand  extends Command
{
    private String inId;
    private String outId;
    private List<String> inArgs = new ArrayList<String>();
    private List<String> outArgs = new ArrayList<String>();
    private String inArg;
    private String outArg;
    private String inPath;
    private String outPath;
    private String inFile;
    private String outFile;

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
        //Todo: Catch some exceptions before delivery
        for (int i = 0; i<2; i++) {
            if (element.getElementsByTagName("cmd").item(i).getAttributes().getNamedItem("in") != null) {
                System.out.println("Parsing the in command"); // Todo: remove before delivery
                inId = element.getElementsByTagName("cmd").item(i).getAttributes().getNamedItem("id").getNodeValue();
                inPath = element.getElementsByTagName("cmd").item(i).getAttributes().getNamedItem("path").getNodeValue();

                //get the 'args' from the XML line
                inArg = element.getElementsByTagName("cmd").item(i).getAttributes().getNamedItem("args").getNodeValue();
                //Todo: work on posibility of having more than one Arg
//             inArg = element.getAttribute("args");
//           if (!(inArg == null || inArg.isEmpty())){
//                StringTokenizer st = new StringTokenizer(inArg);
//                while (st.hasMoreTokens()){
//                    String tok = st.nextToken();
//                    inArgs.add(tok);
//                }
//            }

                inFile = element.getElementsByTagName("cmd").item(i).getAttributes().getNamedItem("id").getNodeValue();

            }
            if (element.getElementsByTagName("cmd").item(i).getAttributes().getNamedItem("out") != null) {
                System.out.println("Parsing the out command"); // Todo: remove before delivery
                outId = element.getElementsByTagName("cmd").item(i).getAttributes().getNamedItem("id").getNodeValue();
                outPath = element.getElementsByTagName("cmd").item(i).getAttributes().getNamedItem("path").getNodeValue();

                //get the 'args' from the XML line
                outArg = element.getElementsByTagName("cmd").item(i).getAttributes().getNamedItem("args").getNodeValue();
                //Todo: work on posibility of having more than one Arg

                outFile = element.getElementsByTagName("cmd").item(i).getAttributes().getNamedItem("id").getNodeValue();
            }
        }
    }
}
