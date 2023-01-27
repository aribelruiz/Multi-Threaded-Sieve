# COP4520_Assignment1
Concepts of Parallel and Distributed Processing - Assignment 1. 
This is an introductory assignment to multi-threaded programming.

## How to Run Program
This program was written using java. To run the program, cd into the Assignment1 directory and enter
the following into the command line:

    javac Assignment1.java
    java Assignment1

## Program Description
The following program find all prime numbers (from 1 to 10^8^) using multi-threaded programming and
the Sieve of Eratosthenes algorithm.

The following information from the program is then stored into a file called 'primes.txt':

    <execution time> <total number of primes found> <sum of all primes found>
    <top ten maximum primes, listed in order from lowest to highest>

## Summary of Approach
My approach to finding all prime numbers from 1 to N involves the use of the Sieve of Eratosthenes
and 8 concurrent threads. 

### Dividing Work Among Threads
To divide the work among all 8 threads, each thread is responsible for marking off
all the multiples of a given number as composite in a boolean list, where a value of true at index i
represents the number i being composite. The first thread is responsible for marking the first prime
number's multiples, the second thread is responsible for marking the second prime number's multiples,
and etc. 

The number each thread is responsible for is found by searching for the next integer from 2 to N 
that has been neither marked prime nor composite. This number is then marked as prime in a boolean 
list of prime numbers and all of its multiples are marked as composite if they have not been marked
by another thread running.Once a thread is finished executing, the thread becomes responsible for 
the next integer that has been marked as neither prime nor composite. Once all the multiples of the 
$\sqrt{N}$ have been marked as composite, all composite numbers should be in the process of being 
marked as composite by other threads due to the Sieve of Eratosthenes. Therefore, once the multiples
of $\sqrt{N}$ have been marked, no more threads will be created.

Once all threads finish executing, the boolean list of composite numbers is iterated through for 
numbers marked as false (prime numbers) to find the top 10 prime numbers, the total number of primes
found, and the sum of all the primes found.

## Experimental Evaulation
***TestApproachAccuracy.java*** tests the accuracy of my multi-threaded approach and prints output to
'threadedAccuracy.txt'. This program prints the execution time, total number of primes, and sum of 
all primes from 1 to N for values of N ranging from 10^1^ to 10^8^. I then compared these values 
individually to a table on https://primes.utm.edu/howmany.html that shows the total number of primes
from 1 to x, where x is values of 10^n^. This proved that my approach was working correctly. I also 
searched online for a list of prime numbers from 1 to N for different values of N and checked if the
top 10 prime numbers in my algorithm were correct as well as the sum.

***TestThreadedTimes.java*** prints all the execution times of Assignment1.java for values 10^1^ to 10^8^
and prints the output to 'threadedTimes.txt'. I compared these times to that of 'sieveTimes.txt' to 
see that for 10^8^, the multi-threaded approach runs faster.

***TestSieveTimes.java*** prints all the execution times of Assignment1.java for values 10^1^ to 10^8^
and prints the output to 'threadedTimes.txt'. I compared these times to that of 'sieveTimes.txt'.

I also tested to see if having multiple concurrent threads ran faster than having fewer threads. 
When I changed the max number of threads in Assignment1.java to less than 8, the program ran slower.
Therefore, the program runs faster with more threads. 

# Reasoning for Efficiency
The multi-threaded approach is more efficient because doing this approach sequentially will create
a longer runtime as each prime number's multiples can only be crossed off after all the multiples of
prime numbers before it have been marked. With large numbers the wait becomes very inefficient, but
with multi-threading the process can happen concurrently which makes it faster.


