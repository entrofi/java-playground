package net.entrofi.studies.java8.demohelpers.sampledatahelpers;

import net.entrofi.studies.java8.demohelpers.models.Flight;
import net.entrofi.studies.java8.demohelpers.models.FlightStatus;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Hasan COMAK
 */
public final class FlightDataDemoHelper {


    private FlightDataDemoHelper() {
    }

    /**
     * Creates a single flight instance using given origin and destination parameters. Other parameters of the flight
     * instance are chosen randomly. Same is true if either origin or destination or both are null.
     * @param origin origin airport code: if null it is chosen randomly
     * @param destination destination airport code: if null it is chosen randomly.
     * @return flight instance
     */
    public static Flight createSingleFlightFromTo(String origin, String destination) {

        Instant date = Instant.now().plus(ThreadLocalRandom.current().nextInt(0, 366), ChronoUnit.DAYS);
        FlightStatus flightStatus =
                    FlightStatus.values()[ThreadLocalRandom.current().nextInt(0, FlightStatus.values().length)];

        Flight flight = createSingleFlight(origin, destination, date, flightStatus);
        return flight;
    }

    /**
     * Creates a single flight instance using given parameters. If any one of the parameters is provided as null
     * value, then it is chosen randomly.
     * @param origin origin airport code: if null it is chosen randomly
     * @param destination destination airport code: if null it is chosen randomly
     * @param date planned departure date: if null it is chosen randomly
     * @param status flight status: if null it is chosen randomly
     * @return
     */
    public static Flight createSingleFlight(String origin, String destination,
                                            Instant date, FlightStatus status) {

        Instant scheduledArrivalTime = date.plus(
                    ThreadLocalRandom.current().nextInt(30, 60 * 24 * 15), ChronoUnit.MINUTES);
        return createSingleFlight(origin, destination, date, scheduledArrivalTime, status);
    }

    /**
     * Creates a single flight instance using given parameters. If any one of the parameters is provided as null
     * value, then it is chosen randomly.
     * @param origin origin airport code: if null it is chosen randomly
     * @param destination destination airport code: if null it is chosen randomly
     * @param date planned departure date: if null it is chosen randomly
     * @param scheduledArrTime scheduled arrival time for this flight. If null value is provided then it is chosen
     *                         randomly.
     * @param status flight status: if null it is chosen randomly
     * @return
     */
    public static Flight createSingleFlight(String origin, String destination,
                                            Instant date, Instant scheduledArrTime,
                                            FlightStatus status) {
        String flightNumber = generateRandomFlightNumber();
        AirportTuple airportTuple = AirportTuple.buildAirportTuple(origin, destination);
        scheduledArrTime = scheduledArrTime == null ? date.
                    plus(ThreadLocalRandom.current().nextInt(30, 60 * 24 * 15), ChronoUnit.MINUTES)
                    : scheduledArrTime;
        status = status == null ? chooseRandomStatus(date) : status;
        Flight flight = new Flight(flightNumber, airportTuple.getOrigin(), airportTuple.getDestination(), date,
                    scheduledArrTime, status);
        return flight;
    }

    /**
     * Creates flight instances using given parameters.
     * @param origin if null value provided than actual value is chosen randomly
     * @param count how many flights?
     * @return
     */
    public static List<Flight> createFlightsFrom(String origin, int count) {
        return createFlightsFromTo(origin, null, count);
    }

    /**
     * Creates flight instances using given parameters.
     * @param destination if null value provided than actual value is chosen randomly
     * @param count how many flights?
     * @return
     */
    public static List<Flight> createFlightsTo(String destination, int count) {
        return createFlightsFromTo(null, destination, count);
    }

    /**
     * Creates flight instances using given parameters.
     * @param origin if null value provided than actual value is chosen randomly
     * @param destination if null value provided than actual value is chosen randomly
     * @param count how many flights?
     * @return
     */
    public static List<Flight> createFlightsFromTo(String origin, String destination, int count) {
        List<Flight> flightList = Stream.generate(() -> createSingleFlightFromTo(origin, destination))
                    .limit(count)
                    .collect(Collectors.toList());
        return flightList;
    }

