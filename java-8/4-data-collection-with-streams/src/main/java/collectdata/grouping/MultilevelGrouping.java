package collectdata.grouping;

import net.entrofi.studies.java8.demohelpers.DemoMetaDataHelper;
import net.entrofi.studies.java8.demohelpers.DemoRunner;
import net.entrofi.studies.java8.demohelpers.models.Flight;
import net.entrofi.studies.java8.demohelpers.models.FlightStatus;
import net.entrofi.studies.java8.demohelpers.sampledatahelpers.FlightDataDemoHelper;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.maxBy;

/**
 * Basic demos for multilevel grouping in streams.
 *
 * @author Hasan COMAK
 */
public class MultilevelGrouping implements DemoRunner {

    public static final String TITLE_GORUPING_BY_DEST_AND_STATUS = "This demo shows two level grouping. Given "
                + "flight plan of TK airlines \n"
                + "to/from ESB. This grouping example will group flights initially by their destination, then by \n"
                + "their statuses: \n"
                + "  flightList.stream().collect(\n"
                + "                    groupingBy(Flight::getDestination,\n"
                + "                                groupingBy(Flight::getFlightStatus)));";
    public static final String TITLE_COLLECT_DATA_IN_SUBS = "In the previous example, you saw that it’s possible "
                + "to pass a second groupingBy collector\n"
                + " to the outer one to achieve a multilevel grouping. But more generally,\n "
                + "the second collector passed to the first groupingBy can be any type of collector, \n"
                + "not just another groupingBy: \n"
                + "  Map<String, Long> numOfFlightsByDest = flightList.stream().collect(groupingBy"
                + "(Flight::getDestination, counting()))";
    public static final String TITLE_DATA_IN_SUBS_MAXBY = "Map<String, Flight> flightMap = flightList.stream()\n"
                + "            .collect(\n"
                + "                        groupingBy(Flight::getDestination,\n"
                + "                            collectingAndThen(\n"
                + "                                maxBy(Comparator.comparing(Flight::getDuration)),\n"
                + "                                Optional::get\n"
                + "                            )\n"
                + "                        )\n"
                + "            );";
    public static final String DEST = "Destination: ";

    private final List<Flight> flightList = generateESBFlightPlan();

    @Override
    public void run() {
        DemoMetaDataHelper.printList("Flight plan for ESB: ", flightList);
        demoFlightsGroupingByDestAndStatus();
        collectDataInSubGroups();
        collecDataInSubsMaxingBy();

    }


    private void demoFlightsGroupingByDestAndStatus() {
        DemoMetaDataHelper.addTitle(TITLE_GORUPING_BY_DEST_AND_STATUS);
        Map<String, Map<FlightStatus, List<Flight>>> multiLevelMap = flightList.stream().collect(
                    groupingBy(Flight::getDestination,
                                groupingBy(Flight::getFlightStatus)));

        for (String dest : multiLevelMap.keySet()) {
            DemoMetaDataHelper.printMessage("DESTINATION: " + dest, ">");
            for (FlightStatus status : multiLevelMap.get(dest).keySet()) {
                DemoMetaDataHelper.printList("Status: " + status + "\n ",
                            multiLevelMap.get(dest).get(status));
            }
            DemoMetaDataHelper.printMessage("END OF DEST: " + dest, "<");
        }

    }

    private void collectDataInSubGroups() {
        DemoMetaDataHelper.addTitle(TITLE_COLLECT_DATA_IN_SUBS);

        Map<String, Long> numOfFlightsByDest = flightList.stream()
                    .collect(groupingBy(Flight::getDestination, counting()));

        for (String key : numOfFlightsByDest.keySet()) {
            DemoMetaDataHelper.printMessage(DEST + key + ", count: " + numOfFlightsByDest.get(key) + "\n");
        }
    }


    private void collecDataInSubsMaxingBy() {
        DemoMetaDataHelper.addTitle(TITLE_DATA_IN_SUBS_MAXBY);


        Map<String, Flight> flightMap = flightList.stream()
                    .collect(
                                groupingBy(Flight::getDestination,
                                            collectingAndThen(
                                                        maxBy(Comparator.comparing(Flight::getDuration)),
                                                        Optional::get
                                            )
                                )
                    );

        for (String key : flightMap.keySet()) {
            DemoMetaDataHelper.printMessage(DEST + key + ", max duration flight: "
                        + flightMap.get(key) + " duration: "
                        + flightMap.get(key).getDuration().toMinutes() + " mins\n");
        }
    }

    private static List<Flight> generateESBFlightPlan() {
        List<Flight> flightList = FlightDataDemoHelper.generateESBFlightPlanFor(Instant.now(), 1);
        flightList.addAll(FlightDataDemoHelper.generateESBFlightPlanFor(Instant.now()
                    .minus(2, ChronoUnit.HOURS), 1));
        flightList.addAll(FlightDataDemoHelper.generateESBFlightPlanFor(Instant.now()
                    .plus(2, ChronoUnit.HOURS), 1));
        return flightList;
    }


}
