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

    @Override
    public void run() {
        flightsFromToESBAfter3Days();
        flightsFromToESBOrFromToISTAfter3Days();
    }


    static void flightsFromToESBAfter3Days() {
        DemoMetaDataHelper.addTitle("Composite predicate stating flights from/to ESB after 3 days from now");
        FlightDataDemoHelper demoHelper = new FlightDataDemoHelper();
        List<Flight> flightList = demoHelper.initFlights(15, true);
        DemoMetaDataHelper.printList("Original list", flightList);

        Predicate<Flight> fromESBToESB = (Flight f) -> f.getOrigin().equals("ESB") || f.getDestination().equals("ESB");
        Predicate<Flight> fromESBAndAfter3Days = fromESBToESB.and(f -> (f.getDate().isAfter(Instant.now().plus(3,
                ChronoUnit.DAYS))));
        List<Flight> filteredFlights = new ArrayList<>();
        flightList.stream().filter(fromESBAndAfter3Days).forEach(f -> filteredFlights.add(f));
        DemoMetaDataHelper.printList("\nFiltered list", filteredFlights);
    }

    static void flightsFromToESBOrFromToISTAfter3Days() {
        DemoMetaDataHelper.addTitle("Composite predicate stating flights from/to ESB or from/to IST after 3 days " +
                "from now");
        FlightDataDemoHelper demoHelper = new FlightDataDemoHelper();
        List<Flight> flightList = demoHelper.initFlights(15, true);
        DemoMetaDataHelper.printList("Original list", flightList);

        Predicate<Flight> fromToESB = (Flight f) -> f.getOrigin().equals("ESB") || f.getDestination().equals("ESB");
        Predicate<Flight> fromToESBOrfromToIST = fromToESB.or((Flight f) -> (f.getOrigin().equals("IST")
                || f.getDestination().equals("IST")));
        Predicate<Flight> fromESBAndAfter3Months = fromToESBOrfromToIST.and(f -> (f.getDate().isAfter(Instant.now().plus(3,
                ChronoUnit.DAYS))));
        List<Flight> filteredFlights = new ArrayList<>();
        flightList.stream().filter(fromESBAndAfter3Months).forEach(f -> filteredFlights.add(f));
        DemoMetaDataHelper.printList("\nFiltered list", filteredFlights);
    }
}
