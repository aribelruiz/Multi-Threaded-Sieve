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
    // ====================================== Class Variables ======================================

    // Variables assist in checking the number of threads running and available
    public static boolean threadParking[] = new boolean[8];
    private static int openParkingIndex = -1;

    // Variables for thread management
    public static int threadsRunning = 0;
    public static boolean executionDone = false; 

    // Variable defines upper bound when searching for all prime numbers
    public static int upperBound = (int)Math.pow(10,8);

    // Variables for prime numbers
    public static HashSet<Long> primeList = new HashSet<Long>();
    public static long numOfPrimes = 0;

    // Boolean list storing whether each number is composite (number N represented by index N)
    public static boolean[] isCompositeList = new boolean[upperBound + 1]; 


    // ========================================= Functions =========================================
    public static void main(String[] args)
    {
        // Starts timer
        long start = System.nanoTime();

        // Checks for program finish (executionDone set to true in thread when upperBound reached)
        while (!executionDone)
        {
            // Resets openParkingIndex variable
            openParkingIndex = -1;

            // Checks if there is an opening for a new thread (-1 when no threads available)
            if ((openParkingIndex = FindOpenIndex()) != -1)
            {
                // Creates new thread at openParkingIndex
                MultiThreadThing myThing = new MultiThreadThing(openParkingIndex);
                Thread myThread = new Thread(myThing);
                myThread.start();
            }
        }  

        // Adds numbers into prime list that are not in composite list (excluding 0 and 1)
        for (long i = 2; i <= upperBound; i++)
        {
            if (isCompositeList[(int)i] == false)
            {
                Assignment1.primeList.add(i);
            }
        }

        // Prints out information for prime numbers
        System.out.println("\nPRIME SUM: " + sumPrimes(primeList));
        System.out.println("# of PRIMES: " + numOfPrimes);
      
        // Ends timer
        long end = System.nanoTime();

        // Prints execution time
        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.println("\nExecution time is " + formatter.format((end - start) / 1000000000d) + " seconds\n");

    }

    // Function searches for open index to start new thread
    public static int FindOpenIndex()
    {
        for (int i = 0; i < threadParking.length; i++)
        {
            // If open parking found, closes parking and returns index of opening
            if (threadParking[i] == false)
            {
                // Found Parking at index i
                threadParking[i] = true;
                return i;
            }
        }

        // Returns -1 if there are no openings in thread parking
        return -1;
    }

    // Function calculates the sum of the elements in a hash set of type 'Long'
    public static long sumPrimes(HashSet<Long> sumList) 
    {
        long sum = 0;
        Iterator itr = sumList.iterator();

        // Sums all numbers in list and increments total numofPrimes counter
        while (itr.hasNext()) 
        {
            sum += (long)itr.next();
            numOfPrimes++;
        }

        return sum;
    }
}



