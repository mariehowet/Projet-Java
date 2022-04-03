package Data;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Optional;

public class Flight {
    private int id;
    private int expectedDuration;
    private GregorianCalendar departureDate;
    private int departureHour;
    private Optional<Integer> realArrivalDeparture;
    private double price;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private HashSet<Stopover> stopovers;
    private HashSet<Seat> seats;

    public Flight(int id, int expectedDuration, GregorianCalendar departureDate, int departureHour, Optional<Integer> realArrivalDeparture, double price, Airport departureAirport, Airport arrivalAirport, HashSet<Stopover> stopovers, HashSet<Seat> seats) {
        this.id = id;
        this.expectedDuration = expectedDuration;
        this.departureDate = departureDate;
        this.departureHour = departureHour;
        this.realArrivalDeparture = realArrivalDeparture;
        this.price = price;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.stopovers = new HashSet<Stopover>();
        this.seats = new HashSet<Seat>();
    }
}
