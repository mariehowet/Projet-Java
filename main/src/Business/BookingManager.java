package Business;

import DataAccess.BookingDBAccess;
import DataAccess.BookingDataAccess;
import Model.Booking;
import Exception.ConnectionException;

import javax.swing.*;


public class BookingManager {
    private BookingDataAccess dao;

    public BookingManager(){
       try {
           setDao( new BookingDBAccess());
       } catch (ConnectionException connectionException) {
           JOptionPane.showMessageDialog(null, connectionException.getMessage()); // mettre une méthode d'affichage pour l'utilisateur
       }

    }

    public Booking[] getAllBookings() {
        return dao.getAllBookings();
    }

    public void addBooking(Booking booking) {
        dao.addBooking(booking);
    }

    public void setDao(BookingDataAccess dao) {
        this.dao = dao;
    }
}
