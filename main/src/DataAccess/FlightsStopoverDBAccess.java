package DataAccess;

import Model.FlightStopover;
import Model.Locality;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import Exception.FlightsStopover;
import Exception.PriceException;
import Exception.ConnectionException;
import View.FlightsStopoverModel;


public class FlightsStopoverDBAccess implements FlightsStopoverDataAccess {

    private Connection connection;

    public FlightsStopoverDBAccess() throws ConnectionException {
        connection = SingletonConnection.getInstance();
    }

    public ArrayList<FlightStopover> getFlightsStopover(Locality departure, Locality arrival, boolean withStopover) throws FlightsStopover, PriceException {
        String sqlInstruction;

        if(withStopover) {
            sqlInstruction = "select f.id, da.name as 'departure_airport', aa.name as 'arrival_airport', f.departure_date, f.expected_arrival_date, f.departure_hour, f.expected_arrival_hour, sa.name as 'stopover_airport', s.duration as 'duration'" +
                    "from flight f " +
                    "inner join airport da on (f.departure_airport_id = da.id) " +
                    "inner join airport aa on (f.arrival_airport_id = aa.id) " +
                    "inner join stopover s on (f.id = s.flight_id)" +
                    "inner join airport sa on (s.airport_id = sa.id)" +
                    "where " +
                    "da.city = ? and da.post_code = ? and da.country = ? " +
                    "and aa.city = ? and aa.post_code = ? and aa.country = ? " +
                    "and exists (select s.flight_id from stopover s where f.id = s.flight_id)";
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

        ArrayList<FlightStopover> flightsStopovers = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, departure.getCity());
            preparedStatement.setString(2, departure.getPostCode());
            preparedStatement.setString(3, departure.getCountry());
            preparedStatement.setString(4, arrival.getCity());
            preparedStatement.setString(5, arrival.getPostCode());
            preparedStatement.setString(6, arrival.getCountry());

            ResultSet data = preparedStatement.executeQuery();
            FlightStopover flightStopover;
            GregorianCalendar departureDate;
            GregorianCalendar arrivalDate;
            String stopoverAirport;
            Integer duration;
            while(data.next()) {
                departureDate = new GregorianCalendar();
                departureDate.setTime(data.getDate("departure_date"));
                arrivalDate = new GregorianCalendar();
                arrivalDate.setTime(data.getDate("expected_arrival_date"));

                flightStopover = new FlightStopover(
                        data.getInt("id"),
                        data.getString("departure_airport"),
                        data.getString("arrival_airport"),
                        departureDate,
                        arrivalDate,
                        data.getString("departure_hour"),
                        data.getString("expected_arrival_hour")
                );

                if(withStopover) {
                    stopoverAirport = data.getString("stopover_airport");
                    if (!data.wasNull())
                        flightStopover.setStopoverAirportName(stopoverAirport);
                    duration = data.getInt("duration");
                    if (!data.wasNull())
                        flightStopover.setDurationStopover(duration);
                }
                flightsStopovers.add(flightStopover);
            }

        } catch (SQLException exception) {
            throw new FlightsStopover();
        }
        return flightsStopovers;
    }
}
