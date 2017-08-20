package lambdasandmethodrefs.composition;


import net.entrofi.studies.java8.demohelpers.DemoMetaDataHelper;
import net.entrofi.studies.java8.demohelpers.DemoRunner;
import net.entrofi.studies.java8.demohelpers.models.Flight;
import net.entrofi.studies.java8.demohelpers.sampledatahelpers.FlightDataDemoHelper;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PredicateComposition implements DemoRunner {

    private static final String ORIGINAL_LIST_MSG = "Original list";
    private static final String ESB_CODE = "ESB";
    private static final String IST_CODE = "IST";

    static void flightsFromToESBAfter3Days() {
        DemoMetaDataHelper.addTitle("Composite predicate stating flights from/to ESB after 3 days from now");
        FlightDataDemoHelper demoHelper = new FlightDataDemoHelper();
        List<Flight> flightList = demoHelper.initFlights(15, true);
        DemoMetaDataHelper.printList(ORIGINAL_LIST_MSG, flightList);

        Predicate<Flight> fromESBToESB = (Flight f) ->
                    f.getOrigin().equals(ESB_CODE) || f.getDestination().equals(ESB_CODE);

        Predicate<Flight> fromESBAndAfter3Days = fromESBToESB.and(f ->
                    f.getDate().isAfter(Instant.now().plus(3, ChronoUnit.DAYS)));

        List<Flight> filteredFlights = new ArrayList<>();
        flightList.stream().filter(fromESBAndAfter3Days).forEach(f -> filteredFlights.add(f));
        DemoMetaDataHelper.printList("\nFiltered  list", filteredFlights);
    }

    static void flightsFromToESBOrFromToISTAfter3Days() {
        DemoMetaDataHelper.addTitle("Composite predicate stating flights from/to ESB or from/to IST after 3 days "
                    + "from now");
        FlightDataDemoHelper demoHelper = new FlightDataDemoHelper();
        List<Flight> flightList = demoHelper.initFlights(15, true);
        DemoMetaDataHelper.printList(ORIGINAL_LIST_MSG, flightList);

        Predicate<Flight> fromToESB = (Flight f) ->
                    f.getOrigin().equals(ESB_CODE) || f.getDestination().equals(ESB_CODE);

        Predicate<Flight> fromToESBOrfromToIST = fromToESB.or((Flight f) -> f.getOrigin().equals(IST_CODE)
                    || f.getDestination().equals(IST_CODE));
        Predicate<Flight> fromESBAndAfter3Months = fromToESBOrfromToIST.and(f -> f.getDate().isAfter(
                    Instant.now().plus(3, ChronoUnit.DAYS)));
        List<Flight> filteredFlights = new ArrayList<>();
        flightList.stream().filter(fromESBAndAfter3Months).forEach(f -> filteredFlights.add(f));
        DemoMetaDataHelper.printList("\nFiltered list", filteredFlights);
    }

    @Override
    public void run() {
        flightsFromToESBAfter3Days();
        flightsFromToESBOrFromToISTAfter3Days();
    }
}
