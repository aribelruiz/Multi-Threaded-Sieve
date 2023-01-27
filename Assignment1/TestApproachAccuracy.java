// Aribel Ruiz
// 01/26/2023

// ================================================
// COP4520 : Testing Multi-Threading Sieve Approach
// ================================================
//      Concepts of Parallel and Distributed Processing. 
//      This program tests multi-threading sieve approach.

import java.util.*;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestApproachAccuracy
{
    final private static boolean PRINT_TEST = true;
    final private static boolean YES_TESTING = true;

    public static void main(String args[])
    {
        testApproachAccuracy();
    }

    // Function tests accuracy of Multi-Threaded Sieve approach
    public static void testApproachAccuracy()
    {
        int upperBound;
        PrintStream console = System.out;

        try {
            PrintStream out = new PrintStream(new FileOutputStream("threadAccuracy.txt", false), false);
            System.setOut(out);
        } catch (IOException e) {
            System.out.println("An error occured.");
        }

        
        for (int i = 1; i < 9; i++)
        {
            upperBound = (int)Math.pow(10,i);

            // Prints FindPrimes() info for numbers 10^1 to 10^8
            Assignment1.FindPrimes(upperBound, PRINT_TEST, YES_TESTING);
        }

        System.setOut(console);
        System.out.println("'TestApproachAccuracy.java' file output written to 'threadAccuracy.txt'\n");

    }
} 
