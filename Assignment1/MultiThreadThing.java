import java.util.*;

public class MultiThreadThing implements Runnable {

    // ====================================== Class Variables ======================================
    private int threadNumber;

    // ========================================= Functions =========================================
    public MultiThreadThing(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public synchronized void run() {
 
        // Increments number of threads running
        Assignment1.threadsRunning++;

        // Finds composite numbers using the Sieve of Eratosthenes Algorithm
        for (int i = 2; i <= Math.sqrt(Assignment1.upperBound); i++)
        {
            if (Assignment1.isCompositeList[(int)i] == false)
            {
                // Calculates numbers with factor of i.
                for (int num = (int)Math.pow(i,2); num <= Assignment1.upperBound; num += i)
                {
                    if (Assignment1.isCompositeList[num] == false)
                    {
                        // Sets num as composite in composite list (number N represented by index N)
                        Assignment1.isCompositeList[num] = true;
                    }
                }
            }
        }

        // Sets executionDone to true when a thread has reached end of Sieve (i = sqrt(upperBound)) 
        Assignment1.executionDone = true;

        // Updates threads running
        Assignment1.threadParking[threadNumber] = false;
        Assignment1.threadsRunning--;
    }
}