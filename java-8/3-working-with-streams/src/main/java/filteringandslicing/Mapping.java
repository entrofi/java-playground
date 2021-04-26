/*
 * Licensed under GPL.
 */
package filteringandslicing;


import net.entrofi.studies.java8.demohelpers.DemoMetaDataHelper;
import net.entrofi.studies.java8.demohelpers.DemoRunner;
import net.entrofi.studies.java8.demohelpers.models.Flight;
import net.entrofi.studies.java8.demohelpers.models.FlightStatus;
import net.entrofi.studies.java8.demohelpers.sampledatahelpers.FlightDataDemoHelper;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO add javadoc
 */
public class Mapping implements DemoRunner {

    public static final String ESB_CODE = "ESB";
    public static final String IST_CODE = "IST";
    public static final String SAW_CODE = "SAW";
    private List<Flight> flightList;

    private static List<Flight> buildSampleData() {
        //TODO build this list using FlightDataDemoHelper
        List<Flight> flightList = FlightDataDemoHelper.createFlightsFromTo(ESB_CODE, IST_CODE,
                    Instant.now(), null,
                    FlightStatus.ONSCHEDULE, 5);


        Flight duplicate381 = FlightDataDemoHelper.createSingleFlight(ESB_CODE, IST_CODE,
                    Instant.now().plus(3, ChronoUnit.DAYS),
                    Instant.now().plus(3, ChronoUnit.DAYS).plus(1, ChronoUnit.HOURS),
                    FlightStatus.ONSCHEDULE);
        flightList.add(duplicate381);
        flightList.add(duplicate381);
        flightList.add(duplicate381);
        Flight duplicate385 = FlightDataDemoHelper.createSingleFlight(ESB_CODE, IST_CODE,
                    Instant.now().plus(1, ChronoUnit.DAYS),
                    Instant.now().plus(3, ChronoUnit.DAYS).plus(3, ChronoUnit.HOURS),
                    FlightStatus.ONSCHEDULE);
        flightList.add(duplicate385);
        flightList.add(duplicate385);
        flightList.add(new Flight("TK383", ESB_CODE, IST_CODE,
                    Instant.now().plus(1, ChronoUnit.DAYS),
                    Instant.now().plus(4, ChronoUnit.DAYS).plus(1, ChronoUnit.HOURS),
                    FlightStatus.ONSCHEDULE));
        flightList.add(new Flight("TK382", ESB_CODE, IST_CODE,
                    Instant.now().plus(1, ChronoUnit.DAYS),
                    Instant.now().plus(4, ChronoUnit.DAYS).plus(2, ChronoUnit.HOURS),
                    FlightStatus.ONSCHEDULE));

        flightList.addAll(FlightDataDemoHelper.createFlightsFromTo(SAW_CODE, IST_CODE,
                    Instant.now().plus(1, ChronoUnit.DAYS),
                    Instant.now().plus(1, ChronoUnit.DAYS).plus(1, ChronoUnit.HOURS),
                    FlightStatus.ONSCHEDULE, 1));
        flightList.addAll(FlightDataDemoHelper.createFlightsFromTo(SAW_CODE, IST_CODE,
                    Instant.now().plus(1, ChronoUnit.DAYS),
                    Instant.now().plus(1, ChronoUnit.DAYS).plus(2, ChronoUnit.HOURS),
                    FlightStatus.ONSCHEDULE, 2));
        flightList.addAll(FlightDataDemoHelper.createFlightsFromTo(SAW_CODE, IST_CODE,
                    Instant.now().plus(1, ChronoUnit.DAYS),
                    Instant.now().plus(1, ChronoUnit.DAYS).plus(1, ChronoUnit.HOURS),
                    FlightStatus.DELAYED, 3));
        return flightList;
    }

    @Override
    public void run() {
        flightList = buildSampleData();
        basicStreamMapping();

    }

    void basicStreamMapping() {

        DemoMetaDataHelper.addTitle("Basic mapping: Here we are extracting flight durations and flight numbers "
                    + "\nfrom a list of flights and printing it.");

        List<String> durationsWithFlightNumbers = flightList.stream().map(flight -> {
            String flightAndDuration = flight.getFlightNumber() + " ";
            Long duration = ChronoUnit.MILLIS.between(flight.getScheduledArrivalTime(), flight.getDate());
            return flightAndDuration + Duration.of(duration, ChronoUnit.MILLIS);
        }).collect(Collectors.toList());

        DemoMetaDataHelper.printList("Mapping: map(flight -> { flightNumber + duration}", durationsWithFlightNumbers);
    }
}
