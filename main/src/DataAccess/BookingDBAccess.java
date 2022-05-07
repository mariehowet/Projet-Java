package DataAccess;

import Model.Booking;

import Exception.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class BookingDBAccess implements BookingDataAccess{
    private Connection connection;

    public BookingDBAccess() throws ConnectionException {
        connection = SingletonConnection.getInstance();
        // connection.close(); throws SQLException
    }

    @Override
    public void addBooking(Booking booking) throws AddBookingException {

        String sqlInstruction = "insert into booking (date_booking, has_paid, meal_type, real_price, flight_id, seat_id, passenger_id) values (?,?,?,?,?,?,?)";
        // Créer l'instruction SQL avec ? pour empêcher les injections SQL
        GregorianCalendar calendar = booking.getDate();
       java.sql.Date sqlDate = new Date(calendar.getTimeInMillis());


        // Créer le PreparedStatement à partir de cette instruction SQL

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction, PreparedStatement.RETURN_GENERATED_KEYS);
            // Remplacer les ? via les filtres (settors)

            preparedStatement.setDate(1, sqlDate);
            preparedStatement.setInt(2,(booking.getHasPaid() ? 1: 0));
            preparedStatement.setString(3, booking.getMealType());
            preparedStatement.setDouble(4, booking.getRealPrice());
            preparedStatement.setInt(5, booking.getPassengerID());
            preparedStatement.setInt(6, booking.getSeatID());
            preparedStatement.setInt(7, booking.getFlightID());
            // Exécuter + Récupérer le nombre de lignes modifiées
            preparedStatement.executeUpdate();

            ResultSet data = preparedStatement.getGeneratedKeys();
            data.next();
            booking.setId(data.getInt(1));

            if(booking.getLuggageWeight() != null) {
                sqlInstruction = "update booking set luggage_weight = ? where id = ? ";
                preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setInt(1,booking.getLuggageWeight());
                preparedStatement.setInt(2, booking.getId());
                preparedStatement.executeUpdate();
            }

            if(booking.getCompanyName() != null) {
                sqlInstruction = "update booking set company_name = ? where id = ? ";
                preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setString(1,booking.getCompanyName());
                preparedStatement.setInt(2, booking.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException exception) {
            throw new AddBookingException();
        }
    }

    @Override
    public ArrayList<Booking> getAllBookings() throws AllBookingsException {
        String sqlInstruction = "select * from booking";
        ArrayList<Booking> allBookings = new ArrayList<>();
        // traitement
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();
            Booking booking;
            GregorianCalendar calendar;
            Integer luggageWeight;
            String companyName;

            while(data.next()) {
                calendar = new GregorianCalendar();
                calendar.setTime(data.getDate("date_booking"));
               booking = new Booking (
                        data.getInt("id"),
                        calendar,
                        data.getBoolean("has_paid"),
                        data.getString("meal_type"),
                        data.getDouble("real_price"),
                        data.getInt("flight_id"),
                        data.getInt("seat_id"),
                        data.getInt("passenger_id")
                );
                luggageWeight = data.getInt("luggage_weight");
                if(!data.wasNull())
                    booking.setLuggageWeight(luggageWeight);

                companyName = data.getString("company_name");
                if(!data.wasNull())
                    booking.setCompanyName(companyName);

                allBookings.add(booking);
            }

        } catch (SQLException exception) {
            throw new AllBookingsException();
        }
        return allBookings;
    }

    public void closeConnection() throws CloseDataException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new CloseDataException(e.getMessage());
        }
    }

    @Override
    public void updateBooking(Booking booking) throws UpdateException{
        String sqlInstruction = "update from booking where id = ? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, booking.getId());
        }
        catch (SQLException exception) {
            throw new UpdateException();
        }
    }

    @Override
    public void deleteBooking(Booking booking) {

    }
}
