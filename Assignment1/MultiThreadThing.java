import java.util.*;

public class MultiThreadThing implements Runnable {

    // ====================================== Class Variables ======================================
    private int threadNumber;

    // ========================================= Functions =========================================
    public MultiThreadThing(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    // Function returns if integer i is prime
    private static synchronized boolean getPrime(int i) {
        return Assignment1.isPrimeList[i];
    }

    // Function sets integer i as prime
    private static synchronized void setPrime(int i) {
        Assignment1.isPrimeList[i] = true;
    }

    @Override
    public synchronized void run() {

        // Increments number of threads running
        Assignment1.threadsRunning++;

        int i;
        // Finds composite numbers using the Sieve of Eratosthenes Algorithm
        for (i = 2; i <= Math.sqrt(Assignment1.upperBound); i++)
        {
            if (Assignment1.isCompositeList[i] == false && getPrime(i) == false)
            {
                // Sets numbers as prime so other threads do not repeat sieve with already processed prime number
                setPrime(i);

                // Calculates numbers with factor of i.
                for (int num = (int)Math.pow(i,2); num <= Assignment1.upperBound; num += i)
                {
                    if (Assignment1.isCompositeList[num] == false)
                    {
                        // Sets num as composite in composite list (number N represented by index N)
                        Assignment1.isCompositeList[num] = true;
                    }
                }
                break;
            }
        }
        
        // If Sieve has already been completed, finish execution of program
        if (i >= Math.sqrt(Assignment1.upperBound))
            Assignment1.executionDone = true;

        // Updates threads running
        Assignment1.threadParking[threadNumber] = false;
        Assignment1.threadsRunning--;
        
    }
}