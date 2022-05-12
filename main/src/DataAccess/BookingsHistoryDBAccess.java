package DataAccess;

import Model.Passenger;
import Model.PassengerBooking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import Exception.*;

public class BookingsHistoryDBAccess implements BookingsHistoryDataAccess {

    private Connection connection;

    public BookingsHistoryDBAccess() throws ConnectionException {
        connection = SingletonConnection.getInstance();
        // connection.close(); throws SQLException
    }

    @Override
    public ArrayList<PassengerBooking> getBookingsHistory(int idPassenger) throws BookingsHistoryException {
        String sqlInstruction =
                "SELECT b.id, b.date_booking, b.real_price, b.flight_id, f.departure_date , da.name as 'departure_airport', aa.name as 'arrival_airport', s.seat_type " +
                        "FROM booking b " +
                        "inner join flight f on (b.flight_id = f.id) " +
                        "inner join airport da on (f.departure_airport_id = da.id) " +
                        "inner join airport aa on (f.arrival_airport_id = aa.id) " +
                        "inner join seat s on (b.seat_id = s.id)" +
                        "where passenger_id = ?";

        ArrayList<PassengerBooking> bookingsHistory = new ArrayList<>();
        // traitement
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, idPassenger);

            ResultSet data = preparedStatement.executeQuery();
            PassengerBooking passengerBooking;
            GregorianCalendar dateBooking;
            GregorianCalendar departureDate;

            while(data.next()) {
                dateBooking = new GregorianCalendar();
                dateBooking.setTime(data.getDate("date_booking"));
                departureDate = new GregorianCalendar();
                departureDate.setTime(data.getDate("departure_date"));

                passengerBooking= new PassengerBooking (
                        data.getInt("id"),
                        dateBooking,
                        data.getInt("flight_id"),
                        data.getString("departure_airport"),
                        data.getString("arrival_airport"),
                        departureDate,
                        data.getString("seat_type"),
                        data.getDouble("real_price")

                );
                bookingsHistory.add(passengerBooking);
            }

        } catch (SQLException exception) {
            throw new BookingsHistoryException();
        }
        return bookingsHistory;
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

}
