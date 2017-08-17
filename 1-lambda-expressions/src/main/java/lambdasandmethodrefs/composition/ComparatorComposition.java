package lambdasandmethodrefs.composition;



import net.entrofi.studies.java8.demohelpers.DemoMetaDataHelper;
import net.entrofi.studies.java8.demohelpers.DemoRunner;
import net.entrofi.studies.java8.demohelpers.models.Flight;
import net.entrofi.studies.java8.demohelpers.sampledatahelpers.FlightDataDemoHelper;

import java.util.Comparator;
import java.util.List;

public class ComparatorComposition implements DemoRunner {

    public void run() {
        reversedOrder();
        chainedComparator();
    }

    static void reversedOrder() {
        FlightDataDemoHelper flightDataDemoHelper = new FlightDataDemoHelper();
        List<Flight> flightList = flightDataDemoHelper.initFlights(5);
        DemoMetaDataHelper.addTitle("Reversed comparator exapmle: list.sort(Comparator.comparing" +
                "(Flight::getFlightNumber).reversed())");
        DemoMetaDataHelper.printList("Unsorted List", flightList);

        Comparator<Flight> comparator = Comparator.comparing(Flight::getFlightNumber);
        flightList.sort(comparator);
        DemoMetaDataHelper.printList("Regular order sorted by flight number", flightList);

        flightList.sort(comparator.reversed());
        DemoMetaDataHelper.printList("Reversed order sort by flight number", flightList);
    }

    static void chainedComparator() {
        FlightDataDemoHelper demoHelper = new FlightDataDemoHelper();
        List<Flight> flightList = demoHelper.initFlights(5);
        DemoMetaDataHelper.addTitle("Chained comparator example:\n" +
                "\t list.sort(compositeComaparator = Comparator.comparing(Flight::getFlightNumber).reversed()\n" +
                "\t                .thenComparing(Flight::getDate);)");
        DemoMetaDataHelper.printList("Unsorted list", flightList);

        Comparator<Flight> compositeComaparator = Comparator.comparing(Flight::getFlightNumber).reversed()
                .thenComparing(Flight::getDate);
        flightList.sort(compositeComaparator);
        DemoMetaDataHelper.printList("Composite sort", flightList);
    }
}
