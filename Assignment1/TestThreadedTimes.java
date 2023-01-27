// Aribel Ruiz
// 01/26/2023

// ==================================================
// COP4520 : Testing Multi-Threading Execution Times
// ==================================================
//      Concepts of Parallel and Distributed Processing. 
//      This program tests the multi-threaded sieve approach execution time and print it to threadedTimes.txt.

import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class TestThreadedTimes
{
    final private static boolean NO_PRINT = false;
    final private static boolean YES_TESTING = true;

    // Function calls testThreadedTimes()
    public static void main(String args[])
    {
        testThreadedTimes();
    }

    // Function gets FindPrime() approach execution times and prints them to 'threadedTimes.txt'
    public static void testThreadedTimes()
    {
        int upperBound;
        double threadedTime = 0;
        
        try {
            FileWriter myWriter = new FileWriter("threadedTimes.txt");
            
            // Prints execution time info of FindPrimes() for numbers 10^1 to 10^8
            for (int i = 1; i < 9; i++)
            {
                upperBound = (int)Math.pow(10,i);
                myWriter.write("Execution time of FindPrime() for " + upperBound + ":\n");

                threadedTime = Assignment1.FindPrimes(upperBound, NO_PRINT, YES_TESTING);
                myWriter.write(threadedTime + "s\n\n");
            }
            
            // Closing file writer
            myWriter.close();
            System.out.println("'TestThreadedTimes.java' output written to 'threadedTimes.txt'.\n");
                
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
} 
