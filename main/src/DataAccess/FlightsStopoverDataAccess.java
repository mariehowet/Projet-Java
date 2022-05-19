package DataAccess;

import Model.FlightResearch;
import Model.Locality;
import java.util.ArrayList;
import Exception.FlightsStopover;
import Exception.PriceException;

public interface FlightsStopoverDataAccess {
     ArrayList<FlightResearch> getFlightsStopover(Locality departure, Locality arrival, boolean withStopover) throws FlightsStopover, PriceException;

}
