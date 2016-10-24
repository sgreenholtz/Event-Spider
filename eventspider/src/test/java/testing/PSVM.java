package testing;

import org.apache.commons.io.*;
import java.io.File;

/**
 * Simple main method testing
 * @author Sebastian Greenholtz
 */
public class PSVM {

    public static void main(String[] args) throws Exception{
        String path = "/home/sebastian/Event-Spider/eventspider/indexes/test/eventspider.beans.EventBean";
        File dir = new File(path);
        if (dir.exists()) {
            FileUtils.deleteDirectory(dir);
        }

    }
}
