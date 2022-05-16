package DataAccess;

import Model.FlightResearch;
import Model.Locality;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import Exception.*;

public class FlightsStopoverDBAccess implements FlightsStopoverDataAccess {

    private Connection connection;

    public FlightsStopoverDBAccess() throws ConnectionException {
        connection = SingletonConnection.getInstance();
        // connection.close(); throws SQLException
    }


    public ArrayList<FlightResearch> getFlightsStopover(Locality departure, Locality arrival, boolean withStopover) throws FlightsStopover, PriceException {

        String sqlInstruction =
                "select f.id, da.name as 'departure_airport', aa.name as 'arrival_airport', f.departure_date, f.expected_arrival_date, f.departure_hour, f.expected_arrival_hour, f.price " +
                        "from flight f " +
                        "inner join airport da on (f.departure_airport_id = da.id) " +
                        "inner join airport aa on (f.arrival_airport_id = aa.id) " +
                        "where " +
                        "da.city = ? and da.post_code = ? and da.country = ? " +
                        "and aa.city = ? and aa.post_code = ? and aa.country = ? " +
                        "and (" +
                        "(? and exists (select flight_id from stopover s where f.id = s.flight_id) ) OR" +
                        "(? and not exists (select flight_id from stopover s where f.id = s.flight_id))" +
                        ")";

        ArrayList<FlightResearch> flightsStopovers = new ArrayList<>();
        // traitement
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, departure.getCity());
            preparedStatement.setString(2, departure.getPostCode());
            preparedStatement.setString(3, departure.getCountry());
            preparedStatement.setString(4, arrival.getCity());
            preparedStatement.setString(5, arrival.getPostCode());
            preparedStatement.setString(6, arrival.getCountry());
            preparedStatement.setBoolean(7, withStopover);
            preparedStatement.setBoolean(8, !withStopover);


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
                flightsStopovers.add(flightResearch);
            }

        } catch (SQLException exception) {
            throw new FlightsStopover();
        } catch (PriceException exception) {
            throw new PriceException();
        }
        return flightsStopovers;
    }
}
