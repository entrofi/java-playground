package lambdasandmethodrefs.composition;



import net.entrofi.studies.java8.demohelpers.DemoMetaDataHelper;
import net.entrofi.studies.java8.demohelpers.DemoRunner;

import java.util.function.Function;

public class FunctionComposition implements DemoRunner {

    static final Function<Integer, Integer> incrementByOne = x -> x+1;
    static final Function<Integer, Integer> squareFunction = x -> x * x;
    static final Function<Integer, Integer> incrementAndThenSquare = incrementByOne.andThen(squareFunction);
    static final Function<Integer, Integer> incrementOfSquare = incrementByOne.compose(squareFunction);

    @Override
    public void run() {
        andThenDemo();
        functionComposition();
    }


    static void functionComposition() {
        DemoMetaDataHelper.addTitle("Function composition example" +
                "\nProcessing is similar to that of mathematical function composition " +
                "\n           f(x), g(x) -> f(g(x))  ");
        int x = 2;
        System.out.println("Increment  " + x + " by one = " + incrementByOne.apply(x));
        System.out.println("Square " + x + " = " + squareFunction.apply(x));
        System.out.println("incrementOfSquare(" + x + ") = " + incrementOfSquare.apply(x));
    }

    static void andThenDemo() {
        DemoMetaDataHelper.addTitle("Function<T,S>#andThen(Function f) demo: " +
                "\n        Function<Integer, Integer> incrementByOne = x -> x+1;\n" +
                "        Function<Integer, Integer> squareFunction = x -> x * x;\n" +
                "        Function<Integer, Integer> incrementAndThenSquare = incrementByOne.andThen(squareFunction);");

        int x = 4;
        System.out.println("Increment  " + x + " by one = " + incrementByOne.apply(x));
        System.out.println("Square " + x + " = " + squareFunction.apply(x));
        System.out.println("Increment " + x + " by one and then square the incremented value = " +
                incrementAndThenSquare.apply(x));

    }
}
