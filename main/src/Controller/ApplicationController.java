package Controller;

import Exception.*;
import Business.BookingManager;
import Model.Booking;
import Model.Passenger;
import Model.SeatType;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Optional;

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
    public void updateBooking(int id, GregorianCalendar date, Boolean hasPaid, Integer luggageWeight, String companyName, String mealType, Double realPrice, int seatID) throws UpdateException{
        manager.updateBooking( id,  date,  hasPaid,  luggageWeight,  companyName,  mealType,  realPrice,  seatID);
    }

    public void deleteBooking(Booking booking) throws DeleteException {
        manager.deleteBooking(booking);
    }

    public ArrayList<Passenger> getAllPassengers() throws PassengerException {
        return manager.getAllPassengers();
    }

    public ArrayList<SeatType> getAllSeatTypes() throws SeatTypeException {
        return manager.getAllSeatTypes();
    }

}
