package Model;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Optional;

public class Flight {
    private int id;
    private GregorianCalendar departureDate;
    private String departureHour;
    private GregorianCalendar expectedArrivalDate;
    private String expectedArrivalHour;
    private double price;
    private int airplaneId;
    private int departureAirportId;
    private int arrivalAirportId;


    public Flight(GregorianCalendar departureDate, String departureHour, GregorianCalendar expectedArrivalDate, String expectedArrivalHour, double price, int airplaneId, int departureAirportId, int arrivalAirportId) {
        this.departureDate = departureDate;
        this.departureHour = departureHour;
        this.expectedArrivalDate = expectedArrivalDate;
        this.expectedArrivalHour = expectedArrivalHour;
        this.price = price;
        this.airplaneId = airplaneId;
        this.departureAirportId = departureAirportId;
        this.arrivalAirportId = arrivalAirportId;
    }

}
