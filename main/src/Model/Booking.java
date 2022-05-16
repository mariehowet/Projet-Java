package Model;

import java.util.GregorianCalendar;
import Exception.PriceException;

public class Booking {
    private int id;
    private GregorianCalendar date;
    private Boolean hasPaid;
    private String luggageWeight;
    private String companyName;
    private String mealType;
    private Double realPrice;
    private int flightID;
    private int seatID;
    private int passengerID;

    public Booking(Boolean hasPaid, String luggageWeight, String companyName, String mealType, Double realPrice, int flightID, int seatID, int passengerID) throws PriceException{
        date = new GregorianCalendar();
        this.hasPaid = hasPaid;
        setLuggageWeight(luggageWeight);
        setCompanyName(companyName);
        this.mealType = mealType;
        setRealPrice(realPrice);
        this.flightID = flightID;
        this.seatID = seatID;
        this.passengerID = passengerID;
    }

    public Booking(int id, GregorianCalendar date, Boolean hasPaid, String mealType, Double realPrice, int flightID, int seatID, int passengerID) throws PriceException{
        setId(id);
        this.date = date;
        this.hasPaid = hasPaid;
        this.mealType = mealType;
        setRealPrice(realPrice);
        this.flightID = flightID;
        this.seatID = seatID;
        this.passengerID = passengerID;
    }

    public int getId() {
        return id;
    }
    public GregorianCalendar getDate() {
        return date;
    }
    public Boolean getHasPaid() {
        return hasPaid;
    }
    public String getLuggageWeight() {
        return luggageWeight;
    }
    public String getCompanyName() {
        return companyName;
    }
    public String getMealType() {
        return mealType;
    }
    public Double getRealPrice() {
        return realPrice;
    }
    public int getFlightID() {
        return flightID;
    }
    public int getSeatID() {
        return seatID;
    }
    public int getPassengerID() {
        return passengerID;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setLuggageWeight(String luggageWeight) {
        this.luggageWeight = luggageWeight;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setRealPrice(Double realPrice) throws PriceException {
        if(realPrice < 0)
            throw new PriceException();
        else
            this.realPrice = realPrice;
    }
}


