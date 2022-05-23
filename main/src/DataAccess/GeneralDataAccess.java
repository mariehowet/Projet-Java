package DataAccess;

import java.util.ArrayList;
import Exception.CloseDataException;
import Exception.PassengerException;
import Exception.AllLocalitiesException;
import Model.Locality;
import Model.Passenger;

public interface GeneralDataAccess {
     void closeConnection() throws CloseDataException;
     ArrayList<Passenger> getAllPassengers() throws PassengerException;
     ArrayList<Locality> getAllLocalities() throws AllLocalitiesException;
}
