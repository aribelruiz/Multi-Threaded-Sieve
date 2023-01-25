import java.util.*;

public class MultiThreadThing implements Runnable {

    private int threadNumber;
    public MultiThreadThing(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public synchronized void run() {

        // Increment number of threads running
        Assignment1.threadsRunning++;
        System.out.println("THREAD " + threadNumber + " START, thread count: "+ Assignment1.threadsRunning);
        
        for (int i = 1; i <= 10; i++)
        {
            // Sleeping just for readability when testing
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
            }
        }

        // Updating threads running
        Assignment1.threadParking[threadNumber] = false;
        Assignment1.threadsRunning--;
        System.out.println("THREAD " + threadNumber + " FINISHED, thread count: "+ Assignment1.threadsRunning);
    }
}