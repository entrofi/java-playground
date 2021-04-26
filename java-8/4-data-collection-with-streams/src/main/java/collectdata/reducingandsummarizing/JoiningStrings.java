package collectdata.reducingandsummarizing;


import net.entrofi.studies.java8.demohelpers.DemoMetaDataHelper;
import net.entrofi.studies.java8.demohelpers.DemoRunner;
import net.entrofi.studies.java8.demohelpers.models.Flight;
import net.entrofi.studies.java8.demohelpers.sampledatahelpers.FlightDataDemoHelper;

import java.util.List;
import java.util.stream.Collectors;

public class JoiningStrings implements DemoRunner {

    private static final String ESB_CODE = "ESB";
    private static final String ADB_CODE = "ADB";
    private static final String IST_CODE = "IST";
    private static final String DEMO_MSG_DECO = "#";
    private final List<Flight> flightList = generateFlights();

    @Override
    public void run() {
        DemoMetaDataHelper.addTitle("This demo demonstrates alternate uses of joining method and its overloads.\n"
                    + "The joining method internally calls toString() method of the objects in the stream and uses \n"
                    + "StringBuilder to generate result:");
        DemoMetaDataHelper.printList("Flight plan for ESB, there is 5 flights to Izmir ADB and \n"
                    + "5 flights to Istanbul IST", flightList);
        joiningDemo();
        joiningDelimitedDemo();
    }


    private void joiningDemo() {
        DemoMetaDataHelper.printMessage("Filter flights from ESB to Izmir ADB", DEMO_MSG_DECO);
        String flightsFromESBToADB = flightList.stream()
                    .filter(f -> ESB_CODE.equals(f.getOrigin()) && ADB_CODE.equals(f.getDestination()))
                    .map(f -> f.getFlightNumber())
                    .collect(Collectors.joining());
        DemoMetaDataHelper.printMessage(" -> " + flightsFromESBToADB);

    }

    private void joiningDelimitedDemo() {
        final String message = "Filter flights from ESB to Istanbul IST and obtain the ',' delimited result";
        DemoMetaDataHelper.printMessage(message, DEMO_MSG_DECO);
        String flightsFromESBToADB = flightList.stream()
                    .filter(f -> ESB_CODE.equals(f.getOrigin()) && IST_CODE.equals(f.getDestination()))
                    .map(f -> f.getFlightNumber())
                    .collect(Collectors.joining(","));
        DemoMetaDataHelper.printMessage("-> " + flightsFromESBToADB);
    }

    private static List<Flight> generateFlights() {
        List<Flight> flightList = FlightDataDemoHelper.createFlightsFromTo(ESB_CODE, null, 5);
        flightList.addAll(FlightDataDemoHelper.createFlightsFromTo(ESB_CODE, IST_CODE, 5));
        flightList.addAll(FlightDataDemoHelper.createFlightsFromTo(ESB_CODE, ADB_CODE, 5));
        return flightList;
    }
}
