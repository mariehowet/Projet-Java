package DataAccess;

import Model.Locality;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import Exception.FlightsStopoverException;
import Exception.PriceException;
import Exception.ConnectionException;
import Exception.FlightStopoversException;
import Model.Stopover;
import Model.FlightResearch;


public class FlightsStopoverDBAccess implements FlightsStopoverDataAccess {

    private Connection connection;

    public FlightsStopoverDBAccess() throws ConnectionException {
        connection = SingletonConnection.getInstance();
    }

    public ArrayList<FlightResearch> getFlightsStopover(Locality departure, Locality arrival, boolean withStopover) throws FlightsStopoverException {
        String sqlInstruction;

        if(withStopover) {
            sqlInstruction = "select f.id, da.name as 'departure_airport', aa.name as 'arrival_airport', f.departure_date, f.expected_arrival_date, f.departure_hour, f.expected_arrival_hour " +
                    "from flight f " +
                    "inner join airport da on (f.departure_airport_id = da.id) " +
                    "inner join airport aa on (f.arrival_airport_id = aa.id) " +
                    "where " +
                    "da.city = ? and da.post_code = ? and da.country = ? " +
                    "and aa.city = ? and aa.post_code = ? and aa.country = ? " +
                    "and exists (select flight_id from stopover s where f.id = s.flight_id)";
        } else {
            sqlInstruction = "select f.id, da.name as 'departure_airport', aa.name as 'arrival_airport', f.departure_date, f.expected_arrival_date, f.departure_hour, f.expected_arrival_hour " +
                    "from flight f " +
                    "inner join airport da on (f.departure_airport_id = da.id) " +
                    "inner join airport aa on (f.arrival_airport_id = aa.id) " +
                    "where " +
                    "da.city = ? and da.post_code = ? and da.country = ? " +
                    "and aa.city = ? and aa.post_code = ? and aa.country = ? " +
                    "and not exists (select flight_id from stopover s where f.id = s.flight_id)";
        }

        ArrayList<FlightResearch> flightsStopovers = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, departure.getCity());
            preparedStatement.setString(2, departure.getPostCode());
            preparedStatement.setString(3, departure.getCountry());
            preparedStatement.setString(4, arrival.getCity());
            preparedStatement.setString(5, arrival.getPostCode());
            preparedStatement.setString(6, arrival.getCountry());

            ResultSet data = preparedStatement.executeQuery();
            FlightResearch flightStopover;
            GregorianCalendar departureDate;
            GregorianCalendar arrivalDate;
            while(data.next()) {
                departureDate = new GregorianCalendar();
                departureDate.setTime(data.getDate("departure_date"));
                arrivalDate = new GregorianCalendar();
                arrivalDate.setTime(data.getDate("expected_arrival_date"));

                flightStopover = new FlightResearch (
                        data.getInt("id"),
                        data.getString("departure_airport"),
                        data.getString("arrival_airport"),
                        departureDate,
                        arrivalDate,
                        data.getString("departure_hour"),
                        data.getString("expected_arrival_hour")
                );
                flightsStopovers.add(flightStopover);
            }

        } catch (SQLException exception) {
            throw new FlightsStopoverException();
        }
        return flightsStopovers;
    }

    public ArrayList<Stopover> getStopoversOfFlight(int flightID) throws FlightStopoversException {
        String sqlInstruction =
                "select a.name, duration from stopover " +
                "inner join airport a on stopover.airport_id = a.id " +
                "where flight_id = ?";

        ArrayList<Stopover> stopovers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, flightID);

            ResultSet data = preparedStatement.executeQuery();
            Stopover stopover;

            while(data.next()) {

                stopover = new Stopover(
                        data.getString("name"),
                        data.getInt("duration")
                );

                stopovers.add(stopover);
            }

        } catch (SQLException exception) {
            throw new FlightStopoversException();
        }
        return stopovers;
    }
}
