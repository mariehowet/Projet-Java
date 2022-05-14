package DataAccess;

import java.sql.SQLException;
import java.util.ArrayList;

import Exception.*;
import Model.Locality;
import Model.Passenger;

public interface GeneralDataAccess {
    public void closeConnection() throws CloseDataException;
    public ArrayList<Passenger> getAllPassengers() throws PassengerException;
    public ArrayList<Locality> getAllLocalities() throws AllLocalitiesException;
}
