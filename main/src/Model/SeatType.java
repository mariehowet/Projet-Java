package Model;

import Exception.PriceException;

public class SeatType {
    private String name;
    private int additionalPrice;

    public SeatType(String name, int additionalPrice) throws PriceException {
        this.name = name;
        setAdditionalPrice(additionalPrice);
    }

    public String getName() {
        return name;
    }
    public int getAdditionalPrice() {
        return additionalPrice;
    }

    public void setAdditionalPrice(int additionalPrice) throws PriceException{
        if(additionalPrice < 0)
            throw new PriceException();
        else
            this.additionalPrice = additionalPrice;
    }
}
