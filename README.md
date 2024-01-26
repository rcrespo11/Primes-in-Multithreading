# Primes-in-Multithreading
The assignment uses multithreading as a solution to find prime numbers up to 10^8.

To compile the program:
javac Main.java

To execute the program, no user input is needed. Simply run:
java Main

Within the program, you can edit the number of required threads and the target number to find prime numbers. 
The program achieved an average time of 15 seconds to find all prime numbers up to the desired number. 
An optimized algorithm with a time complexity of O(√n) was used to find prime numbers.

When testing with different values:

For 10^7, it took 642 milliseconds.
For 10^6, the execution time was 44 milliseconds.
The program defines the myThread class, which extends Thread to perform independent calculations for each started thread. Inside this class, the isPrime method is used to identify prime numbers in each thread, which are then stored in a collection for analysis. The ouput will be printed to a file named primes.txt.
