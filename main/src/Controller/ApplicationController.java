package Controller;

import Exception.*;
import Business.BookingManager;
import Model.Booking;

import java.util.ArrayList;

public class ApplicationController {
    private BookingManager manager;

    public ApplicationController() throws ConnectionException{
        manager = new BookingManager();
    }

    public void addBooking(Booking booking) throws AddBookingException {
        manager.addBooking(booking);
    }

    public ArrayList<Booking> getAllBookings () throws AllBookingsException {
        return manager.getAllBookings();
    }

    public void closeConnection() throws  CloseDataException {
        manager.closeConnection();
    }
}
