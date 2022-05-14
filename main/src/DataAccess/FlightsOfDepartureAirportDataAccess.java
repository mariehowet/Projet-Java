package DataAccess;

import Model.Airport;

import java.util.ArrayList;
import java.util.Date;

import Exception.*;
import Model.FlightOfDepartureAirport;

public interface FlightsOfDepartureAirportDataAccess {
    public ArrayList<Airport> getAllAirports() throws AllAirportsException;
    public ArrayList<FlightOfDepartureAirport> getFlightsOfDepartureAirport(Date startDate, Date endDate, int idAirport) throws FlightsOfDepartureAirportException;
}
