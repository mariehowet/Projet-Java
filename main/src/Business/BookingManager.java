package Business;

import DataAccess.BookingDBAccess;
import DataAccess.BookingDataAccess;
import Model.Booking;
import Exception.*;

import javax.swing.*;
import Exception.AddBookingException;

import java.util.ArrayList;


public class BookingManager {
    private BookingDataAccess dao;

    public BookingManager() throws ConnectionException{
        dao = new BookingDBAccess();
    }

    public ArrayList<Booking> getAllBookings() throws AllBookingsException {
        ArrayList<Booking>  bookingList = dao.getAllBookings();
        // traitement
        return bookingList;
    }

    public void addBooking(Booking booking) throws AddBookingException {
        dao.addBooking(booking);
    }

    public void setDao(BookingDataAccess dao) {
        this.dao = dao;
    }

    public void closeConnection() throws CloseDataException {
        dao.closeConnection();
    }
}
