package DataAccess;

import Model.Booking;

public interface BookingDataAccess {

    public void addBooking(Booking booking);
    public Booking[] getAllBookings();
    public void updateBooking(Booking booking);
    public void deleteBooking(Booking booking);
}
