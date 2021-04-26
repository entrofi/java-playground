package collectdata.customcollectors;

import net.entrofi.studies.java8.demohelpers.DemoMetaDataHelper;
import net.entrofi.studies.java8.demohelpers.DemoRunner;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.partitioningBy;

/**
 * @author Hasan COMAK
 */
public class PrimeNumberCollection implements DemoRunner {

    public static final String TITLE_PRIME_NUMBER_COLLECTION = "This demo shows a simple use case for factory methods"
                + " provided by Collectors class where performance is an issue. "
                + "\n In other demos, however, you can see a custom collector implementation where the performance"
                + " issue is resolved. Note that, the performance issue here is we're checking every element from"
                + " 2 to root of the candidate however, we should only do the check for the prime numbers less "
                + "than candidate root. ";


    @Override
    public void run() {
        DemoMetaDataHelper.addTitle(TITLE_PRIME_NUMBER_COLLECTION);
        partitionPrimesDemo();
    }



    private void partitionPrimesDemo() {
        int n = 500;
        DemoMetaDataHelper.printMessage("Partition prime numbers up to " + n + " by \n"
                    + "IntStream.rangeClosed(2, n).boxed()\n"
                    + "                    .collect(partitioningBy(candidate -> isPrime(candidate)));\n"
                    + "where isPrime is checking whether candidate is divisible by any number upto candidates root: "
                    + "\n"
                    + " boolean isPrime(int candidate) {\n"
                    + "        int candidateRoot = (int) Math.sqrt((double) candidate);\n"
                    + "        return IntStream.rangeClosed(2, candidateRoot)\n"
                    + "                    .noneMatch(i -> candidate % i == 0);\n"
                    + "}");

        Map<Boolean, List<Integer>> primePartitionMap = partitionPrimes(n);

        final String decorator = "::";
        final String commaSpace = ", ";
        DemoMetaDataHelper.printList("Primes up to " + n,
                    primePartitionMap.get(true), commaSpace, decorator);
        DemoMetaDataHelper.printList("Non-primes up to " + n,
                    primePartitionMap.get(false),  commaSpace, decorator);
    }


    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                    .collect(partitioningBy(candidate -> isPrime(candidate)));
    }

    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                    .noneMatch(i -> candidate % i == 0);
    }

}
