package DataAccess;

import Model.Locality;
import java.util.ArrayList;
import Exception.FlightsStopoverException;
import Exception.FlightStopoversException;
import Exception.PriceException;
import Model.Stopover;
import Model.FlightResearch;

public interface FlightsStopoverDataAccess {
     ArrayList<FlightResearch> getFlightsStopover(Locality departure, Locality arrival, boolean withStopover) throws FlightsStopoverException;
     ArrayList<Stopover> getStopoversOfFlight(int flightID) throws FlightStopoversException;
}
