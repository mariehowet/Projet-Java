package Model;

import java.util.HashSet;

public class SeatType {
    private String name;
    private double additionalPercent;
    private HashSet<Seat> seats;

    public SeatType(String name, double additionalPercent, HashSet<Seat> seats) {
        this.name = name;
        this.additionalPercent = additionalPercent;
        this.seats = new HashSet<Seat>();
    }
}
