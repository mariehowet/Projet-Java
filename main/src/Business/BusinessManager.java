package Business;

import DataAccess.DBAccess;
import DataAccess.DataAccess;
import Model.*;
import Exception.*;

import Exception.AddBookingException;

import java.util.ArrayList;
import java.util.GregorianCalendar;


public class BusinessManager {
    private DataAccess dao;

    public BusinessManager() throws ConnectionException{
        dao = new DBAccess();
    }

    public ArrayList<Booking> getAllBookings() throws AllBookingsException {
        ArrayList<Booking>  bookingList = dao.getAllBookings();
        // traitement
        return bookingList;
    }

    public void updateBooking(int id, GregorianCalendar date, Boolean hasPaid, Integer luggageWeight, String companyName, String mealType, Double realPrice, int seatID) throws UpdateException{
        dao.updateBooking(id, date, hasPaid, luggageWeight, companyName, mealType, realPrice, seatID);
    }

    public void deleteBooking(Booking booking) throws DeleteException {
        dao.deleteBooking(booking);
    }

    public void addBooking(Booking booking) throws AddBookingException {
        dao.addBooking(booking);
    }

    public void setDao(DataAccess dao) {
        this.dao = dao;
    }

    public void closeConnection() throws CloseDataException {
        dao.closeConnection();
    }

    public ArrayList<Passenger> getAllPassengers() throws PassengerException {
        return dao.getAllPassengers();
    }

    public ArrayList<SeatType> getAllSeatTypes() throws SeatTypeException {
        return dao.getAllSeatTypes();
    }

    public ArrayList<Flight> getAllFlights() throws AllFlightsException {
        return dao.getAllFlights();
    }
    public ArrayList<Seat> getAvailableSeats(String seatType) throws AvailableSeatsException {
        return dao.getAvailableSeats(seatType);
    }

    public ArrayList<PassengerBooking> getBookingsHistory(int idPassenger) throws BookingsHistoryException {
        return dao.getBookingsHistory(idPassenger);
    }


}
