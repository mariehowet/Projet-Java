package Model;

import java.util.Optional;

public class Seat {
    private int id;
    private int number;
    private char columnLetter;
    private Flight flight;
    private Optional<Booking> booking; // ?????

    public Seat(int id, int number, char columnLetter, Flight flight, Optional<Booking> booking) {
        this.id = id;
        this.number = number;
        this.columnLetter = columnLetter;
        this.flight = flight;
        this.booking = booking; // ?????
    }
}
