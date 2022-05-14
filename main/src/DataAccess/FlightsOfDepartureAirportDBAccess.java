package DataAccess;

import Model.Airport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import Exception.*;
import Model.FlightOfDepartureAirport;

public class FlightsOfDepartureAirportDBAccess implements FlightsOfDepartureAirportDataAccess {
    private Connection connection;

    public FlightsOfDepartureAirportDBAccess() throws ConnectionException {
        connection = SingletonConnection.getInstance();
        // connection.close(); throws SQLException
    }

    public ArrayList<FlightOfDepartureAirport> getFlightsOfDepartureAirport(Date startDate, Date endDate, int idAirport) throws FlightsOfDepartureAirportException {
        String sqlInstruction = "";

        ArrayList<FlightOfDepartureAirport> flightsOfDepartureAirport = new ArrayList<>();

        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
        System.out.println(sqlStartDate);
        System.out.println(sqlEndDate);

        // traitement
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setDate(1, sqlStartDate);
            preparedStatement.setDate(2, sqlEndDate);
            preparedStatement.setInt(8, idAirport);


            ResultSet data = preparedStatement.executeQuery();
            FlightOfDepartureAirport flightOfDepartureAirport;
            GregorianCalendar departureDate;
            GregorianCalendar arrivalDate;

            while(data.next()) {
                departureDate = new GregorianCalendar();
                departureDate.setTime(data.getDate("departure_date"));
                arrivalDate = new GregorianCalendar();
                arrivalDate.setTime(data.getDate("expected_arrival_date"));

                flightOfDepartureAirport = new FlightOfDepartureAirport (
                        data.getInt("id"),
                        data.getString("departure_airport"),
                        departureDate,
                        arrivalDate,
                        data.getInt("remainingSeats")

                );
                flightsOfDepartureAirport.add(flightOfDepartureAirport);
            }
            return flightsOfDepartureAirport;

        } catch (SQLException exception) {
            throw new FlightsOfDepartureAirportException();
        }
    }


    public ArrayList<Airport> getAllAirports() throws AllAirportsException {
        ArrayList<Airport> allAirports = new ArrayList<>();
        String sqlInstruction = "select * from airport";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();

            Airport airport;

            while(data.next()) {


                airport = new Airport(
                        data.getInt("id"),
                        data.getString("name"),
                        data.getString("city"),
                        data.getString("post_code"),
                        data.getString("country")
                );

                allAirports.add(airport);
            }
            return allAirports;

        } catch (SQLException exception) {
            throw new AllAirportsException();
        }
    }
}
