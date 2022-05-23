package Model;

import Exception.SeatNumberException;
import java.util.Objects;

public class Seat {
    private int id;
    private int number;
    private String columnLetter;

    public Seat(int id, int number, String columnLetter) throws SeatNumberException {
        this.id = id;
        setNumber(number);
        this.columnLetter = columnLetter;

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

    public void setNumber(int number) throws SeatNumberException{
        if( number < 0)
            throw new SeatNumberException();
        else
            this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return id == seat.id && number == seat.number && Objects.equals(columnLetter, seat.columnLetter);
    }

}
