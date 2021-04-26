/*
 * Licensed under GPL.
 */
package findingandmatching;


import net.entrofi.studies.java8.demohelpers.DemoMetaDataHelper;
import net.entrofi.studies.java8.demohelpers.DemoRunner;
import net.entrofi.studies.java8.demohelpers.models.Flight;
import net.entrofi.studies.java8.demohelpers.models.FlightStatus;
import net.entrofi.studies.java8.demohelpers.sampledatahelpers.FlightDataDemoHelper;

import java.time.Instant;
import java.util.List;

/**
 * TODO add javadoc
 */
public class FindingAndMatching implements DemoRunner {

    public static final String ESB_CODE = "ESB";
    public static final String SAW_CODE = "SAW";
    public static final String DO_FLIGHTS_HAVE_FLIGHTS_FROM_SAW_MSG = "Does flight list have flights from SAW: ";

    @Override
    public void run() {
        anyMatch();
        noneMatch();
        findAny();
        optionalIfPresent();
        optionalIsPresent();
        optionalOrElse();
    }


    private void anyMatch() {
        DemoMetaDataHelper.addTitle("Does the flight list have any flights from ESB"
                    + "\n lightList.stream().anyMatch(f -> f.getOrigin().equals(\"ESB\")");
        List<Flight> flightList = createAndPrint8FlightsFromESBorIST();

        DemoMetaDataHelper.printMessage("Flight list has flights from ESB:"
                    + flightList.stream().anyMatch(f -> f.getOrigin().equals(ESB_CODE)));
    }

    private void noneMatch() {
        DemoMetaDataHelper.addTitle("Does the flight list have no flights from SAW"
                    + "\n + flightList.stream().noneMatch(f -> f.getOrigin().equals(\"SAW\"))");
        List<Flight> flightList = createAndPrint8FlightsFromESBorIST();

        DemoMetaDataHelper.printMessage("Does flight list have flights from SAW"
                    + flightList.stream().noneMatch(f -> f.getOrigin().equals(SAW_CODE)));

    }

    private void findAny() {
        DemoMetaDataHelper.addTitle("Find any flight from the "
                    +
                    "\n flightList.stream().filter(f -> f.getOrigin().equals(\"SAW\")).findAny().get()");
        List<Flight> flightList = createAndPrint8FlightsFromESBorIST();

        DemoMetaDataHelper.printMessage(DO_FLIGHTS_HAVE_FLIGHTS_FROM_SAW_MSG
                    + flightList.stream().filter(f -> f.getOrigin().equals(SAW_CODE)).findAny().isPresent());
    }

    private void optionalIfPresent() {
        DemoMetaDataHelper.addTitle("Find any flight from the  and use Optional.#ifPresent to print name"
                    + "\n flightList.stream().filter(f -> f.getOrigin().equals(\"ESB\"))"
                    + "               .findAny().ifPresent(f -> DemoMetaDataHelper.printMessage\n"
                    + "                (\"Flight number is:\" + f.getFlightNumber()));");
        List<Flight> flightList = createAndPrint8FlightsFromESBorIST();

        flightList.stream().filter(f -> f.getOrigin().equals(SAW_CODE)).findAny()
                    .ifPresent(f -> DemoMetaDataHelper.printMessage("Flight number is:" + f.getFlightNumber()));
    }


    private void optionalIsPresent() {
        DemoMetaDataHelper.addTitle("Optional isPresent() demo "
                    + "\n flightList.stream().filter(f -> f.getOrigin().equals(\"ESB\")).findAny().isPresent()");
        List<Flight> flightList = createAndPrint8FlightsFromESBorIST();

        DemoMetaDataHelper.printMessage(DO_FLIGHTS_HAVE_FLIGHTS_FROM_SAW_MSG
                    + flightList.stream().filter(f -> f.getOrigin().equals(SAW_CODE)).findAny().isPresent());
    }


    private void optionalOrElse() {
        DemoMetaDataHelper.addTitle("Optional orElse() demo "
                    + "\n Flight flight = flightList.stream().filter(f -> f.getOrigin().equals(\"SAW\"))\n"
                    + "                  .findAny()\n"
                    + "                  .orElse(new Flight(\"default\", \"origin\", \"destination\", Instant.now(), "
                    + "Instant.now(), "
                    + "                          FlightStatus.CANCELED));");
        List<Flight> flightList = createAndPrint8FlightsFromESBorIST();

        Flight flight = flightList.stream().filter(f -> f.getOrigin().equals(SAW_CODE))
                    .findAny()
                    .orElse(new Flight("default", "origin", "destination",
                                Instant.now(), Instant.now(), FlightStatus.CANCELED));
        DemoMetaDataHelper.printMessage("Default flight or the find one: " + flight);
    }

    private List<Flight> createAndPrint8FlightsFromESBorIST() {
        List<Flight> flightList = FlightDataDemoHelper.createFlightsFrom(ESB_CODE, 4);
        flightList.addAll(FlightDataDemoHelper.createFlightsFrom("IST", 4));
        DemoMetaDataHelper.printList("Original List", flightList);
        return flightList;
    }


}
