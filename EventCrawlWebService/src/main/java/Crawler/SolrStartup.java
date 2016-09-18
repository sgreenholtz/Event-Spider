package Crawler;

import java.io.*;

/**
 * Starts up the Solr instance, if not already started
 * @author Sebastian Greenholtz
 */
public class SolrStartup {

    /**
     * Runs the start method, logging errors
     * @throws IOException
     * TODO Get error output and save into logs/solr.log
     */
    public void start() throws IOException {
        Process process = startProcess();
        process.getOutputStream();
    }

    /**
     * Gets the startup script and returns the running process
     * @return Process of startup
     * @throws IOException
     */
    private Process startProcess() throws IOException {
        ProcessBuilder builder = new ProcessBuilder("EventCrawlWebSerice/bin/solrStartup.sh");
        return builder.start();
    }
}
