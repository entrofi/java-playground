/*
 * Licensed under GPL.
 */
package filteringandslicing;


import net.entrofi.studies.java8.demohelpers.DemoMetaDataHelper;
import net.entrofi.studies.java8.demohelpers.DemoRunner;
import net.entrofi.studies.java8.demohelpers.models.Flight;
import net.entrofi.studies.java8.demohelpers.models.FlightStatus;
import net.entrofi.studies.java8.demohelpers.sampledatahelpers.FlightDataDemoHelper;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * TODO add javadoc
 */
public class Filtering implements DemoRunner {

    private static final String ESB_CODE = "ESB";
    private static final String IST_CODE = "IST";
    private static final Instant PLUS_ONE_DAY = Instant.now().plus(1, ChronoUnit.DAYS);
    private static final Instant PLUS_3_DAYS = Instant.now().plus(3, ChronoUnit.DAYS);
    private static final String SAW_CODE = "SAW";
    private static final Instant PLUS_4_DAY = Instant.now().plus(4, ChronoUnit.DAYS);
    private static final String FLIGHTS_AFTER_ONE_DAY = "Flights after one day";
    private List<Flight> flightList;

    private static List<Flight> buildSampleData() {
        //TODO build this list using FlightDataDemoHelper
        List<Flight> flightList = FlightDataDemoHelper.generateESBFlightPlanFor(PLUS_ONE_DAY, 1);
        final String tk380 = "TK380";
        flightList.add(new Flight(tk380, ESB_CODE, IST_CODE,
                    PLUS_ONE_DAY,
                    PLUS_ONE_DAY.plus(1, ChronoUnit.HOURS),
                    FlightStatus.ONSCHEDULE));
        flightList.add(new Flight(tk380, ESB_CODE, IST_CODE,
                    PLUS_ONE_DAY,
                    Instant.now().plus(2, ChronoUnit.DAYS).plus(1, ChronoUnit.HOURS),
                    FlightStatus.ONSCHEDULE));
        flightList.add(new Flight(tk380, ESB_CODE, IST_CODE,
                    PLUS_ONE_DAY,
                    PLUS_3_DAYS.plus(1, ChronoUnit.HOURS),
                    FlightStatus.ONSCHEDULE));
        Flight duplicate381 = new Flight("TK381", ESB_CODE, IST_CODE,
                    PLUS_3_DAYS,
                    PLUS_3_DAYS.plus(1, ChronoUnit.HOURS),
                    FlightStatus.ONSCHEDULE);
        flightList.add(duplicate381);
        flightList.add(duplicate381);
        flightList.add(duplicate381);
        Flight duplicate385 = new Flight("TK385", ESB_CODE, IST_CODE,
                    PLUS_ONE_DAY,
                    PLUS_3_DAYS.plus(3, ChronoUnit.HOURS),
                    FlightStatus.ONSCHEDULE);
        flightList.add(duplicate385);
        flightList.add(duplicate385);
        flightList.add(new Flight("TK383", ESB_CODE, IST_CODE,
                    PLUS_ONE_DAY,
                    PLUS_4_DAY.plus(1, ChronoUnit.HOURS),
                    FlightStatus.ONSCHEDULE));
        flightList.add(new Flight("TK382", ESB_CODE, IST_CODE,
                    PLUS_ONE_DAY,
                    PLUS_4_DAY.plus(2, ChronoUnit.HOURS),
                    FlightStatus.ONSCHEDULE));

        final String tk390 = "TK390";
        flightList.add(new Flight(tk390, SAW_CODE, IST_CODE,
                    PLUS_ONE_DAY,
                    PLUS_ONE_DAY.plus(1, ChronoUnit.HOURS),
                    FlightStatus.ONSCHEDULE));
        flightList.add(new Flight(tk390, SAW_CODE, IST_CODE,
                    PLUS_ONE_DAY,
                    Instant.now().plus(2, ChronoUnit.DAYS).plus(1, ChronoUnit.HOURS),
                    FlightStatus.ONSCHEDULE));
        flightList.add(new Flight(tk390, SAW_CODE, IST_CODE,
                    PLUS_ONE_DAY,
                    PLUS_3_DAYS.plus(1, ChronoUnit.HOURS),
                    FlightStatus.DELAYED));
        flightList.add(new Flight("TK393", SAW_CODE, IST_CODE,
                    PLUS_ONE_DAY,
                    PLUS_3_DAYS.plus(3, ChronoUnit.HOURS),
                    FlightStatus.DELAYED));
        flightList.add(new Flight(tk390, SAW_CODE, IST_CODE,
                    PLUS_ONE_DAY,
                    PLUS_4_DAY.plus(1, ChronoUnit.HOURS),
                    FlightStatus.DELAYED));
        flightList.add(new Flight(tk390, SAW_CODE, IST_CODE,
                    PLUS_ONE_DAY,
                    PLUS_4_DAY.plus(2, ChronoUnit.HOURS),
                    FlightStatus.DELAYED));
        flightList.addAll(FlightDataDemoHelper.createFlightsFrom(null, 5));

        return flightList;
    }

