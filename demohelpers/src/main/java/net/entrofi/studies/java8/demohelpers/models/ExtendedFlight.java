package net.entrofi.studies.java8.demohelpers.models;

import java.time.Instant;

/**
 * @author Hasan COMAK
 */
public class ExtendedFlight extends Flight {

    private final int maxCapacity;

    private double fare;

    private int reservedCapacity;


    public ExtendedFlight(String flightNumber, String origin, String destination,
                          Instant date, Instant scheduledArrivalTime,
                          FlightStatus flightStatus, int maxCapacity) {
        super(flightNumber, origin, destination, date, scheduledArrivalTime, flightStatus);
        this.maxCapacity = maxCapacity;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public int getReservedCapacity() {
        return reservedCapacity;
    }

    public void setReservedCapacity(int reservedCapacity) {
        this.reservedCapacity = reservedCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}
