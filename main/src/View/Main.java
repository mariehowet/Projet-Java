package View;

import DataAccess.BookingDBAccess;
import DataAccess.BookingDataAccess;
import DataAccess.SingletonConnexion;

import Exception.ConnectionException;
import Model.Booking;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {
        MainJFrame mainJFrame = new MainJFrame();
        GregorianCalendar date = new GregorianCalendar();

        try {
            BookingDBAccess bda = new BookingDBAccess();
           // bda.addBooking(new Booking( date, true," hallal", 750.5,1,1,1));
        } catch(ConnectionException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
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
}
