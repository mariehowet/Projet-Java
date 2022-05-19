package Model;

public class FlightOption {
    private String seatType;
    private int priceSeatType;
    private String luggageWeight;
    private Double priceLuggageWeight;
    private Double totalPrice;

    public FlightOption(String seatType, int priceSeatType, String luggageWeight, Double priceLuggageWeight, Double totalPrice) {
        this.seatType = seatType;
        this.priceSeatType = priceSeatType;
        this.luggageWeight = luggageWeight;
        this.priceLuggageWeight = priceLuggageWeight;
        this.totalPrice = totalPrice;
    }

    public String getSeatType() {
        return seatType;
    }
    public int getPriceSeatType() {
        return priceSeatType;
    }
    public String getLuggageWeight() {
        return luggageWeight;
    }
    public Double getPriceLuggageWeight() {
        return priceLuggageWeight;
    }
    public Double getTotalPrice() {
        return totalPrice;
    }
}
