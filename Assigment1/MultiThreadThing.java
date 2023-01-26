import java.util.*;

public class MultiThreadThing implements Runnable {

    private int threadNumber;
    public MultiThreadThing(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public synchronized void run() {

        // long upperBound = (long)Math.pow(10,8);
        long upperBound = 50;   // used for smaller testing
        
        // Increment number of threads running
        // System.out.println("THREAD " + threadNumber + " STARTED, thread count: "+ Assignment1.threadsRunning);
        Assignment1.threadsRunning++;
        
        long num = 1;
        for (long i = 2; i <= upperBound; i++)
        {
            if (Assignment1.primeList.contains(i) == true && Assignment1.compositeList.contains(i) == false)
            {
                // Calculates numbers with factor of i.
                if (i <= (long)Math.floor(Math.sqrt(upperBound)))
                {
                    // Starts at i^2 when adding to list of composites.
                    num = (long)Math.pow(i,2);
                    while (num <= upperBound)
                    {                    
                        // If num is not in compositeList, add to compositeList and remove from primeList
                        if (Assignment1.compositeList.contains(num) == false )
                        {
                            // Adds num to composite list and removes num from primeList
                            Assignment1.compositeList.add(num);
                            Assignment1.primeList.remove(num);
                                                        
                            // Assignment1.compSum += num;
                            // Assignment1.primeSum -= num;
                        }
                        num = num + i;
                    }
                    
                    // ==========================TO BE ALTERED======================================
                    // // Adds primes to prime list
                    // if (Assignment1.compositeList.contains(i) == false)
                    // {
                    //     // Adding i to prime list
                    //     // System.out.println("Adding PRIME " + i + " from THREAD " + threadNumber);
                    //     // Assignment1.primeList.add(i);
                    //     // Assignment1.primeSum += i;
                    //     break;
                    // }
                    // ==========================TO BE ALTERED======================================

                }
                else
                {
                    Assignment1.executionDone = true;
                    break;
                }
            }
        }

        // Updates threads running
        Assignment1.threadParking[threadNumber] = false;
        Assignment1.threadsRunning--;
        // System.out.println("THREAD " + threadNumber + " FINISHED, thread count: "+ Assignment1.threadsRunning);
    }
}