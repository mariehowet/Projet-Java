package DataAccess;

import Model.Booking;
import Exception.ConnectionException;


import java.sql.Connection;

public class BookingDBAccess implements BookingDataAccess{
    private Connection connection;

    public BookingDBAccess() throws ConnectionException {
        connection = SingletonConnexion.getInstance();
    }

    @Override
    public void addBooking(Booking booking) {
        //String sql
    }

    @Override
    public Booking[] getAllBookings() {
        return null;
    }

    @Override
    public void updateBooking(Booking booking) {

    }

    @Override
    public void deleteBooking(Booking booking) {

    }
}
