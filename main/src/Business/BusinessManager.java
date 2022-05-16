package Business;

import DataAccess.*;

import Model.*;
import Exception.*;

import Exception.AddBookingException;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;


public class BusinessManager {
    private BookingDataAccess daoBooking;

    private BookingsHistoryDataAccess daoBookingsHistory;
    private GeneralDataAccess daoGeneral;
    private FlightsStopoverDataAccess daoFlightsStopover;
    private FlightsOfDepartureAirportDataAccess daoFlightsOfDepartureAirport;
    private FindFlightsDataAccess daoFlights;

    public BusinessManager() throws ConnectionException{
        daoBooking = new BookingDBAccess();
        daoBookingsHistory = new BookingsHistoryDBAccess();
        daoGeneral = new GeneralDBAccess();
        daoFlightsStopover = new FlightsStopoverDBAccess();
        daoFlightsOfDepartureAirport = new FlightsOfDepartureAirportDBAccess();
        daoFlights = new FindFlightsDBAccess();
    }

    public ArrayList<Booking> getAllBookings() throws AllBookingsException, PriceException {
        return daoBooking.getAllBookings();
    }

    public void updateBooking(int id, GregorianCalendar date, Boolean hasPaid, String luggageWeight, String companyName, String mealType, Double realPrice, int seatID) throws UpdateException, PriceException{
        if(realPrice < 0)
            throw new PriceException();
        daoBooking.updateBooking(id, date, hasPaid, luggageWeight, companyName, mealType, realPrice, seatID);
    }

    public void deleteBooking(Booking booking) throws DeleteException {
        daoBooking.deleteBooking(booking);
    }

    public void addBooking(Booking booking) throws AddBookingException {
        daoBooking.addBooking(booking);
    }


    public void closeConnection() throws CloseDataException {
        daoGeneral.closeConnection();
    }

    public ArrayList<Passenger> getAllPassengers() throws PassengerException {
        return daoGeneral.getAllPassengers();
    }

    public ArrayList<SeatType> getAllSeatTypes() throws SeatTypeException, PriceException{
        return daoBooking.getAllSeatTypes();
    }

    public ArrayList<Flight> getAllFlights() throws AllFlightsException, PriceException {
        return daoBooking.getAllFlights();
    }
    public ArrayList<Seat> getAvailableSeats(String seatType, int flightID) throws AvailableSeatsException, SeatNumberException {
        return daoBooking.getAvailableSeats(seatType, flightID);
    }

    public Double getFlightPrice(int flightID) throws FlightPriceException {
        return daoBooking.getFlightPrice(flightID);
    }
    public String getSeatTypeName(int seatID) throws SeatTypeNameException{
        return daoBooking.getSeatTypeName(seatID);
    }

    public Seat getActualSeat (int seatID) throws ActualSeatException, SeatNumberException{
        return daoBooking.getActualSeat(seatID);
    }

    public ArrayList<PassengerBooking> getBookingsHistory(int idPassenger) throws BookingsHistoryException , PriceException{
        return daoBookingsHistory.getBookingsHistory(idPassenger);
    }

    public ArrayList<FlightResearch> getFlightsStopover(Locality departure, Locality arrival, boolean withStopover) throws FlightsStopover, PriceException {
        return daoFlightsStopover.getFlightsStopover(departure, arrival, withStopover);
    }

    public ArrayList<Locality> getAllLocalities() throws AllLocalitiesException {
        return daoGeneral.getAllLocalities();
    }
    public ArrayList<Airport> getAllAirports() throws AllAirportsException {
        return daoFlightsOfDepartureAirport.getAllAirports();
    }

    public ArrayList<FlightOfDepartureAirport> getFlightsOfDepartureAirport(Date startDate, Date endDate, int idAirport) throws FlightsOfDepartureAirportException {
        return daoFlightsOfDepartureAirport.getFlightsOfDepartureAirport(startDate, endDate, idAirport);
    }


    public ArrayList<FlightResearch> getFlights(Locality departure, Locality arrival, Date startDate, Date endDate) throws FlightsException, PriceException {
        return daoFlights.getFlights(departure, arrival, startDate, endDate);
    }

}