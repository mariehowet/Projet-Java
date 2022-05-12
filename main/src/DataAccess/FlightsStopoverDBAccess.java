package DataAccess;

import Model.FlightStopover;
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


    public ArrayList<FlightStopover> getFlightsStopover(Locality departure, Locality arrival, boolean withStopover) throws FlightsStopover {
        // pas encore de verif sur les localitées :
        String sqlInstruction =
                "select f.id, da.name as 'departure_airport', aa.name as 'arrival_airport', f.departure_date, f.expected_arrival_date, f.departure_hour, f.expected_arrival_hour " +
                        "from flight f " +
                        "inner join airport da on (f.departure_airport_id = da.id) " +
                        "inner join airport aa on (f.arrival_airport_id = aa.id) " +
                        "where " + (withStopover?
                        "exists (select flight_id from stopover s where f.id = s.flight_id)" :
                        "not exists (select flight_id from stopover s where f.id = s.flight_id)");

        ArrayList<FlightStopover> flightsStopovers = new ArrayList<>();
        // traitement
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            ResultSet data = preparedStatement.executeQuery();
            FlightStopover flightStopover;
            GregorianCalendar departureDate;
            GregorianCalendar arrivalDate;

            while(data.next()) {
                departureDate = new GregorianCalendar();
                departureDate.setTime(data.getDate("departure_date"));
                arrivalDate = new GregorianCalendar();
                arrivalDate.setTime(data.getDate("expected_arrival_date"));

                flightStopover = new FlightStopover (
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
            throw new FlightsStopover();
        }
        return flightsStopovers;
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
