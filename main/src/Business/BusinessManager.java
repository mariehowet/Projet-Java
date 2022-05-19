package Business;

import DataAccess.*;
import Model.*;
import Exception.*;
import Exception.AddBookingException;
import test.Calculator;
import javax.swing.*;
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

    public ArrayList<SeatType> getAllSeatTypes() throws AllSeatTypesException, PriceException{
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
    public  SeatType getActualSeatType(int seatID) throws SeatTypeException, PriceException{
        return daoBooking.getActualSeatType(seatID);
    }

    public Seat getActualSeat (int seatID) throws ActualSeatException, SeatNumberException{
        return daoBooking.getActualSeat(seatID);
    }

    public Passenger getActualPassenger(int passengerID) throws ActualPassengerException {
        return daoBooking.getActualPassenger(passengerID);
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

    public ArrayList<FlightOption> getFlightOptions(FlightResearch flight) {
        ArrayList<FlightOption> flightsOptions = new ArrayList<>();

        try {
            ArrayList<SeatType> allSeatTypes = daoBooking.getAllSeatTypes();
            String[] weights = {"sans bagage","0 < 10 kg","10 < 20 kg","20 < 30 kg","Max 35 kg"};
            Double[] priceWeights = {0.0,0.0,10.0,20.0,25.0};
            Calculator calculator = new Calculator();
            // remplir le tableau des options de vol
            // peut etre faire une boucle sur les différents siege et a l'interieur faire une boucle sur les différents

            for (SeatType seatType : allSeatTypes) {
                for (int i = 0; i < weights.length; i++) {
                    flightsOptions.add(new FlightOption(seatType.getName(),seatType.getAdditionalPrice(),weights[i], priceWeights[i], calculator.add(flight.getPrice(),(double)seatType.getAdditionalPrice(),priceWeights[i])));
                }
            }

            return flightsOptions;
        }
        catch (AllSeatTypesException allSeatTypesException) {
            JOptionPane.showMessageDialog(null, allSeatTypesException.getMessage(), "Problème", JOptionPane.WARNING_MESSAGE);
        }
        catch (PriceException priceException) {
            JOptionPane.showMessageDialog(null, priceException.getMessage(), "Problème", JOptionPane.WARNING_MESSAGE);
        }
        return flightsOptions;
    }
}