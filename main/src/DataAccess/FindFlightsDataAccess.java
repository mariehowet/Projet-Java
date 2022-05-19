package DataAccess;

import Model.FlightResearch;
import java.util.ArrayList;
import java.util.Date;
import Exception.FlightsException;
import Exception.PriceException;
import Model.Locality;

public interface FindFlightsDataAccess {
     ArrayList<FlightResearch> getFlights(Locality departure, Locality arrival, Date startDate, Date endDate) throws FlightsException, PriceException;
}
