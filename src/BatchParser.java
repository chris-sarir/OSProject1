import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by assar on 9/15/2015.
 */
public class BatchParser {
    public static Batch buildBatch (File batchFile){
        Batch aBatch = null;
        Command aCommand = null;

        try {
            FileInputStream fis = new FileInputStream(batchFile);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fis);

            Element pnode = doc.getDocumentElement();
            NodeList nodes = pnode.getChildNodes();
            for (int idx = 0; idx < nodes.getLength(); idx++) {
                Node node = nodes.item(idx);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
//                    aCommand = buildCommand(elem);
                    aBatch.addCommand(buildCommand(elem));
                }
            }
        }
        catch (Exception e) {
            /*Send to exception Handler method*/
        }
        return aBatch;
    }
//test
    public static Command buildCommand (Element elem){
        String cmdName = elem.getNodeName();
        Command cmd = null;

        if (cmdName == null) {
            /*Throw our own exception*/
        }
        else if ("wd".equalsIgnoreCase(cmdName)) {
            System.out.println("Parsing wd");
            cmd = WDCommand.parse(elem);
        }
        else if ("file".equalsIgnoreCase(cmdName)) {
            System.out.println("Parsing file");
            cmd = FileCommand.parse(elem);
        }
        else if ("cmd".equalsIgnoreCase(cmdName)) {
            System.out.println("Parsing cmd");
            cmd = CmdCommand.parse(elem);
            //parseCmd(elem); // Example of parsing a cmd element
        }
        else if ("pipe".equalsIgnoreCase(cmdName)) {
            System.out.println("Parsing pipe");
            cmd = PipeCommand.parse(elem);
        }
        else {
            /*Throw our own exception*/
        }
        return cmd;
    }
}
