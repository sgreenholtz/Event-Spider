package dbOperations;

/**
 * This is a test of multithreading
 */

public class MultithreadTest implements Runnable {
    private Thread thread;
    private String threadName = "myThread";
    

    public void run() {
        try {
            System.out.print("This is what happens with this thread");
        } catch (InteruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    public void start() {
        if (thread == null) {
            thread = new Thread(this, threadName);
            thred.start();
        }   
    }
}