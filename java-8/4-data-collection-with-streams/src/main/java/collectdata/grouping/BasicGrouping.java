package collectdata.grouping;


import net.entrofi.studies.java8.demohelpers.DemoMetaDataHelper;
import net.entrofi.studies.java8.demohelpers.DemoRunner;
import net.entrofi.studies.java8.demohelpers.models.Flight;
import net.entrofi.studies.java8.demohelpers.sampledatahelpers.FlightDataDemoHelper;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;

/**
 * Streams grouping introduction.
 *
 * @author Hasan COMAK
 */
public class BasicGrouping implements DemoRunner {

    public static final String TITLE_GRP_BY_DEST = "Given the flight plan for ESB this examples shows how to use "
                + "groupingBy operation in such a list. \nIn the example we will group  flights by destination using "
                + "the expression: \n"
                + " Map<String, List<Flight>> flightsByDestination = flightList.stream()\n"
                + "                    .collect(groupingBy(Flight::getDestination));";

    private final List<Flight> flightList = generateESBFlightPlan();

    @Override
    public void run() {
        DemoMetaDataHelper.printList("Departure Flight Plan for ESB:", flightList);
        groupByDestination();
    }


    private void groupByDestination() {
        DemoMetaDataHelper.addTitle(TITLE_GRP_BY_DEST);

        Map<String, List<Flight>> flightsByDestination = flightList.stream()
                    .collect(groupingBy(Flight::getDestination));

        String flightsByDestinationMessage;
        for (String key : flightsByDestination.keySet()) {
            flightsByDestinationMessage = "Destination: " + key + "\n";
            flightsByDestinationMessage += flightsByDestination.get(key)
                        .stream().map(f -> f.getFlightNumber()).collect(joining(", "));
            flightsByDestinationMessage += "\n ";
            DemoMetaDataHelper.printMessage(flightsByDestinationMessage, ".");
        }
    }


    private static List<Flight> generateESBFlightPlan() {
        final String esbCode = "ESB";
        List<Flight> flightList = FlightDataDemoHelper.createFlightsFromTo(esbCode, "IST", 5);
        flightList.addAll(FlightDataDemoHelper.createFlightsFromTo(esbCode, "ADB", 10));
        flightList.addAll(FlightDataDemoHelper.createFlightsFromTo(esbCode, "SAW", 5));
        flightList.addAll(FlightDataDemoHelper.createFlightsFromTo(esbCode, "JFK", 6));

        return flightList;
    }

}
