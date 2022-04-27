package Model;

public class SeatBooked {

    private Flight flight;
    private Seat seat;
    private Booking booking;

    public SeatBooked(Flight flight, Seat seat, Booking booking) {
        this.flight = flight;
        this.seat = seat;
        this.booking = booking;
    }

    public SeatBooked(Flight flight, Seat seat) {
        this(flight, seat, null);
    }
}
