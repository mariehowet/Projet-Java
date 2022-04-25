package Model;

import java.util.GregorianCalendar;
import java.util.Optional;

public class Booking {
    private Integer id;
    private GregorianCalendar date;
    private Boolean hasPaid;
    private Integer luggageWeight;
    private String companyName;
    private String mealType;
    private Double realPrice;
    private Integer passengerID;
    private Integer seatID;
    private Integer flightID;


    public Booking(Integer id, GregorianCalendar date, Boolean hasPaid, Integer luggageWeight, String companyName, String mealType, Double realPrice, Integer passengerID, Integer seatID, Integer flightID) {
        this.id = id;
        this.date = date;
        this.hasPaid = hasPaid;
        this.luggageWeight = luggageWeight;
        this.companyName = companyName;
        this.mealType = mealType;
        this.realPrice = realPrice;
        this.passengerID = passengerID;
        this.flightID = flightID;
        this.seatID = seatID;
    }

    public Booking(Integer id, GregorianCalendar date, Boolean hasPaid, String mealType, Double realPrice, Integer passengerID, Integer seatID, Integer flightID) {
        this(id, date, hasPaid, null, null, mealType, realPrice, passengerID, seatID, flightID);
    }

    public Integer getId() {
        return id;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public Boolean getHasPaid() {
        return hasPaid;
    }

    public Integer getLuggageWeight() {
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

    public Integer getPassengerID() {
        return passengerID;
    }

    public Integer getSeatID() {
        return seatID;
    }

    public Integer getFlightID() {
        return flightID;
    }
}


