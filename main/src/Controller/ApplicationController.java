package Controller;

import Exception.*;
import Business.BusinessManager;
import Model.*;

import java.util.ArrayList;
import java.util.Date;
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

    public ArrayList<FlightAncien> getAllFlights() throws AllFlightsException {
        return manager.getAllFlights();
    }
    public ArrayList<Seat> getAvailableSeats(String seatType, int flightID) throws AvailableSeatsException {
        return manager.getAvailableSeats(seatType, flightID);
    }

    public ArrayList<PassengerBooking> getBookingsHistory(int idPassenger) throws BookingsHistoryException {
        return manager.getBookingsHistory(idPassenger);
    }

    public ArrayList<Flight> getFlightsStopover(Locality departure, Locality arrival, boolean withStopover) throws FlightsStopover {
        return manager.getFlightsStopover(departure, arrival, withStopover);
    }

    public ArrayList<Locality> getAllLocalities() throws AllLocalitiesException {
        return manager.getAllLocalities();
    }
    public ArrayList<Airport> getAllAirports() throws AllAirportsException {
        return manager.getAllAirports();
    }

    public ArrayList<FlightOfDepartureAirport> getFlightsOfDepartureAirport(Date startDate, Date endDate, int idAirport) throws FlightsOfDepartureAirportException {
        return manager.getFlightsOfDepartureAirport(startDate, endDate, idAirport);
    }

}