    /**
     * Creates flight instances using given parameters.
     * @param origin if null value provided than actual value is chosen randomly
     * @param destination if null value provided than actual value is chosen randomly
     * @param date planned departure date: if null it is chosen randomly
     * @param scheduledArrTime scheduled arrival time for this flight. If null value is provided then it is chosen
     *                         randomly.
     * @param status flight status: if null it is chosen randomly
     * @param count how many?
     * @return
     */
    public static List<Flight> createFlightsFromTo(String origin, String destination,
                                                   Instant date, Instant scheduledArrTime, FlightStatus status,
                                                   int count) {
        List<Flight> flightList = Stream.generate(() -> createSingleFlight(origin, destination, date,
                    scheduledArrTime, status))
                    .limit(count)
                    .collect(Collectors.toList());
        return flightList;
    }

    /**
     * Generates a flight plan for airport code ESB using the provided date. For each <code>tripleCount</code> three
     * flights are created; one from ESB to a random airport, one from a random airport to ESB and one from ESB to IST.
     * Flight statuses are chosen regarding the Instant instance provided. So for instance, if  you provide an
     * Instant instance two hours before now your flight might be landed, not-landed, delayed, or cancelled.
     * {{@link #statusPredicateByDate(Instant)}}
     *
     * @param date        flight origin date (departure date)
     * @param tripleCount how many triplets do you need?
     * @return List of flights from or to ESB planned at the given {@link Instant} <code>date</code>.
     */
    public static List<Flight> generateESBFlightPlanFor(final Instant date, final int tripleCount) {
        List<AirportTuple> airportTuples = new ArrayList<>();
        for (int i = 1; i <= tripleCount; i++) {
            final String esbCode = "ESB";
            airportTuples.add(AirportTuple.buildAirportTuple(esbCode, null));
            airportTuples.add(AirportTuple.buildAirportTuple(null, esbCode));
            airportTuples.add(AirportTuple.buildAirportTuple(esbCode, "IST"));
        }
        return airportTuples.stream()
                    .map(a -> createSingleFlight(a.getOrigin(), a.getDestination(),
                                date, chooseRandomStatus(date)))
                    .collect(Collectors.toList());
    }


    private static FlightStatus chooseRandomStatus(Instant date) {
        List<FlightStatus> statusList = getStatusList(date);
        return statusList.get(ThreadLocalRandom.current().nextInt(statusList.size()));
    }

    private static List<FlightStatus> getStatusList(Instant date) {
        return Arrays.stream(FlightStatus.values())
                    .filter(statusPredicateByDate(date)).collect(Collectors.toList());
    }

    /**
     * Creates a prediate for flight statuses using the date provided. The predicate returned will give only
     * logically valid set of statuses for the given time.
     * @param date what time will the flight depart?
     * @return
     */
    public static Predicate<FlightStatus> statusPredicateByDate(Instant date) {
        Predicate<FlightStatus> byDateStatusPredicate = f -> {
            if (date.isAfter(Instant.now().plus(20, ChronoUnit.HOURS))) {
                return betweenNowAnd20HoursAfter(f);
            } else if (date.isBefore(Instant.now()) && date.isAfter(Instant.now().minus(20, ChronoUnit.HOURS))) {
                return between20HoursBeforeAndNow(f);
            } else if (date.isAfter(Instant.now()) && date.isBefore(Instant.now().plus(2, ChronoUnit.HOURS))) {
                return betweenNowAndTwoHoursAfter(f);
            } else {
                return f.equals(f);
            }
        };
        return byDateStatusPredicate;
    }

    private static boolean betweenNowAndTwoHoursAfter(FlightStatus f) {
        boolean boordingOrCheckin = FlightStatus.BOARDING.equals(f)
                    || FlightStatus.CHECKIN.equals(f);
        boolean delayedOrLandedOrNotLanded = FlightStatus.DELAYED.equals(f)
                    || FlightStatus.LANDED.equals(f)
                    || FlightStatus.NOT_LANDED.equals(f);
        return boordingOrCheckin || delayedOrLandedOrNotLanded;
    }

    private static boolean between20HoursBeforeAndNow(FlightStatus f) {
        return FlightStatus.CANCELED.equals(f)
                    || FlightStatus.LANDED.equals(f)
                    || FlightStatus.DELAYED.equals(f)
                    || FlightStatus.NOT_LANDED.equals(f);
    }

    private static boolean betweenNowAnd20HoursAfter(FlightStatus f) {
        return f.equals(FlightStatus.CANCELED)
                    || f.equals(FlightStatus.DELAYED)
                    || f.equals(FlightStatus.ONSCHEDULE);
    }

    private static String generateRandomFlightNumber() {
        return "TK" + ThreadLocalRandom.current().nextInt(100, 999);
    }


}
