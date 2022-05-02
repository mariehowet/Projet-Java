package DataAccess;

import Model.Booking;
import Exception.AddBookingException;

public interface BookingDataAccess {

    public void addBooking(Booking booking) throws AddBookingException;
    public Booking[] getAllBookings();
    public void updateBooking(Booking booking);
    public void deleteBooking(Booking booking);
}
