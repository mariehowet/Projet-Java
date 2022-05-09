package Model;

import java.util.Optional;

public class Seat {
    private int id;
    private int number;
    private String columnLetter;
    private String seatType;
    private int airplaneId;


    public Seat(int number, String columnLetter, String seatType, int airplaneId) {
        this.number = number;
        this.columnLetter = columnLetter;
        this.seatType = seatType;
        this.airplaneId = airplaneId;
    }

    public Seat(int id, int number, String columnLetter, String seatType, int airplaneId) {
        this.id = id;
        this.number = number;
        this.columnLetter = columnLetter;
        this.seatType = seatType;
        this.airplaneId = airplaneId;
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public String getColumnLetter() {
        return columnLetter;
    }
}
