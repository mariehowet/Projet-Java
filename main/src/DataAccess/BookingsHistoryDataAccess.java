package DataAccess;

import Model.Passenger;
import Model.PassengerBooking;

import java.util.ArrayList;
import Exception.*;

public interface BookingsHistoryDataAccess {
    public ArrayList<PassengerBooking> getBookingsHistory(int idPassenger) throws BookingsHistoryException;
    public ArrayList<Passenger> getAllPassengers() throws PassengerException;
}
