package Business;

import DataAccess.BookingDBAccess;
import DataAccess.BookingDataAccess;
import Model.Booking;
import Exception.*;

import javax.swing.*;
import Exception.AddBookingException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Optional;


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

    public void updateBooking(int id, GregorianCalendar date, Boolean hasPaid, Integer luggageWeight, String companyName, String mealType, Double realPrice, int seatID) throws UpdateException{
        dao.updateBooking(id, date, hasPaid, luggageWeight, companyName, mealType, realPrice, seatID);
    }

    public void deleteBooking(Booking booking) throws DeleteException {
        dao.deleteBooking(booking);
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
