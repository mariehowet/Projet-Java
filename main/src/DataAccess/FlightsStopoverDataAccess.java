package DataAccess;

import Model.FlightStopover;
import Model.Locality;
import java.util.ArrayList;
import Exception.FlightsStopover;
import Exception.PriceException;


public interface FlightsStopoverDataAccess {
     ArrayList<FlightStopover> getFlightsStopover(Locality departure, Locality arrival, boolean withStopover) throws FlightsStopover, PriceException;

}
