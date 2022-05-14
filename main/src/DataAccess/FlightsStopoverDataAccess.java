package DataAccess;

import Model.FlightResearch;
import Model.Locality;

import java.util.ArrayList;

import Exception.*;

public interface FlightsStopoverDataAccess {
    public ArrayList<FlightResearch> getFlightsStopover(Locality departure, Locality arrival, boolean withStopover) throws FlightsStopover;

}
