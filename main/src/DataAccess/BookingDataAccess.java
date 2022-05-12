package DataAccess;

import Model.Booking;
import Exception.*;
import Model.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;


public interface BookingDataAccess {

    public void addBooking(Booking booking) throws AddBookingException;
    public ArrayList<Booking> getAllBookings() throws AllBookingsException;
    public void updateBooking(int id, GregorianCalendar date, Boolean hasPaid, String luggageWeight, String companyName, String mealType, Double realPrice, int seatID) throws UpdateException;
    public void deleteBooking(Booking booking) throws DeleteException;
    public ArrayList<Passenger> getAllPassengers() throws PassengerException;
    public ArrayList<SeatType> getAllSeatTypes () throws SeatTypeException;
    public ArrayList<Flight> getAllFlights() throws AllFlightsException;
    public ArrayList<Seat> getAvailableSeats(String seatType, Integer flightID) throws AvailableSeatsException;
}