    @Override
    public void run() {
        buildOriginalAndPrint();
        filterWithPredicate();
        filterWithPredicateUniqueElements();
        filterWithPredicateAndTruncate();
        filterWithPredicateAndSkip();
    }

    private void filterWithPredicate() {
        DemoMetaDataHelper.addTitle("Simple Filtering: Filter flights from ESB using predicate "
                    + "\n\t' (Flight f) -> (f.getOrigin().equals(\"ESB\")) '");


        Predicate<Flight> esbPredicate = (Flight f) -> f.getOrigin().equals(ESB_CODE);
        List<Flight> esbFlights = flightList.stream().filter(esbPredicate).collect(Collectors.toList());
        DemoMetaDataHelper.printList("Flights from ESB", esbFlights);
    }

    private void filterWithPredicateUniqueElements() {
        DemoMetaDataHelper.addTitle("Filter with predicate and select distinct elements: ");

        Predicate<Flight> flightsAfter1DayPredicate = (Flight f) ->
                    f.getDate().isAfter(PLUS_ONE_DAY);

        List<Flight> flightsAfter1Day = flightList.stream()
                    .filter(flightsAfter1DayPredicate)
                    .collect(Collectors.toList());
        DemoMetaDataHelper.printList(FLIGHTS_AFTER_ONE_DAY, flightsAfter1Day);

        List<Flight> distinctFlightsAfter1Day = flightList.stream()
                    .filter(flightsAfter1DayPredicate)
                    .distinct()
                    .collect(Collectors.toList());
        DemoMetaDataHelper.printList("Distinct flights after one day", distinctFlightsAfter1Day);


    }

    private void filterWithPredicateAndTruncate() {
        DemoMetaDataHelper.addTitle("Filter with predicate and truncate to first 3");

        Predicate<Flight> flightsAfter1DayPredicate = (Flight f) -> 
                    f.getDate().isAfter(PLUS_ONE_DAY);

        List<Flight> flightsAfter1Day = flightList.stream()
                    .filter(flightsAfter1DayPredicate)
                    .collect(Collectors.toList());
        DemoMetaDataHelper.printList(FLIGHTS_AFTER_ONE_DAY, flightsAfter1Day);


        List<Flight> flightsAfter1DayLimit3 = flightList.stream()
                    .filter(flightsAfter1DayPredicate)
                    .limit(3)
                    .collect(Collectors.toList());
        DemoMetaDataHelper.printList("Flights after one day limit 3", flightsAfter1DayLimit3);

    }

    private void filterWithPredicateAndSkip() {
        DemoMetaDataHelper.addTitle("Filter with predicate and skip first 3");

        Predicate<Flight> flightsAfter1DayPredicate = (Flight f) ->
                    f.getDate().isAfter(PLUS_ONE_DAY);

        List<Flight> flightsAfter1Day = flightList.stream()
                    .filter(flightsAfter1DayPredicate)
                    .collect(Collectors.toList());
        DemoMetaDataHelper.printList(FLIGHTS_AFTER_ONE_DAY, flightsAfter1Day);


        List<Flight> flightsAfter1DayLimit3 = flightList.stream()
                    .filter(flightsAfter1DayPredicate)
                    .skip(3)
                    .collect(Collectors.toList());
        DemoMetaDataHelper.printList("Flights after one day skip 3", flightsAfter1DayLimit3);
    }

    private List<Flight> buildOriginalAndPrint() {
        flightList = buildSampleData();
        DemoMetaDataHelper.printList("Original List", flightList);
        return flightList;
    }
}
