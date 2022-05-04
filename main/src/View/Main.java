package View;

import DataAccess.BookingDBAccess;


import Exception.*;
import DataAccess.*;
import Model.Booking;

import javax.swing.*;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {
        MainJFrame mainJFrame = new MainJFrame();
        /*
        GregorianCalendar date = new GregorianCalendar(2022, 7, 25);

        try {
            BookingDBAccess bda = new BookingDBAccess();
            Booking booking = new Booking(date, true, null, null, "poulet", 750.0, 1, 1, 1);
            bda.addBooking(booking);
        } catch (AddBookingException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        } catch (ConnectionException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
        */



    }
}
    //si Modif
    //docker container rm projet
    //docker image rm projet
    //docker build -t projet .
    //docker run --name projet -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d projet
    //sinon
    //docker start projet
    //docker stop projet

