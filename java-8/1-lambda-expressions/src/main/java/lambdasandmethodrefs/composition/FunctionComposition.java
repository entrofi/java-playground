package lambdasandmethodrefs.composition;


import net.entrofi.studies.java8.demohelpers.DemoMetaDataHelper;
import net.entrofi.studies.java8.demohelpers.DemoRunner;

import java.util.function.Function;

public class FunctionComposition implements DemoRunner {

    static final Function<Integer, Integer> INCREMENT_BY_ONE = x -> x + 1;
    static final Function<Integer, Integer> SQUARE_FUNCTION = x -> x * x;
    static final Function<Integer, Integer> INCREMENT_AND_THEN_SQUARE = INCREMENT_BY_ONE.andThen(SQUARE_FUNCTION);
    static final Function<Integer, Integer> INCREMENT_OF_SQUARE = INCREMENT_BY_ONE.compose(SQUARE_FUNCTION);

    static void functionComposition() {
        DemoMetaDataHelper.addTitle("Function composition example"
                    + "\nProcessing is similar to that of mathematical function composition "
                    + "\n           f(x), g(x) -> f(g(x))  ");
        int x = 2;
        DemoMetaDataHelper.printMessage("Increment  " + x + " by one = " + INCREMENT_BY_ONE.apply(x));
        DemoMetaDataHelper.printMessage("Square " + x + " = " + SQUARE_FUNCTION.apply(x));
        DemoMetaDataHelper.printMessage("INCREMENT_OF_SQUARE(" + x + ") = " + INCREMENT_OF_SQUARE.apply(x));
    }

    static void andThenDemo() {
        DemoMetaDataHelper.addTitle("Function<T,S>#andThen(Function f) demo: "
                    + "\n        Function<Integer, Integer> incrementByOne = x -> x+1;\n"
                    + "        Function<Integer, Integer> squareFunction = x -> x * x;\n"
                    + "        Function<Integer, Integer> incrementAndThenSquare ="
                    + " incrementByOne.andThen(squareFunction);");

        int x = 4;
        DemoMetaDataHelper.printMessage("Increment  '" + x + "' by one = " + INCREMENT_BY_ONE.apply(x));
        DemoMetaDataHelper.printMessage("Square '" + x + "' -> " + SQUARE_FUNCTION.apply(x));
        DemoMetaDataHelper.printMessage("Increment " + x + " by one and then square the incremented value = "
                    + INCREMENT_AND_THEN_SQUARE.apply(x));

    }

    @Override
    public void run() {
        andThenDemo();
        functionComposition();
    }
}
