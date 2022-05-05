package Model;

import java.util.Optional;

public class Seat {
    private int id;
    private int number;
    private char columnLetter;
    private String seatType;
    private int airplaneId;


    public Seat(int number, char columnLetter, String seatType, int airplaneId) {
        this.number = number;
        this.columnLetter = columnLetter;
        this.seatType = seatType;
        this.airplaneId = airplaneId;
    }
}
