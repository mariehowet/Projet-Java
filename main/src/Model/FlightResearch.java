package Model;

import java.util.GregorianCalendar;
import Exception.PriceException;

public class FlightResearch {
    private int flightId;
    private String departureAirportName;
    private String arrivalAirportName;
    private GregorianCalendar departureDate;
    private GregorianCalendar arrivalDate;
    private String departureHour;
    private String arrivalHour;
    private double price;

    public FlightResearch(int flightId, String departureAirportName, String arrivalAirportName, GregorianCalendar departureDate, GregorianCalendar arrivalDate, String departureHour, String arrivalHour, Double price) throws PriceException{
        this.flightId = flightId;
        this.departureAirportName = departureAirportName;
        this.arrivalAirportName = arrivalAirportName;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureHour = departureHour;
        this.arrivalHour = arrivalHour;
        setPrice(price);
    }

    public FlightResearch(int flightId, String departureAirportName, String arrivalAirportName, GregorianCalendar departureDate, GregorianCalendar arrivalDate, String departureHour, String arrivalHour) throws PriceException {
        this(flightId, departureAirportName, arrivalAirportName, departureDate, arrivalDate, departureHour, arrivalHour, null);
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
    public void setPrice(double price) throws PriceException {
        if(price < 0)
            throw new PriceException();
        else
            this.price = price;
    }
}
