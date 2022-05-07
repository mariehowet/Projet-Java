package Model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

public class Booking {
    private int id;
    private GregorianCalendar date;
    private Boolean hasPaid;
    private Integer luggageWeight;
    private String companyName;
    private String mealType;
    private Double realPrice;
    private int flightID;
    private int seatID;
    private int passengerID;

    public Booking(Boolean hasPaid, Integer luggageWeight, String companyName, String mealType, Double realPrice, int flightID, int seatID, int passengerID) {
        setDate(new GregorianCalendar());
        this.hasPaid = hasPaid;
        setLuggageWeight(luggageWeight);
        setCompanyName(companyName);
        this.mealType = mealType;
        this.realPrice = realPrice;
        this.flightID = flightID;
        this.seatID = seatID;
        this.passengerID = passengerID;
    }

    public Booking(int id, GregorianCalendar date, Boolean hasPaid, String mealType, Double realPrice, int flightID, int seatID, int passengerID) {
        this.id = id;
        this.date = date;
        this.hasPaid = hasPaid;
        this.mealType = mealType;
        this.realPrice = realPrice;
        this.flightID = flightID;
        this.seatID = seatID;
        this.passengerID = passengerID;
    }
//

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public Boolean getHasPaid() {
        return hasPaid;
    }

    public void setHasPaid(Boolean hasPaid) {
        this.hasPaid = hasPaid;
    }

    public Integer getLuggageWeight() {
        return luggageWeight;
    }

    public void setLuggageWeight(Integer luggageWeight) {
        this.luggageWeight = luggageWeight;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public Double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public int getSeatID() {
        return seatID;
    }

    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }

    public int getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(int passengerID) {
        this.passengerID = passengerID;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", date=" + date.getTime() +
                ", hasPaid=" + hasPaid +
                ", luggageWeight=" + luggageWeight +
                ", companyName='" + companyName + '\'' +
                ", mealType='" + mealType + '\'' +
                ", realPrice=" + realPrice +
                ", flightID=" + flightID +
                ", seatID=" + seatID +
                ", passengerID=" + passengerID +
                '}';
    }
}


