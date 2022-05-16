package DataAccess;

import Model.FlightOfDepartureAirport;
import Model.FlightResearch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import Exception.*;
import Model.Locality;

public class FindFlightsDBAccess implements FindFlightsDataAccess {

    private Connection connection;

    public FindFlightsDBAccess() throws ConnectionException {
        connection = SingletonConnection.getInstance();
        // connection.close(); throws SQLException
    }

    public ArrayList<FlightResearch> getFlights(Locality departure, Locality arrival, Date startDate, Date endDate) throws FlightsException, PriceException {
        ArrayList<FlightResearch> flights = new ArrayList<>();
        String sqlInstruction =
                "select f.id, da.name as 'departure_airport', aa.name as 'arrival_airport', f.departure_date, f.expected_arrival_date, f.departure_hour, f.expected_arrival_hour, f.price " +
                "from flight f " +
                "inner join airport da on (f.departure_airport_id = da.id) " +
                "inner join airport aa on (f.arrival_airport_id = aa.id) " +
                "where " +
                "f.departure_date between ? and ? " +
                "and da.city = ? and da.post_code = ? and da.country = ? " +
                "and aa.city = ? and aa.post_code = ? and aa.country = ?;";

        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
        System.out.println(sqlStartDate);
        System.out.println(sqlEndDate);

        // traitement
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setDate(1, sqlStartDate);
            preparedStatement.setDate(2, sqlEndDate);
            preparedStatement.setString(3, departure.getCity());
            preparedStatement.setString(4, departure.getPostCode());
            preparedStatement.setString(5, departure.getCountry());
            preparedStatement.setString(6, arrival.getCity());
            preparedStatement.setString(7, arrival.getPostCode());
            preparedStatement.setString(8, arrival.getCountry());

            ResultSet data = preparedStatement.executeQuery();

            FlightResearch flightResearch;
            GregorianCalendar departureDate;
            GregorianCalendar arrivalDate;

            while(data.next()) {
                departureDate = new GregorianCalendar();
                departureDate.setTime(data.getDate("departure_date"));
                arrivalDate = new GregorianCalendar();
                arrivalDate.setTime(data.getDate("expected_arrival_date"));

                flightResearch = new FlightResearch(
                        data.getInt("id"),
                        data.getString("departure_airport"),
                        data.getString("arrival_airport"),
                        departureDate,
                        arrivalDate,
                        data.getString("departure_hour"),
                        data.getString("expected_arrival_hour"),
                        data.getDouble("price")
                );
                flights.add(flightResearch);
            }
            return flights;

        } catch (SQLException exception) {
            throw new FlightsException();
        } catch (PriceException exception) {
            throw new PriceException();
        }

    }
}
