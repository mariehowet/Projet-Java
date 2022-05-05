package DataAccess;

import Model.Booking;

import Exception.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class BookingDBAccess implements BookingDataAccess{
    private Connection connection;

    public BookingDBAccess() throws ConnectionException {
        connection = SingletonConnexion.getInstance();
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
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
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
        return allBookings;
    }

    @Override
    public void updateBooking(Booking booking) {

    }

    @Override
    public void deleteBooking(Booking booking) {

    }
}
