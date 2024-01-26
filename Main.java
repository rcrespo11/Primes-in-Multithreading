import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    static int nPrimes = 0;
    static AtomicLong sumOfPrimes = new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedQueue<Integer> allPrimes = new ConcurrentLinkedQueue<>();

        // Redirect output to a file
        try {
            PrintStream out = new PrintStream(new FileOutputStream("primes.txt"));
            System.setOut(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        long startTime = System.currentTimeMillis();

        List<myThread> threadList = new ArrayList<>();

        int totalNumbers = 100000000;  // Specify the total numbers to check for primes
        int threads = 8;

        int numbersPerThread = totalNumbers / threads;

        for (int i = 0; i < threads; i++) {
            int low = i * numbersPerThread + 1;
            int high = (i + 1) * numbersPerThread;
            myThread thread = new myThread(low, high, allPrimes);
            threadList.add(thread);
        }

        // Start threads
        for (myThread thread : threadList) {
            thread.start();
        }

        // Join threads
        for (myThread thread : threadList) {
            thread.join();
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        nPrimes = allPrimes.size();

        List<Integer> sortedPrimes = new ArrayList<>(allPrimes);
        Collections.sort(sortedPrimes);

        System.out.println("Total primes: " + nPrimes);
        System.out.println("Sum of primes: " + sumOfPrimes.get());
        System.out.println("Execution time: " + elapsedTime + " milliseconds");

        System.out.println("Top ten maximum primes:");
        ArrayList<Integer> print = new ArrayList<>();
        int topTenSize = Math.min(10, sortedPrimes.size());
        for (int i = sortedPrimes.size() - 1; i >= sortedPrimes.size() - topTenSize; i--) {
            print.add(sortedPrimes.get(i));
        }
        Collections.reverse(print);
        for (int i : print) {
            System.out.println(i);
        }
    }

    static class myThread extends Thread {
        private int low;
        private int target;
        private ConcurrentLinkedQueue<Integer> allPrimes;

        public myThread(int low, int target, ConcurrentLinkedQueue<Integer> allPrimes) {
            this.target = target;
            this.low = low;
            this.allPrimes = allPrimes;
        }

        public void run() {
            for (int num = Math.max(2, low); num <= target; num++) {
                if (isPrime(num)) {
                    allPrimes.add(num);
                    sumOfPrimes.addAndGet(num);
                }
            }
        }

        private static boolean isPrime(int num) {
            if (num <= 1) {
                return false;
            }

            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    return false;
                }
            }

            return true;
        }
    }
}





















        
	
	

 
 











