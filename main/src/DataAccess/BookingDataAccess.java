package DataAccess;

import Model.Booking;
import Exception.*;
import Model.PassengerBooking;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Optional;

public interface BookingDataAccess {

    public void addBooking(Booking booking) throws AddBookingException;
    public ArrayList<Booking> getAllBookings() throws AllBookingsException;
    public void updateBooking(int id, GregorianCalendar date, Boolean hasPaid, Integer luggageWeight, String companyName, String mealType, Double realPrice, int seatID) throws UpdateException;
    public void deleteBooking(Booking booking) throws DeleteException;
    public void closeConnection() throws CloseDataException;
    public ArrayList<PassengerBooking> getBookingsHistory(int idPassenger);
}
