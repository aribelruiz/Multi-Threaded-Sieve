// Aribel Ruiz
// 01/26/2023

// ==================================================================
// COP4520 : Sieve of Eratosthenes Algorithm Without Multi-Threading
// ==================================================================
//      Program is identical to Assignment1.java but without multi-threading to test and prove that
//      the program execution time is better with multi-threading

import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class TestNormalSieve {

    public static void main(String args[]) 
    {
        int upperBoundSieve = (int)Math.pow(10,8);
        // int upperBoundSieve = 50;

        boolean[] isCompositeListSieve = new boolean[upperBoundSieve + 1]; 

        long[] top10Primes = new long[10];
        int top10Counter = 0;
        long numOfPrimes = 0;
        long primeSum = 0; 

        // Starts timer
        long start = System.nanoTime();

        // Finds composite numbers using the Sieve of Eratosthenes Algorithm
        for (int i = 2; i <= Math.sqrt(upperBoundSieve); i++)
        {
            if (isCompositeListSieve[(int)i] == false)
            {
                // Calculates numbers with factor of i.
                for (int num = (int)Math.pow(i,2); num <= upperBoundSieve; num += i)
                {
                    if (isCompositeListSieve[num] == false)
                    {
                        // Sets num as composite in composite list (number N represented by index N)
                        isCompositeListSieve[num] = true;
                    }
                }
            }
        }

         // Finds prime numbers (number is prime if not found in composite list (excluding 0 and 1))
         for (long i = upperBoundSieve; i >= 2; i--)
         {
             if (isCompositeListSieve[(int)i] == false)
             {
                 // Assures top 10 primes are added to top10Primes[]
                 if (top10Counter < 10)
                     top10Primes[top10Counter++] = i;
 
                 // Increments number of primes and adds primes to primeSum
                 numOfPrimes++;
                 primeSum += i;
             }
         }
 
         // Creates primes.txt file to print output
         try {
             File outputFile = new File("normalSievePrimes.txt");
         
             if (outputFile.createNewFile())
                 System.out.println("File created: " + outputFile.getName());
             else
                 System.out.println("File '" + outputFile.getName() + "' already exists.");
 
         } catch (IOException e) {
             System.out.println("An error occured.");
             e.printStackTrace();
         }
 
         // Ends timer
         long end = System.nanoTime();
 
         // Prints execution time
         NumberFormat formatter = new DecimalFormat("#0.00000");
 
         // Writes prime number information to prime.txt file
         try {
             FileWriter myWriter = new FileWriter("normalSievePrimes.txt");
             
             // Printing <execution time (in seconds)> <total number of primes found> <sum of all primes found> to output 
             myWriter.write(formatter.format((end - start) / 1000000000d) + "s ");
             
             myWriter.write(numOfPrimes + " ");
             myWriter.write(primeSum + " ");
 
             // Printing <top 10 maximum primes, listed in order from lowest to highest> to output 
             myWriter.write("\n" + printTop10Primes(top10Primes));
             
             // Closing file writer
             myWriter.close();
             System.out.println("Successfully wrote output to 'normalSievePrimes.txt'.\n");
         } catch (IOException e) {
             System.out.println("An error occurred.");
             e.printStackTrace();
         }
    }

    // Function returns the top 10 prime numbers as a string (from lowest to highest)
    public static String printTop10Primes(long[] top10)
    {
        String top10Str = "";
        top10Str += "[";
        for (int i = top10.length - 1; i >= 0; i--)
        {
            if (i == 0)
            {
                top10Str += (top10[i]);
                break;
            }

            top10Str += (top10[i] + ", ");
        }
        top10Str += "]\n";

        return top10Str;
    }
}