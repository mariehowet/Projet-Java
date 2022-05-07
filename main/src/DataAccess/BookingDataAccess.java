package DataAccess;

import Model.Booking;
import Exception.*;

import java.util.ArrayList;

public interface BookingDataAccess {

    public void addBooking(Booking booking) throws AddBookingException;
    public ArrayList<Booking> getAllBookings() throws AllBookingsException;
    public void updateBooking(Booking booking) throws UpdateException;
    public void deleteBooking(Booking booking);
    public void closeConnection() throws CloseDataException;
}
