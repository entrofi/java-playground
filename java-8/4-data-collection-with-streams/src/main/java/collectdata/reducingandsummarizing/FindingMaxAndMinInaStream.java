package collectdata.reducingandsummarizing;


import net.entrofi.studies.java8.demohelpers.DemoMetaDataHelper;
import net.entrofi.studies.java8.demohelpers.DemoRunner;
import net.entrofi.studies.java8.demohelpers.models.Flight;
import net.entrofi.studies.java8.demohelpers.sampledatahelpers.FlightDataDemoHelper;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.minBy;

public class FindingMaxAndMinInaStream implements DemoRunner {

    public static final String ESB_CODE = "ESB";

    @Override
    public void run() {
        DemoMetaDataHelper.addTitle("Examples of maxBy and minBy methods provided by the Collectors class");
        maxByDemo();
        minByDemo();
    }


    private void maxByDemo() {
        List<Flight> flightList = FlightDataDemoHelper.createFlightsFrom(ESB_CODE, 10);
        DemoMetaDataHelper.printList("Create a comparator to choose from the list; say longest flight from ESB"
                + "\nComparator<Flight> flightDurationComparator = Comparator.comparingLong(\n"
                + "                    f ->  { return Duration.between(f.getDate(), f.getScheduledArrivalTime())"
                + ".toNanos();});\n        Optional<Flight> longestFlight = "
                + "flightList.stream().collect(maxBy(flightDurationComparator));", flightList);

        Comparator<Flight> flightDurationComparator = Comparator
                .comparingLong(f -> Duration.between(f.getDate(), f.getScheduledArrivalTime()).toNanos());

        Optional<Flight> longestFlight = flightList.stream().collect(maxBy(flightDurationComparator));
        DemoMetaDataHelper.printMessage("\nLongest flight from ESB: " + longestFlight);

    }


    private void minByDemo() {
        List<Flight> flightList = FlightDataDemoHelper.createFlightsFrom(ESB_CODE, 10);
        DemoMetaDataHelper.printList("Create a comparator to choose from list: say shortest flight from ESB"
                + "\ncollect(minBy(flightDurationComparator))", flightList);
        Comparator<Flight> flightDurationComparator = Comparator
                .comparingLong(f -> Duration.between(f.getDate(), f.getScheduledArrivalTime()).toNanos());
        Optional<Flight> longestFlight = flightList.stream().collect(minBy(flightDurationComparator));
        DemoMetaDataHelper.printMessage("\nShortest flight from ESB: " + longestFlight);
    }


}
