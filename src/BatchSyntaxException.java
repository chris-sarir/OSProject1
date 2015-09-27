/**
 * Created by mdw130530- on 9/26/2015.
 */
public class BatchSyntaxException extends Exception{
    public String e;

    public BatchSyntaxException ( String e){
        super("Error Bad Batch Syntax: "+ e);
        this.e=e;
    }

    public String getError(){
        return e;
    }
}
