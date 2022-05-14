package Model;

import java.util.GregorianCalendar;

public class Flight {
    private int flightId;
    private String departureAirportName;
    private String arrivalAirportName;
    private GregorianCalendar departureDate;
    private GregorianCalendar arrivalDate;
    private String departureHour;
    private String arrivalHour;
    private double price;


    public Flight(int flightId, String departureAirportName, String arrivalAirportName, GregorianCalendar departureDate, GregorianCalendar arrivalDate, String departureHour, String arrivalHour, Double price) {
        this.flightId = flightId;
        this.departureAirportName = departureAirportName;
        this.arrivalAirportName = arrivalAirportName;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureHour = departureHour;
        this.arrivalHour = arrivalHour;
        this.price = price;
    }

    public double getPrice() {return price;}

    public int getFlightId() {
        return flightId;
    }

    public String getDepartureAirportName() {
        return departureAirportName;
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

    public String getDepartureHour() {
        return departureHour;
    }

    public String getArrivalHour() {
        return arrivalHour;
    }
}
