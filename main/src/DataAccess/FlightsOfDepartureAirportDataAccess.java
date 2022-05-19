package DataAccess;

import Model.Airport;
import java.util.ArrayList;
import java.util.Date;
import Exception.AllAirportsException;
import Exception.FlightsOfDepartureAirportException;
import Model.FlightOfDepartureAirport;

public interface FlightsOfDepartureAirportDataAccess {
     ArrayList<Airport> getAllAirports() throws AllAirportsException;
     ArrayList<FlightOfDepartureAirport> getFlightsOfDepartureAirport(Date startDate, Date endDate, int idAirport) throws FlightsOfDepartureAirportException;
}
