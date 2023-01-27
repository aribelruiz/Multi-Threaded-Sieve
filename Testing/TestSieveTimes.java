// Aribel Ruiz
// 01/26/2023

// ===============================================
// COP4520 : Testing Normal Sieve Execution Times
// ===============================================
//      Concepts of Parallel and Distributed Processing. 
//      This program tests the normal sieve approach execution time and print it to sieveTimes.txt.

import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class TestSieveTimes
{
    final private static boolean NO_PRINT = false;

    // Function calls testSieveTimes()
    public static void main(String args[])
    {
        testSieveTimes();
    }

    // Function gets NormalSieve() approach execution times and prints them to 'sieveTimes.txt'
    public static void testSieveTimes()
    {
        int upperBound;
        double sieveTime = 0;
        
        try {
            FileWriter myWriter = new FileWriter("sieveTimes.txt");
            
            // Prints execution time info of NormalSieve() for numbers 10^1 to 10^8
            for (int i = 1; i < 9; i++)
            {
                upperBound = (int)Math.pow(10,i);
                myWriter.write("Execution time of NormalSieve() for " + upperBound + ":\n");

                sieveTime = TestNormalSieve.NormalSieve(upperBound, NO_PRINT);
                myWriter.write(sieveTime + "s\n\n");
            }
            
            // Closing file writer
            myWriter.close();
            System.out.println("'TestSieveTimes.java' output written to 'sieveTimes.txt'.\n");
                
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
} 
