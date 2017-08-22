package collectdata.customcollectors;

import net.entrofi.studies.java8.demohelpers.DemoMetaDataHelper;
import net.entrofi.studies.java8.demohelpers.DemoRunner;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * @author Hasan COMAK
 */
public class PrimeNumberCustomCollection implements DemoRunner {



    @Override
    public void run() {
        DemoMetaDataHelper.addTitle("To see how the custom collector implemented take a look at "
                    + "the file PrimeNumberCollector.java. This example compares the performance of this custom "
                    + "collector and standard collectors provided by java.");
        Scanner scanner = new Scanner(System.in);
        int n = scanN(scanner);
        long start;
        long runJavaColl;
        long runCustomColl;
        while (n != -1) {
            start = System.nanoTime();
            PrimeNumberCollection.partitionPrimes(n);
            runJavaColl = System.nanoTime() - start;

            start = System.nanoTime();
            Map<Boolean, List<Integer>> primePartitionMap = partitionPrimes(n);
            runCustomColl = System.nanoTime() - start;

            printMap(scanner, primePartitionMap);
            DemoMetaDataHelper.printMessage("\nRunning time difference (customCollector) - (standardCollector) = "
                        + (runCustomColl - runJavaColl) / Math.pow(10, 9) + " seconds.\n");

            n = scanN(scanner);
        }
        scanner.next();
    }

    private int scanN(Scanner scanner) {
        final String message = "Please enter a positive integer to find the prime numbers less than or equal "
                    + "to it. "
                    + "or press -1 to exit: ";
        DemoMetaDataHelper.printMessage(message);
        int n = -1;
        try {
            n = scanner.nextInt();
        } catch (InputMismatchException e) {
            DemoMetaDataHelper.printMessage("Invalid input! Maximum allowed input is " + Integer.MAX_VALUE + "\n");
            scanner.next();
            n = scanN(scanner);
        }
        return n;
    }

    private void printMap(Scanner scanner, Map<Boolean, List<Integer>> primePartitionMap) {
        DemoMetaDataHelper.printMessage("Print maps? (y/n)");
        String print = scanner.next();
        if ("y".equalsIgnoreCase(print)) {
            final String commaDelim = ", ";
            final String decorator = "::";
            DemoMetaDataHelper.printList("Primes ", primePartitionMap.get(true), commaDelim, decorator);
            DemoMetaDataHelper.printList("Non-primes", primePartitionMap.get(false), commaDelim, decorator);
        } else if ("n".equalsIgnoreCase(print)) {
            return;
        } else {
            DemoMetaDataHelper.printMessage(print + " is an invalid command. Please enter 'y' or 'n'. ");
            printMap(scanner, primePartitionMap);
        }
    }

    private Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                    .collect(new PrimeNumberCollector());
    }
}
