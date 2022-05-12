package DataAccess;

import Model.FlightStopover;
import Model.Locality;

import java.util.ArrayList;

import Exception.*;

public interface FlightsStopoverDataAccess {
    public ArrayList<FlightStopover> getFlightsStopover(Locality departure, Locality arrival, boolean withStopover) throws FlightsStopover;
    public ArrayList<Locality> getAllLocalities() throws AllLocalitiesException;

}
