package DataAccess;

import Model.Booking;
import Exception.ConnectionException;


import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.GregorianCalendar;

public class BookingDBAccess implements BookingDataAccess{
    private Connection connection;

    public BookingDBAccess() throws ConnectionException {
        /* ******************* connection********************************** */
        try {
            SingletonConnexion.getInstance();
        }catch (ConnectionException connectException) {
            JOptionPane.showMessageDialog(null, connectException.getMessage());
        }
        // connection.close(); throws SQLException
    }

    @Override
    public void addBooking(Booking booking) throws ConnectionException {
        try {
            // Créer l'instruction SQL avec ? pour empêcher les injections SQL
            GregorianCalendar calendar = booking.getDate();
            java.sql.Date sqlDate = new java.sql.Date(calendar.getTimeInMillis());
            String sqlInstruction = "insert into booking (id, date, has_paid, meal_type, real_price, passenger_id, seat_id, flight_id) values (?,?,?,?,?,?,?,?)";
            // Créer le PreparedStatement à partir de cette instruction SQL
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            // Remplacer les ? via les filtres (settors)
            preparedStatement.setInt(1, booking.getId());
            preparedStatement.setDate(2, sqlDate);
            preparedStatement.setBoolean(3,booking.getHasPaid());
            preparedStatement.setString(4, booking.getMealType());
            preparedStatement.setDouble(5, booking.getRealPrice());
            preparedStatement.setInt(6, booking.getPassengerID());
            preparedStatement.setInt(7, booking.getSeatID());
            preparedStatement.setInt(8, booking.getFlightID());
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
            throw new ConnectionException();
        }
    }

    @Override
    public Booking[] getAllBookings() {
        return null;
    }

    @Override
    public void updateBooking(Booking booking) {

    }

    @Override
    public void deleteBooking(Booking booking) {

    }
}
