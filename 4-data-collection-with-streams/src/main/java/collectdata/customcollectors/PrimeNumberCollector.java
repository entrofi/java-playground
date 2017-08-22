package collectdata.customcollectors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

/**
 * @author Hasan COMAK
 */
public class PrimeNumberCollector implements
            Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {


    /**
     * Supply the partition map with empty lists for each key (true, false).
     *
     * @return empty instance for reduction
     */
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>() {
            {
                put(true, new ArrayList<>());
                put(false, new ArrayList<>());

            }
        };
    }


    /**
     * This is the actual reduction funtion that accumulates the current stream element to the actual reduction. For
     * Prime number case this function will add the current stream element to the corresponding list in partition map
     * .<br/>
     *
     * @return
     */
    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
            acc.get(
                        isPrime(acc.get(true), candidate)
            ).add(candidate);
        };
    }

    /**
     * @return
     */
    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> leftMap, Map<Boolean, List<Integer>> rightMap) -> {
            leftMap.get(true).addAll(rightMap.get(true));
            leftMap.get(false).addAll(rightMap.get(false));
            return leftMap;
        };
    }

    /**
     * @return
     */
    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    /**
     * @return
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));
    }


    private boolean isPrime(List<Integer> primes, int candidate) {
        return getSubListLessThan(primes, candidate).stream().noneMatch(i -> candidate % i == 0);
    }

    /**
     * @param originalList sorted prime number list
     * @param candidate
     * @return
     */
    private List<Integer> getSubListLessThan(List<Integer> originalList, Integer candidate) {
        int root = (int) Math.sqrt((double) candidate);
        for (int i = 0; i < originalList.size(); i++) {
            if (originalList.get(i) > root) {
                return originalList.subList(0, i);
            }
        }
        return originalList;
    }
}
