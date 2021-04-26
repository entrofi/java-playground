package collectdata.reducingandsummarizing;


import net.entrofi.studies.java8.demohelpers.DemoMetaDataHelper;
import net.entrofi.studies.java8.demohelpers.DemoRunner;
import net.entrofi.studies.java8.demohelpers.models.Flight;
import net.entrofi.studies.java8.demohelpers.sampledatahelpers.FlightDataDemoHelper;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.stream.Collectors.reducing;

public class SummarizationWithReduction implements DemoRunner {

    private static final String ESB_CODE = "ESB";
    private static final String IST_CODE = "IST";
    private static final String MSG_DECO = "-";
    private static final String SPACE = " ";
    private final List<Flight> flightList = generateFlightList();

    private final Predicate<Flight> flightFromESBToIST =
            f -> ESB_CODE.equals(f.getOrigin()) && IST_CODE.equals(f.getDestination());

    private final Predicate<Flight> flightFromISTToESB =
            f -> IST_CODE.equals(f.getOrigin()) && ESB_CODE.equals(f.getDestination());

    @Override
    public void run() {
        DemoMetaDataHelper.addTitle("This demo shows alternate uses for the Collectors.reducing(...) function.\n"
                +
                "All of the cases handled in previous examles; finding max and min by maxBy, minBy, Joining, \n"
                +
                "summing, summarizing etc are the generalizations of reducing function for special use cases. ");
        DemoMetaDataHelper.printList("Flight plan", flightList);
        findFlightsFromESBToIST();
        findFlightsBetweenESBAndIST();
    }


    private void findFlightsFromESBToIST() {
        DemoMetaDataHelper.printMessage("Find flights from ESB to IST and reduce flight numbers to a string. \n"
                        + "The overload of reducing function used here is: reduce( initVal, "
                        + "transformationFunction, aggregationFunction)"
                        + "flightList.stream()\n "
                        + " .filter(f -> \"ESB\".equals(f.getOrigin()) && \"IST\".equals(f.getDestination()))\n"
                        + " .collect(\n"
                        + "           reducing(\"\", Flight::getFlightNumber, (fn1, fn2) -> fn1 + \" \" + "
                        + "fn2)\n"
                        + "            );",
                MSG_DECO);
        String flights = flightList.stream()
                .filter(flightFromESBToIST)
                .collect(reducing("", Flight::getFlightNumber, (fn1, fn2) ->  fn1 + SPACE + fn2));
        DemoMetaDataHelper.printMessage("Flights from ESB to IST: " + flights);
    }


    private void findFlightsBetweenESBAndIST() {
        DemoMetaDataHelper.printMessage("flightList.stream()\n"
                + "                    .filter(flightFromESBToIST.or(flightFromISTToESB))\n"
                + "                    .map(Flight::getFlightNumber)\n"
                + "                    .collect(reducing((fn1, fn2) -> fn1 + \" \" + fn2));", MSG_DECO);
        Optional<String> flights = flightList.stream()
                .filter(flightFromESBToIST.or(flightFromISTToESB))
                .map(Flight::getFlightNumber)
                .collect(reducing((fn1, fn2) -> fn1 + SPACE + fn2));
        DemoMetaDataHelper.printMessage("Flights between ESB and IST: " + flights.orElse("No flights"));
    }


    private static List<Flight> generateFlightList() {
        List<Flight> flightList = FlightDataDemoHelper.createFlightsFromTo(ESB_CODE, null, 5);
        flightList.addAll(FlightDataDemoHelper.createFlightsFromTo(ESB_CODE, IST_CODE, 5));
        flightList.addAll(FlightDataDemoHelper.createFlightsFromTo(ESB_CODE, "ADB", 5));
        flightList.addAll(FlightDataDemoHelper.createFlightsFromTo(IST_CODE, ESB_CODE, 5));
        return flightList;
    }


}
