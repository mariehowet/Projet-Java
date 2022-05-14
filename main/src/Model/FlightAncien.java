package Model;

import java.util.GregorianCalendar;

public class FlightAncien {
    private int id;
    private GregorianCalendar departureDate;
    private String departureHour;
    private GregorianCalendar expectedArrivalDate;
    private String expectedArrivalHour;
    private double price;
    private int airplaneId;
    private int departureAirportId;
    private int arrivalAirportId;


    public FlightAncien(GregorianCalendar departureDate, String departureHour, GregorianCalendar expectedArrivalDate, String expectedArrivalHour, double price, int airplaneId, int departureAirportId, int arrivalAirportId) {
        this.departureDate = departureDate;
        this.departureHour = departureHour;
        this.expectedArrivalDate = expectedArrivalDate;
        this.expectedArrivalHour = expectedArrivalHour;
        this.price = price;
        this.airplaneId = airplaneId;
        this.departureAirportId = departureAirportId;
        this.arrivalAirportId = arrivalAirportId;
    }

    public FlightAncien(int id, GregorianCalendar departureDate, String departureHour, GregorianCalendar expectedArrivalDate, String expectedArrivalHour, double price, int airplaneId, int departureAirportId, int arrivalAirportId) {
        this.id = id;
        this.departureDate = departureDate;
        this.departureHour = departureHour;
        this.expectedArrivalDate = expectedArrivalDate;
        this.expectedArrivalHour = expectedArrivalHour;
        this.price = price;
        this.airplaneId = airplaneId;
        this.departureAirportId = departureAirportId;
        this.arrivalAirportId = arrivalAirportId;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public int getAirplaneId() {
        return airplaneId;
    }
}
