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

    public ArrayList<Booking> getAllBookings () throws AllBookingsException, PriceException {
        return manager.getAllBookings();
    }

    public void closeConnection() throws  CloseDataException {
        manager.closeConnection();
    }
    public void updateBooking(int id, GregorianCalendar date, Boolean hasPaid, String luggageWeight, String companyName, String mealType, Double realPrice, int seatID) throws UpdateException, PriceException{
        manager.updateBooking( id,  date,  hasPaid,  luggageWeight,  companyName,  mealType,  realPrice,  seatID);
    }

    public void deleteBooking(Booking booking) throws DeleteException {
        manager.deleteBooking(booking);
    }

    public ArrayList<Passenger> getAllPassengers() throws PassengerException {
        return manager.getAllPassengers();
    }

    public ArrayList<SeatType> getAllSeatTypes() throws SeatTypeException, PriceException {
        return manager.getAllSeatTypes();
    }

    public ArrayList<Flight> getAllFlights() throws AllFlightsException,PriceException {
        return manager.getAllFlights();
    }
    public ArrayList<Seat> getAvailableSeats(String seatType, int flightID) throws AvailableSeatsException, SeatNumberException {
        return manager.getAvailableSeats(seatType, flightID);
    }

    public Double getFlightPrice(int flightID) throws FlightPriceException {
        return manager.getFlightPrice(flightID);
    }

    public String getSeatTypeName(int seatID) throws SeatTypeNameException{
        return manager.getSeatTypeName(seatID);
    }

    public Seat getActualSeat (int seatID) throws ActualSeatException, SeatNumberException {
        return manager.getActualSeat(seatID);
    }

    public Passenger getActualPassenger(int passengerID) throws ActualPassengerException {
        return manager.getActualPassenger(passengerID);
    }

    public ArrayList<PassengerBooking> getBookingsHistory(int idPassenger) throws BookingsHistoryException, PriceException {
        return manager.getBookingsHistory(idPassenger);
    }

    public ArrayList<FlightResearch> getFlightsStopover(Locality departure, Locality arrival, boolean withStopover) throws FlightsStopover, PriceException {
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

    public ArrayList<FlightResearch> getFlights(Locality departure, Locality arrival, Date startDate, Date endDate) throws FlightsException, PriceException {
        return manager.getFlights(departure, arrival, startDate, endDate);
    }

    public ArrayList<FlightOption> getFlightOptions(int flightId) {
        return manager.getFlightOptions(flightId);
    }

}
