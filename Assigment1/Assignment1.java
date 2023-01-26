// Aribel Ruiz
// 01/26/2023

// =========================================
// COP4520 : Assignment 1 - Multi-Threading
// =========================================
//      Concepts of Parallel and Distributed Processing Assignment 1. 
//      This is an introductory assignment to multi-threaded programming.

import java.util.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class Assignment1 
{
    // Variables for thread management
    public static int threadsRunning = 0;
    public static boolean executionDone = false; 

    // Creates a Hash Set storing primes and a Hash Set storing composites
    public static HashSet<Long> primeList = new HashSet<Long>();
    public static HashSet<Long> compositeList = new HashSet<Long>();

    // Variables for sum and prime count
    public static long primeSum = 0;
    public static long compSum = 0;
    public static long numOfPrimes = 0;

    // Creates a boolean array that checks how many threads are running
    public static boolean threadParking[] = new boolean[8];

    public static void main(String[] args)
    {
        // Starts timer
        long start = System.nanoTime();

        // Variable alongside FindOpenIndex() assists in checking if new thread can be started
        int parkingIndex;

        // Initializes prime and composite lists (not including 0 or 1)
        // for (long i = 2; i <= Math.pow(10,8); i++)
        for (long i = 2; i <= 50; i++)
        {
            primeList.add(i);
        }

        // Checks if program done (executionDone set to true in thread when upper bound is reached)
        while (!executionDone)
        {
            // Resets parking index variable
            parkingIndex = -1;

            // Checks if there is an opening for a new thread
            if ((parkingIndex = FindOpenIndex()) != -1)
            {
                // Creating new thread at (parkingIndex)
                MultiThreadThing myThing = new MultiThreadThing(parkingIndex);
                Thread myThread = new Thread(myThing);
                myThread.start();
            }
        }

        // Prints out information for Primes
        System.out.println("\nPRIMES: " + primeList);
        System.out.println("PRIME SUM: " + sum(primeList));
        System.out.println("# of PRIMES: " + numOfPrimes);
        // System.out.println("PRIME SUM: " + primeSum);
        // System.out.println("# of PRIMES: " + primeList.size());

        
        // Prints out information for Composites
        System.out.println("\nCOMPOSITES: " + compositeList);
        System.out.println("COMP SUM: " + sum(compositeList));
        // System.out.println("COMP SUM: " + compSum);


        // Ends Timer
        long end = System.nanoTime();

        // Prints execution time
        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.println("\nExecution time is " + formatter.format((end - start) / 1000000000d) + " seconds\n");

    }

    // Searches for open index to start thread
    public static int FindOpenIndex()
    {
        // Checks if index is open to start new thread
        for (int i = 0; i < threadParking.length; i++)
        {
            // If thread parking has an opening, returns index of opening
            if (threadParking[i] == false)
            {
                // Found Parking at index i
                threadParking[i] = true;
                return i;
            }
        }
        // If there are no openings in thread parking, return -1.
        return -1;
    }

    // Calculates the sum of the elements in a hash set
    public static long sum(HashSet<Long> sumList) 
    {
        long sum = 0;

        // Creates Iterator object.
        Iterator itr = sumList.iterator();

        // Sums all numbers in list and increments number of primes counter
        while (itr.hasNext()) 
        {
            sum += (long)itr.next();
            numOfPrimes++;  // remove
        }

        return sum;
    }

}



