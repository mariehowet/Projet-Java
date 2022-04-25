package DataAccess;

import Model.Booking;
import Exception.ConnectionException;

import java.sql.SQLException;

public interface BookingDataAccess {

    public void addBooking(Booking booking) throws ConnectionException;
    public Booking[] getAllBookings();
    public void updateBooking(Booking booking);
    public void deleteBooking(Booking booking);
}
