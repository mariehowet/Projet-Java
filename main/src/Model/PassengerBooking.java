package Model;

import java.util.GregorianCalendar;
import Exception.PriceException;

public class PassengerBooking {
    private int idBooking;
    private GregorianCalendar dateBooking;
    private int flightID;
    private String departureAirportName;
    private String arrivalAirportName;
    private GregorianCalendar departureDate;
    private String seatType;
    private Double realPrice;


    public PassengerBooking(int idBooking, GregorianCalendar dateBooking, int flightID, String departureAirportName, String arrivalAirportName, GregorianCalendar departureDate, String seatType, Double realPrice) throws PriceException {
        this.idBooking = idBooking;
        this.dateBooking = dateBooking;
        this.flightID = flightID;
        this.departureAirportName = departureAirportName;
        this.arrivalAirportName = arrivalAirportName;
        this.departureDate = departureDate;
        this.seatType = seatType;
        setRealPrice(realPrice);
    }

    public int getIdBooking() {
        return idBooking;
    }

    public GregorianCalendar getDateBooking() {
        return dateBooking;
    }

    public int getFlightID() {
        return flightID;
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

    public String getSeatType() {
        return seatType;
    }

    public Double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Double realPrice) throws PriceException {
        if(realPrice < 0)
            throw new PriceException();
        else
            this.realPrice = realPrice;
    }
}
