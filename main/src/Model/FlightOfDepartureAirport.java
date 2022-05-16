package Model;

import java.util.GregorianCalendar;

public class FlightOfDepartureAirport {
    private int flightId;
    private String arrivalAirportName;
    private GregorianCalendar departureDate;
    private GregorianCalendar arrivalDate;
    private Integer remainingSeats;


    public FlightOfDepartureAirport(int flightId, String arrivalAirportName, GregorianCalendar departureDate, GregorianCalendar arrivalDate, Integer remainingSeats) {
        this.flightId = flightId;
        this.arrivalAirportName = arrivalAirportName;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.remainingSeats = remainingSeats;
    }

    public int getFlightId() {
        return flightId;
    }
    public String getArrivalAirportName() {
        return arrivalAirportName;
    }
    public GregorianCalendar getDepartureDate() {
        return departureDate;
    }
    public GregorianCalendar getArrivalDate() {
        return arrivalDate;
    }
    public Integer getRemainingSeats() {
        return remainingSeats;
    }

}
