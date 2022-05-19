package DataAccess;

import Model.Airport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import Exception.ConnectionException;
import Exception.AllAirportsException;
import Exception.FlightsOfDepartureAirportException;
import Model.FlightOfDepartureAirport;

public class FlightsOfDepartureAirportDBAccess implements FlightsOfDepartureAirportDataAccess {
    private Connection connection;

    public FlightsOfDepartureAirportDBAccess() throws ConnectionException {
        connection = SingletonConnection.getInstance();
    }

    public ArrayList<FlightOfDepartureAirport> getFlightsOfDepartureAirport(Date startDate, Date endDate, int idAirport) throws FlightsOfDepartureAirportException {
        String sqlInstruction =
                "select f.id, aa.name as 'arrival_airport', f.departure_date, f.expected_arrival_date, " +
                "((select count(*) from seat inner join airplane a1 on seat.airplane_id = a1.id " +
                "where a1.id = a.id) - count(*)) as 'remaining_seats' " +
                "from flight f " +
                    "inner join airport aa on (f.arrival_airport_id = aa.id) " +
                    "inner join airplane a on (f.airplane_id = a.id) " +
                    "inner join booking b on f.id = b.flight_id " +
                "where f.departure_airport_id = ? " +
                        "and f.departure_date between ? and ? " +
                "group by f.id, aa.name, f.departure_date, f.expected_arrival_date";

        ArrayList<FlightOfDepartureAirport> flightsOfDepartureAirport = new ArrayList<>();

        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, idAirport);
            preparedStatement.setDate(2, sqlStartDate);
            preparedStatement.setDate(3, sqlEndDate);

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
                        data.getString("arrival_airport"),
                        departureDate,
                        arrivalDate,
                        data.getInt("remaining_seats")

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
