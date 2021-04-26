/*
 * Licensed under GPL.
 */
package reducing;


import net.entrofi.studies.java8.demohelpers.DemoMetaDataHelper;
import net.entrofi.studies.java8.demohelpers.DemoRunner;
import net.entrofi.studies.java8.demohelpers.models.Flight;
import net.entrofi.studies.java8.demohelpers.sampledatahelpers.FlightDataDemoHelper;

import java.util.List;

/**
 * TODO add javadoc
 */
public class BasicReducing implements DemoRunner {

    private static final String ESB = "ESB";
    private static final String TITLE = "Basic Reducing demo - Fold to number of flights from ESB in a given flight "
                + "list"
                + "\n int count = flightList.stream()\n"
                + "                              .filter(f -> f.getOrigin().equals(\"ESB\"))\n"
                + "                              .map(esbF -> 1)\n"
                + "                              .reduce(0, (a, b) -> a + b);";

    @Override
    public void run() {
        countFlightsFromESB();
    }


    void countFlightsFromESB() {
        DemoMetaDataHelper.addTitle(TITLE);
        final List<Flight> flightList = FlightDataDemoHelper.createFlightsFrom(ESB, 3);
        flightList.addAll(FlightDataDemoHelper.createFlightsTo(null, 6));
        DemoMetaDataHelper.printList("Original list", flightList);

        int count = flightList.stream()
                    .filter(f -> f.getOrigin().equals(ESB))
                    .map(esbF -> 1)
                    .reduce(0, (a, b) -> a + b);
        DemoMetaDataHelper.printMessage("Total number of flights from ESB: " + count);
    }


}
