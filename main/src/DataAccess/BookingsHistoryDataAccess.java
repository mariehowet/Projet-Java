package DataAccess;

import Model.PassengerBooking;
import java.util.ArrayList;
import Exception.BookingsHistoryException;
import Exception.PriceException;

public interface BookingsHistoryDataAccess {
     ArrayList<PassengerBooking> getBookingsHistory(int idPassenger) throws BookingsHistoryException, PriceException;
}
