package net.entrofi.studies.java8.demohelpers.models;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.Duration;
import java.time.Instant;

public class Flight {

    private final String flightNumber;

    private final String origin;

    private final String destination;

    private final Instant date;

    private final Instant scheduledArrivalTime;

    private final FlightStatus flightStatus;

    public Flight(String flightNumber, String origin, String destination,
                  Instant date, Instant scheduledArrivalTime, FlightStatus flightStatus) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.scheduledArrivalTime = scheduledArrivalTime;
        this.flightStatus = flightStatus;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public Instant getDate() {
        return date;
    }

    public Instant getScheduledArrivalTime() {
        return scheduledArrivalTime;
    }

    public FlightStatus getFlightStatus() {
        return flightStatus;
    }

    public Duration getDuration() {
        return  Duration.between(date, scheduledArrivalTime);
    }

    @Override
    public String toString() {
        return flightNumber + "-" + origin + "-" + destination + "-" + date + "-" + flightStatus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Flight flight = (Flight) o;

        return new EqualsBuilder()
                .append(flightNumber, flight.flightNumber)
                .append(origin, flight.origin)
                .append(destination, flight.destination)
                .append(date, flight.date)
                .append(scheduledArrivalTime, flight.scheduledArrivalTime)
                .append(flightStatus, flight.flightStatus)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(flightNumber)
                .append(origin)
                .append(destination)
                .append(date)
                .append(scheduledArrivalTime)
                .append(flightStatus)
                .toHashCode();
    }
}
