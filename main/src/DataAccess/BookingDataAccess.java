package DataAccess;

import Model.Booking;
import Exception.*;
import Model.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;


public interface BookingDataAccess {
    void addBooking(Booking booking) throws AddBookingException;

    ArrayList<Booking> getAllBookings() throws AllBookingsException, PriceException;

    void updateBooking(int id, GregorianCalendar date, Boolean hasPaid, String luggageWeight, String companyName, String mealType, Double realPrice, int seatID) throws UpdateException;

    void deleteBooking(Booking booking) throws DeleteException;

    ArrayList<SeatType> getAllSeatTypes() throws AllSeatTypesException, PriceException;

    ArrayList<Flight> getAllFlights() throws AllFlightsException, PriceException;

    ArrayList<Seat> getAvailableSeats(String seatType, int flightID) throws AvailableSeatsException, SeatNumberException;

    Double getFlightPrice(int flightID) throws FlightPriceException;

    SeatType getActualSeatType(int seatID) throws SeatTypeException, PriceException;

    Seat getActualSeat(int seatID) throws ActualSeatException, SeatNumberException;

    Passenger getActualPassenger(int passengerID) throws ActualPassengerException;
}
