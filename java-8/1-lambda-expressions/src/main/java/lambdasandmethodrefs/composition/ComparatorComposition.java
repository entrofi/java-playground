package lambdasandmethodrefs.composition;


import net.entrofi.studies.java8.demohelpers.DemoMetaDataHelper;
import net.entrofi.studies.java8.demohelpers.DemoRunner;
import net.entrofi.studies.java8.demohelpers.models.Flight;
import net.entrofi.studies.java8.demohelpers.sampledatahelpers.FlightDataDemoHelper;

import java.util.Comparator;
import java.util.List;

public class ComparatorComposition implements DemoRunner {

    private static void reversedOrder() {
        final List<Flight> flightList = FlightDataDemoHelper.createFlightsFromTo(null, null, 5);
        DemoMetaDataHelper.addTitle("Reversed comparator exapmle: list.sort(Comparator.comparing"
                    + "(Flight::getFlightNumber).reversed())");
        DemoMetaDataHelper.printList("Unsorted List", flightList);

        final Comparator<Flight> comparator = Comparator.comparing(Flight::getFlightNumber);
        flightList.sort(comparator);
        DemoMetaDataHelper.printList("Regular order sorted by flight number", flightList);

        flightList.sort(comparator.reversed());
        DemoMetaDataHelper.printList("Reversed order sort by flight number", flightList);
    }

    private static void chainedComparator() {
        final List<Flight> flightList = FlightDataDemoHelper.createFlightsFromTo(null, null, 5);
        DemoMetaDataHelper.addTitle("Chained comparator example:\n"
                    + "\t list.sort(compositeComaparator = Comparator.comparing(Flight::getFlightNumber).reversed()\n"
                    + "\t                .thenComparing(Flight::getDate);)");
        DemoMetaDataHelper.printList("Unsorted list", flightList);

        final Comparator<Flight> compositeComaparator = Comparator.comparing(Flight::getFlightNumber).reversed()
                    .thenComparing(Flight::getDate);
        flightList.sort(compositeComaparator);
        DemoMetaDataHelper.printList("Composite sort", flightList);
    }

    /**
     * Runs finding and matching samples
     */
    public void run() {
        reversedOrder();
        chainedComparator();
    }
}
