package model;


import java.time.Instant;

public class Flight {

    private String flightNumber;

    private String origin;

    private String destination;

    private Instant date;


    public Flight() {

    }

    public Flight(final String flightNumber, final String origin, final String destination, final Instant date) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    @Override
    public String toString() {
        final String delimiter = "-";
        return flightNumber + delimiter + origin + delimiter + destination + delimiter + date;
    }
}
