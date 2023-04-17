import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SystemExecutor {
    enum OutputCollection {
        VOID,
        OUTPUT,
        ERRORS
    }
    
    private Runtime proc;
    private StringBuilder output;
    private OutputCollection dataCollection;
    
    public SystemExecutor(final OutputCollection data)
    {
        proc = Runtime.getRuntime();
        output = new StringBuilder();
        dataCollection = data;
    }
    
    public void execute(final String command) throws IOException
    {
        Process p = proc.exec(command);
        switch (dataCollection) {
            case VOID:
                break;
            case OUTPUT:
                String s;
                BufferedReader stdInput = new BufferedReader(new 
                     InputStreamReader(p.getInputStream()));
                     
                while ((s = stdInput.readLine()) != null) {
                    output.append(s);
                }
                break;
            case ERRORS:
                BufferedReader stdError = new BufferedReader(new 
                     InputStreamReader(p.getErrorStream()));
                     
                while ((s = stdError.readLine()) != null) {
                    output.append(s);
                }     
                break;
        }
    }
    
    public String getOutput()
    {
        final String out = output.toString();
        output.setLength(0);
        return out;
    }
}