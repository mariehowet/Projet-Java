package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import Exception.*;
import Model.Locality;
import Model.Passenger;

import javax.swing.*;

public class GeneralDBAccess implements GeneralDataAccess {

    private Connection connection;

    public GeneralDBAccess() throws ConnectionException {
        connection = SingletonConnection.getInstance();
        // connection.close(); throws SQLException
    }



    public void closeConnection() throws CloseDataException {
        try {
            SingletonConnection.getInstance().close();
        }
        catch (SQLException e) {
            throw new CloseDataException(e.getMessage());
        }
        catch (ConnectionException connectionException) {
            JOptionPane.showMessageDialog(null, connectionException.getMessage(), "Problème", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public ArrayList<Passenger> getAllPassengers() throws PassengerException {
        String sqlInstruction = "select * from passenger";
        ArrayList<Passenger>  allPassengers = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();

            Passenger passenger;
            GregorianCalendar calendar;

            while(data.next()) {
                calendar = new GregorianCalendar();
                calendar.setTime(data.getDate("birth_date"));
                passenger = new Passenger(
                        data.getInt("id"),
                        data.getString("last_name"),
                        data.getString("first_name"),
                        data.getString("initial_middle_name"),
                        calendar,
                        data.getString("email"),
                        data.getString("phone_number"),
                        data.getString("street_and_number"),
                        data.getString("city"),
                        data.getString("post_code"),
                        data.getString("country")
                );

                allPassengers.add(passenger);
            }
            return allPassengers;

        } catch (SQLException exception) {
            throw new PassengerException();
        }
    }

    public ArrayList<Locality> getAllLocalities() throws AllLocalitiesException {
        String sqlInstruction = "select * from locality";
        ArrayList<Locality>  allLocalities = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();
            Locality locality;

            while(data.next()) {

                locality = new Locality(
                        data.getString("city"),
                        data.getString("post_code"),
                        data.getString("country")
                );

                allLocalities.add(locality);
            }
            return allLocalities;

        } catch (SQLException exception) {
            throw new AllLocalitiesException();
        }
    }


}
