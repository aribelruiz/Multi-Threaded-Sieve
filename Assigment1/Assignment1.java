// Aribel Ruiz
// 01/26/2023

// =========================================
// COP4520 : Assignment 1 - Multi-Threading
// =========================================
//      Concepts of Parallel and Distributed Processing Assignment 1. 
//      This is an introductory assignment to multi-threaded programming.

import java.util.*;

public class Assignment1 
{
    public static int threadsRunning = 0;

    // Creating a Hash Set storing primes and a Hash Set storing composites. 
    public static HashSet<Long> primeList = new HashSet<Long>();
    public static HashSet<Long> compositeList = new HashSet<Long>();

    // Creates a boolean array that checks how many threads are running
    public static boolean threadParking[] = new boolean[8];


    public static void main(String[] args)
    {
        int parkingIndex;

        for (int i = 0; i < 1000; i++) 
        {
            // Resetting Parking Index variable
            parkingIndex = -1;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
            }

            // Checks if there is an opening for a new thread
            if ((parkingIndex = FindOpenIndex()) != -1)
            {
                System.out.println("CREATING NEW THREAD @ " + (parkingIndex) + ", thread count: " + threadsRunning);

                MultiThreadThing myThing = new MultiThreadThing(parkingIndex);
                Thread myThread = new Thread(myThing);
                myThread.start();
            }
            else
            {
                System.out.println("CONTINUE");
            }
        }

        System.out.println("DONE");
    }


    public static int FindOpenIndex()
    {
        System.out.println("\nFINDING PARKING");
        for (int i = 0; i < threadParking.length; i++)
        {
            // If thread parking has an opening, return index of opening
            if (threadParking[i] == false)
            {
                System.out.println("Parking at index: " + i);
                threadParking[i] = true;
                return i;
            }
        }

        // If there are no openings in thread parking, return -1.
        System.out.println("No Parking.");
        return -1;
    }
}



