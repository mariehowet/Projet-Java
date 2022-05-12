package Business;

import DataAccess.*;

import Model.*;
import Exception.*;

import Exception.AddBookingException;

import java.util.ArrayList;
import java.util.GregorianCalendar;


public class BusinessManager {
    private BookingDataAccess daoBooking;

    private BookingsHistoryDataAccess daoBookingsHistory;
    private GeneralDataAccess daoGeneral;
    private FlightsStopoverDataAccess daoFlightsStopover;
    private FlightsOfDepartureAirportDataAccess daoFlightsOfDepartureAirport;

    public BusinessManager() throws ConnectionException{
        daoBooking = new BookingDBAccess();
        daoBookingsHistory = new BookingsHistoryDBAccess();
        daoGeneral = new GeneralDBAccess();
        daoFlightsStopover = new FlightsStopoverDBAccess();
        daoFlightsOfDepartureAirport = new FlightsOfDepartureAirportDBAccess();
    }

    public ArrayList<Booking> getAllBookings() throws AllBookingsException {
        ArrayList<Booking>  bookingList = daoBooking.getAllBookings();
        // traitement
        return bookingList;
    }

    public void updateBooking(int id, GregorianCalendar date, Boolean hasPaid, String luggageWeight, String companyName, String mealType, Double realPrice, int seatID) throws UpdateException{
        daoBooking.updateBooking(id, date, hasPaid, luggageWeight, companyName, mealType, realPrice, seatID);
    }

    public void deleteBooking(Booking booking) throws DeleteException {
        daoBooking.deleteBooking(booking);
    }

    public void addBooking(Booking booking) throws AddBookingException {
        daoBooking.addBooking(booking);
    }

    public void setdaoBooking(BookingDataAccess daoBooking) {
        this.daoBooking = daoBooking;
    }

    public void closeConnection() throws CloseDataException {
        daoGeneral.closeConnection();
    }

    public ArrayList<Passenger> getAllPassengers() throws PassengerException {
        return daoBookingsHistory.getAllPassengers();
    }

    public ArrayList<SeatType> getAllSeatTypes() throws SeatTypeException {
        return daoBooking.getAllSeatTypes();
    }

    public ArrayList<Flight> getAllFlights() throws AllFlightsException {
        return daoBooking.getAllFlights();
    }
    public ArrayList<Seat> getAvailableSeats(String seatType, Integer flightID) throws AvailableSeatsException {
        return daoBooking.getAvailableSeats(seatType, flightID);
    }

    public ArrayList<PassengerBooking> getBookingsHistory(int idPassenger) throws BookingsHistoryException {
        return daoBookingsHistory.getBookingsHistory(idPassenger);
    }

    public ArrayList<FlightStopover> getFlightsStopover(Locality departure, Locality arrival, boolean withStopover) throws FlightsStopover {
        return daoFlightsStopover.getFlightsStopover(departure, arrival, withStopover);
    }

    public ArrayList<Locality> getAllLocalities() throws AllLocalitiesException {
        return daoFlightsStopover.getAllLocalities();
    }

}
