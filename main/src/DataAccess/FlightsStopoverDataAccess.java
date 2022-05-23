package DataAccess;

import Model.FlightWithOrWhithoutStopover;
import Model.Locality;
import java.util.ArrayList;
import Exception.FlightsStopoverException;
import Exception.FlightStopoversException;
import Exception.PriceException;
import Model.Stopover;


public interface FlightsStopoverDataAccess {
     ArrayList<FlightWithOrWhithoutStopover> getFlightsStopover(Locality departure, Locality arrival, boolean withStopover) throws FlightsStopoverException, PriceException;
     ArrayList<Stopover> getStopoversOfFlight(int flightID) throws FlightStopoversException;
}
