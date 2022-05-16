package DataAccess;

import Model.FlightResearch;

import java.util.ArrayList;
import java.util.Date;

import Exception.*;
import Model.Locality;

public interface FindFlightsDataAccess {
    public ArrayList<FlightResearch> getFlights(Locality departure, Locality arrival, Date startDate, Date endDate) throws FlightsException, PriceException;
}
