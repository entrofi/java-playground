/*
 * Licensed under GPL.
 */
package intermediateandterminalops;


import net.entrofi.studies.java8.demohelpers.DemoMetaDataHelper;
import net.entrofi.studies.java8.demohelpers.DemoRunner;
import net.entrofi.studies.java8.demohelpers.models.Flight;
import net.entrofi.studies.java8.demohelpers.sampledatahelpers.FlightDataDemoHelper;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * TODO add javadoc
 */
public class IntermediateAndTerminalOpsDemo implements DemoRunner {


    private static final String TITLE = "This example shows how intermediate operation execution proceeds." +
            "\n Demo shows the following: " +
            "\n\t1. Loop fusion concept: Despite map() and filter are two seperate operations they were merged into\n" +
            "the same pass" +
            "\n\t2. Operation merging " +
            " Predicate<Flight> fromESBOrToESB = (Flight f) -> {\n" +
            "            System.out.println(\"Filtering flight with predicate origin or destination is ESB \" + \"[origin: \" \n" +
            "                    + f.getOrigin() + \", destination: \" + f.getDestination());\n" +
            "            return f.getOrigin().equals(\"ESB\") || f.getDestination().equals(\"ESB\");\n" +
            "        };\n" +
            "\n" +
            "\n" +
            "        List<String> flightNumbers = flightList.stream().filter(fromESBOrToESB).map(f -> {\n" +
            "            System.out.println(\"Mapping to flight numbers \" + f.getFlightNumber());\n" +
            "            return f.getFlightNumber();\n" +
            "        }).collect(Collectors.toList());";


    @Override
    public void run() {
        showIntermediatePipelineExec();
        summarize();
    }


    static void showIntermediatePipelineExec() {
        DemoMetaDataHelper.addTitle(TITLE);
        FlightDataDemoHelper flightDataDemoHelper = new FlightDataDemoHelper();
        List<Flight> flightList = flightDataDemoHelper.initFlights(15, true);
        DemoMetaDataHelper.printList("Original list", flightList);

        Predicate<Flight> fromESBOrToESB = (Flight f) -> {
            DemoMetaDataHelper.printMessage("Filtering flight with predicate origin or destination is ESB " + "[origin: "
                    + f.getOrigin() + ", destination: " + f.getDestination() + "\n");
            return f.getOrigin().equals("ESB") || f.getDestination().equals("ESB");
        };


        List<String> flightNumbers = flightList.stream().filter(fromESBOrToESB).map(f -> {
            DemoMetaDataHelper.printMessage("Mapping to flight numbers " + f.getFlightNumber() + "\n");
            return f.getFlightNumber();
        }).collect(Collectors.toList());

        DemoMetaDataHelper.printList("", flightNumbers);

    }


    static void summarize() {
        String message = "Here are some key concepts to take away :\n\t" +
                "1. A stream is a sequence of elements from a source that supports data processing operations. \n\t" +
                "2. Streams make use of internal iteration: the iteration is abstracted away through operations such as " +
                "filter , map , and sorted . \n\t" +
                "3.There are two types of stream operations: intermediate and terminal operations. \n\t" +
                "4. Intermediate operations such as filter and map return a stream and can be chained together. \n\t" +
                "They’re used to set up a pipeline of operations but don’t produce any result. " +
                "5.Terminal operations such as forEach and count return a nonstream value and process a stream " +
                "pipeline to return a result \n\t" +
                "6. The elements of a stream are computed on demand.\n";
        DemoMetaDataHelper.addTitle(message);
    }
}
