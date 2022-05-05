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

    public BookingManager(){
       try {
           setDao( new BookingDBAccess());
       } catch (ConnectionException connectionException) {
           JOptionPane.showMessageDialog(null, connectionException.getMessage()); // mettre une méthode d'affichage pour l'utilisateur
       }

    }

    public ArrayList<Booking> getAllBookings() throws AllBookingsException, AddBookingException {
        return dao.getAllBookings();
    }

    public void addBooking(Booking booking) throws AddBookingException {
        dao.addBooking(booking);
    }

    public void setDao(BookingDataAccess dao) {
        this.dao = dao;
    }
}
