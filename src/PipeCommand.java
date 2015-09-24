import org.w3c.dom.Element;

import java.io.*;
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

    private PipeCmdCommand inCmd = new PipeCmdCommand();
    private PipeCmdCommand outCmd = new PipeCmdCommand();



    public String describe()
    {
        return new String(); //Todo: description implementation goes here
    }

    public void execute(String workingDir)
    {
        /*todo: execution implementation goes here*/
        System.out.println("Executing PipeCommand");//TODO:Remove before delivery
        try {
            OutputStream os1 = inCmd.execute(workingDir);

            FileInputStream fis = new FileInputStream(new File(commandInfo.get( inCmd.getIOFileArg() )));

            copyStreams(fis, os1);

            OutputStream os2 = outCmd.execute(workingDir);

            copyStreams(inCmd.getInputStream(), os2);

            FileOutputStream fos = new FileOutputStream(new File (commandInfo.get( outCmd.getIOFileArg() )));

            copyStreams(outCmd.getInputStream(), fos);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parse(Element element)
    {
        System.out.println("Parsing the in command"); // Todo: remove before delivery

        //Todo: Catch some exceptions before delivery
        for (int i = 0; i<2; i++) {
            if (element.getElementsByTagName("cmd").item(i).getAttributes().getNamedItem("in") != null) {
                inCmd.parse(((Element)element.getElementsByTagName("cmd").item(i)), true);
            }
            if (element.getElementsByTagName("cmd").item(i).getAttributes().getNamedItem("out") != null) {
                outCmd.parse(((Element)element.getElementsByTagName("cmd").item(i)), false);
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
