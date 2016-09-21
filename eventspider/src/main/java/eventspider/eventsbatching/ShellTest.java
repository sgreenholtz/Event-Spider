package eventspider.eventsbatching;

import org.apache.log4j.Logger;

import java.io.*;

/**
 * Practicing running shell scripts from inside Java
 * @author Sebastian Greenholtz
 */
public class ShellTest {
    private static final Logger logger = Logger.getLogger(ShellTest.class);

    public static void main(String[] args) throws IOException {
        ShellTest shellTest = new ShellTest();
        BufferedReader reader = shellTest.readScript();
        String line = "";
        while (reader.ready()) {
            line = reader.readLine();
            logger.info(line);
        }
    }

    private BufferedReader readScript() throws IOException {
        ProcessBuilder builder = new ProcessBuilder("eventspider/bin/test.sh");
        Process process = builder.start();
        return new BufferedReader(new InputStreamReader(process.getInputStream()));
    }
}
