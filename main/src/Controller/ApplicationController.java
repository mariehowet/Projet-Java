package Controller;

import Exception.*;
import Business.BusinessManager;
import Model.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ApplicationController {
    private BusinessManager manager;

    public ApplicationController() throws ConnectionException{
        manager = new BusinessManager();
    }

    public void addBooking(Booking booking) throws AddBookingException {
        manager.addBooking(booking);
    }

    public ArrayList<Booking> getAllBookings () throws AllBookingsException {
        return manager.getAllBookings();
    }

    public void closeConnection() throws  CloseDataException {
        manager.closeConnection();
    }
    public void updateBooking(int id, GregorianCalendar date, Boolean hasPaid, String luggageWeight, String companyName, String mealType, Double realPrice, int seatID) throws UpdateException{
        manager.updateBooking( id,  date,  hasPaid,  luggageWeight,  companyName,  mealType,  realPrice,  seatID);
    }

    public void deleteBooking(Booking booking) throws DeleteException {
        manager.deleteBooking(booking);
    }

    public ArrayList<Passenger> getAllPassengers() throws PassengerException {
        return manager.getAllPassengers();
    }

    public ArrayList<SeatType> getAllSeatTypes() throws SeatTypeException {
        return manager.getAllSeatTypes();
    }

    public ArrayList<Flight> getAllFlights() throws AllFlightsException {
        return manager.getAllFlights();
    }
    public ArrayList<Seat> getAvailableSeats(String seatType, Integer flightID) throws AvailableSeatsException {
        return manager.getAvailableSeats(seatType, flightID);
    }

    public ArrayList<PassengerBooking> getBookingsHistory(int idPassenger) throws BookingsHistoryException {
        return manager.getBookingsHistory(idPassenger);
    }

}
