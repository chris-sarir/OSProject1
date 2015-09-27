import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.*;


/**
 * Created by assar on 9/15/2015.
 */
public class PipeCommand  extends Command
{
    private PipeCmdCommand inCmd = new PipeCmdCommand();
    private PipeCmdCommand outCmd = new PipeCmdCommand();

    public String describe()
    {
        return new String("Pipe Command"); //Todo: description implementation goes here
    }

    public void execute(String workingDir) throws BatchSyntaxException //mdw
    {
        System.out.println("Executing PipeCommand");
        
        try { //mdw
            if (commandInfo.get( inCmd.getIOFileArg() )==null)
                throw new BatchSyntaxException("Undeclared Input file: " + inCmd.getIOFileArg());
            if (commandInfo.get( outCmd.getIOFileArg() )==null)
                throw new BatchSyntaxException("Undeclared Output file: " + outCmd.getIOFileArg());

            OutputStream os1 = inCmd.execute(workingDir);
          
            FileInputStream fis = new FileInputStream(workingDir + "\\"+commandInfo.get( inCmd.getIOFileArg() ));

            System.out.println("###testing mdw: " + commandInfo.get( outCmd.getIOFileArg() )); // mdw

            //FileInputStream fis = new FileInputStream(new File(commandInfo.get( inCmd.getIOFileArg() )));


            copyStreams(fis, os1);

            OutputStream os2 = outCmd.execute(workingDir);

            copyStreams(inCmd.getInputStream(), os2);

            FileOutputStream fos = new FileOutputStream(new File (workingDir+"\\"+commandInfo.get( outCmd.getIOFileArg() )));

            copyStreams(outCmd.getInputStream(), fos);

        } catch (IOException e) {
            e.printStackTrace();
            throw new BatchSyntaxException(e.getMessage());
        }
        
    }

    public void parse(Element element)
    {
        
        NodeList elementList = element.getElementsByTagName("cmd");
        
        Element currentElement = null;
        
        //Parses the cmds embedded in the pipe command
        for (int i = 0; i<2; i++) {
        	
        	currentElement = (Element)elementList.item(i);
        	
        	if(!currentElement.getAttribute("in").isEmpty()){
        		inCmd.parse(currentElement, true);
        	}
        	
        	if(!currentElement.getAttribute("out").isEmpty()){
        		outCmd.parse(currentElement, false);
        	}
        }
    }

    static void copyStreams(final InputStream is, final OutputStream os) {
        Runnable copyThread = (new Runnable() {
            @Override
            public void run()
            {
                try {
                    int achar;
                    while ((achar = is.read()) != -1) {
                        os.write(achar);
                    }
                    os.close();
                }
                catch (IOException ex) {
                    throw new RuntimeException(ex.getMessage(), ex);
                }
            }
        });
        new Thread(copyThread).start();
    }
}
