package DataAccess;

import Model.FlightResearch;
import Model.Locality;
import java.util.ArrayList;
import Exception.FlightsStopoverException;
import Exception.FlightStopoversException;
import Model.Stopover;

public interface FlightsStopoverDataAccess {
     ArrayList<FlightResearch> getFlightsStopover(Locality departure, Locality arrival, boolean withStopover) throws FlightsStopoverException;
     ArrayList<Stopover> getStopoversOfFlight(int flightID) throws FlightStopoversException;
}
