package Model;

import java.util.GregorianCalendar;
import Exception.PriceException;

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

    public Flight(int id, GregorianCalendar departureDate, String departureHour, GregorianCalendar expectedArrivalDate, String expectedArrivalHour, double price, int airplaneId, int departureAirportId, int arrivalAirportId) throws PriceException {
        this.id = id;
        this.departureDate = departureDate;
        this.departureHour = departureHour;
        this.expectedArrivalDate = expectedArrivalDate;
        this.expectedArrivalHour = expectedArrivalHour;
        setPrice(price);
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

    public void setPrice(double price) throws PriceException{
        if(price < 0)
            throw new PriceException();
        else
            this.price = price;
    }
}
