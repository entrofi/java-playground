package model;


import java.time.Instant;

public class Flight {

    private String flightNumber;

    private String origin;

    private String destination;

    private Instant date;


    public Flight() {

    }

    public Flight(String flightNumber, String origin, String destination, Instant date) {
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
        return flightNumber + "-" + origin + "-" + destination + "-" + date;
    }
}
