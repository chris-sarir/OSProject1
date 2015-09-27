import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by assar on 9/15/2015.
 */
public class BatchParser {
    public static Batch aBatch;

    public static Batch buildBatch (File batchFile) throws BatchSyntaxException {
        aBatch = new Batch();
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
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            throw new BatchSyntaxException(e.getMessage());
        } catch (SAXException e) {
            e.printStackTrace();
            throw new BatchSyntaxException(e.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new BatchSyntaxException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new BatchSyntaxException(e.getMessage());
        }
        return aBatch;
    }

    public static Command buildCommand (Element elem){
        String cmdName = elem.getNodeName();
        Command cmd = null;

        if (cmdName == null) {
            /*Throw our own exception*/
        }
        else if ("wd".equalsIgnoreCase(cmdName)) {
            System.out.println("Parsing wd");
            cmd = new WDCommand();
            cmd.parse(elem);
        }
        else if ("file".equalsIgnoreCase(cmdName)) {
            System.out.println("Parsing file");
            cmd = new FileCommand();
            cmd.parse(elem);
        }
        else if ("cmd".equalsIgnoreCase(cmdName)) {
            System.out.println("Parsing cmd");
            cmd = new CmdCommand();
            cmd.parse(elem);
            //parseCmd(elem); // Example of parsing a cmd element
        }
        else if ("pipe".equalsIgnoreCase(cmdName)) {
            System.out.println("Parsing pipe");
            cmd = new PipeCommand();
            cmd.parse(elem);
        }
        else {
            /*Throw our own exception*/
        }
        return cmd;
    }
}
