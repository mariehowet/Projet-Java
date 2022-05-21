package Model;

import Exception.PriceException;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatType seatType = (SeatType) o;
        return additionalPrice == seatType.additionalPrice && Objects.equals(name, seatType.name);
    }

}
