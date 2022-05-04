package Controller;

import Exception.AddBookingException;
import Business.BookingManager;
import Model.Booking;

public class ApplicationController {
    private BookingManager manager;

    public ApplicationController() {
        manager = new BookingManager();
    }

    public void addBooking(Booking booking) throws AddBookingException {
        manager.addBooking(booking);
    }
}
