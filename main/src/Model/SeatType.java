package Model;

import java.util.HashSet;

public class SeatType {
    private String name;
    private int additionalPrice;


    public SeatType(String name, int additionalPrice) {
        this.name = name;
        this.additionalPrice = additionalPrice;
    }

    public String getName() {
        return name;
    }

    public int getAdditionalPrice() {
        return additionalPrice;
    }
}
