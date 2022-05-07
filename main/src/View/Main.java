package View;

import DataAccess.BookingDBAccess;


import Exception.*;
import DataAccess.*;
import Model.Booking;

import javax.swing.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) throws ConnectionException {
        MainJFrame mainJFrame = new MainJFrame();

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

