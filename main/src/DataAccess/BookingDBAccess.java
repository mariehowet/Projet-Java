package DataAccess;

import Model.*;

import Exception.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class BookingDBAccess implements BookingDataAccess {
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
            preparedStatement.setBoolean(2,booking.getHasPaid());
            preparedStatement.setString(3, booking.getMealType());
            preparedStatement.setDouble(4, booking.getRealPrice());
            preparedStatement.setInt(5, booking.getFlightID());
            preparedStatement.setInt(6, booking.getSeatID());
            preparedStatement.setInt(7, booking.getPassengerID());
            // Exécuter + Récupérer le nombre de lignes modifiées
            preparedStatement.executeUpdate();

            ResultSet data = preparedStatement.getGeneratedKeys();
            data.next();
            booking.setId(data.getInt(1));

            if(booking.getLuggageWeight() != null) {
                sqlInstruction = "update booking set luggage_weight = ? where id = ? ";
                preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setString(1,booking.getLuggageWeight());
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

            connection.setAutoCommit(true);
        } catch (SQLException exception) {
            throw new AddBookingException();
        }
    }

    @Override
    public ArrayList<Booking> getAllBookings() throws AllBookingsException, PriceException {
        String sqlInstruction = "select * from booking";
        ArrayList<Booking> allBookings = new ArrayList<>();
        // traitement
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();
            Booking booking;
            GregorianCalendar calendar;
            String luggageWeight;
            String companyName;

            while(data.next()) {
                calendar = new GregorianCalendar();
                calendar.setTime(data.getDate("date_booking"));

                booking = new Booking(
                        data.getInt("id"),
                        calendar,
                        data.getBoolean("has_paid"),
                        data.getString("meal_type"),
                        data.getDouble("real_price"),
                        data.getInt("flight_id"),
                        data.getInt("seat_id"),
                        data.getInt("passenger_id")
                );

                luggageWeight = data.getString("luggage_weight");
                if(!data.wasNull())
                    booking.setLuggageWeight(luggageWeight);

                companyName = data.getString("company_name");
                if(!data.wasNull())
                    booking.setCompanyName(companyName);

                allBookings.add(booking);
            }

        } catch (SQLException exception) {
            throw new AllBookingsException();
        }catch (PriceException exception) {
            throw new PriceException();
        }
        return allBookings;
    }


    @Override
    public void updateBooking(int id, GregorianCalendar date, Boolean hasPaid, String luggageWeight, String companyName, String mealType, Double realPrice, int seatID) throws UpdateException{
        String sqlInstruction = "update booking set date_booking = ?, has_paid = ?, luggage_weight = ?, company_name = ?, meal_type = ?,real_price = ?,  seat_id = ? where id = ? ";
        GregorianCalendar calendar = date;
        java.sql.Date sqlDate = new Date(calendar.getTimeInMillis());

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setDate(1, sqlDate);
            preparedStatement.setBoolean(2,hasPaid);
            preparedStatement.setString(3,luggageWeight);
            preparedStatement.setString(4, companyName);
            preparedStatement.setString(5, mealType);
            preparedStatement.setDouble(6, realPrice);
            preparedStatement.setInt(7,seatID);
            preparedStatement.setInt(8, id);
            preparedStatement.executeUpdate();

            connection.setAutoCommit(true);
        }
        catch (SQLException exception) {
            throw new UpdateException();
        }
    }

    @Override
    public void deleteBooking(Booking booking) throws DeleteException {
        String sqlInstruction = "delete from booking where id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, booking.getId());
            preparedStatement.executeUpdate();

            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DeleteException();
        }


    }

    public ArrayList<Seat> getAvailableSeats(String seatType, int flightID) throws AvailableSeatsException, SeatNumberException{
        String sqlInstruction ="select s.id, s.number, s.column_letter from seat s inner join airplane a on(s.airplane_id = a.id)  " +
                "inner join seat_type st on(st.name = s.seat_type)  " +
                "inner join flight f on (f.airplane_id = s.airplane_id)  " +
                "where st.name = ? and f.id = ? and s.id not in (select seat_id from booking) ";
                ArrayList<Seat> availableSeats = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, seatType);
            preparedStatement.setInt(2, flightID);
            ResultSet data = preparedStatement.executeQuery();
            Seat seat;

            while(data.next()) {
                seat = new Seat(
                        data.getInt("id"),
                        data.getInt("number"),
                        data.getString("column_letter")
                );
                availableSeats.add(seat);
            }
            return availableSeats;

        } catch (SQLException exception) {
            throw new AvailableSeatsException();
        } catch (SeatNumberException exception) {
            throw new SeatNumberException();
        }
    }

    @Override
    public ArrayList<SeatType> getAllSeatTypes() throws SeatTypeException, PriceException {
        String sqlInstruction = "select * from seat_type";
        ArrayList<SeatType>  allSeatTypes = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();
            SeatType seatType;

            while(data.next()) {
                seatType = new SeatType(
                        data.getString("name"),
                        data.getInt("additional_price")
                );

                allSeatTypes.add(seatType);
            }
            return allSeatTypes;

        } catch (SQLException exception) {
            throw new SeatTypeException();
        } catch (PriceException exception) {
            throw new PriceException();
        }
    }

    public ArrayList<Flight> getAllFlights() throws AllFlightsException, PriceException { // changer prendre que ce dont j'ai besoin
        String sqlInstruction = "select * from flight";
        ArrayList<Flight>  allFlights = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();
            Flight flight;
            GregorianCalendar departureDate;
            GregorianCalendar arrivalDate;

            while(data.next()) {
                departureDate = new GregorianCalendar();
                arrivalDate = new GregorianCalendar();

                departureDate.setTime( data.getDate("departure_date"));
                arrivalDate.setTime( data.getDate("expected_arrival_date"));
                flight = new Flight(
                        data.getInt("id"),
                       departureDate,
                        data.getString("departure_hour"),
                        arrivalDate,
                        data.getString("expected_arrival_hour"),
                        data.getDouble("price"),
                        data.getInt("airplane_id"),
                        data.getInt("departure_airport_id"),
                        data.getInt("arrival_airport_id")
                );

                allFlights.add(flight);
            }
            return allFlights;

        } catch (SQLException exception) {
            throw new AllFlightsException();
        } catch (PriceException exception) {
            throw new PriceException();
        }
    }

    public Double getFlightPrice(int flightID) throws FlightPriceException{
        String sqlInstruction = "select price from flight where id = ?";
        Double price = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, flightID);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                price = data.getDouble("price");
            }
            return price;
        } catch (SQLException e) {
           throw new FlightPriceException();
        }
    }

    public String getSeatTypeName(int seatID) throws SeatTypeNameException{
        String sqlInstruction = "select seat_type from seat where id = ?";
        String seatType = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, seatID);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                seatType = data.getString("seat_type");
            }
            return seatType;
        } catch (SQLException e) {
            throw new SeatTypeNameException();
        }
    }

    public Seat getActualSeat (int seatID) throws ActualSeatException, SeatNumberException{
        String sqlInstruction = "select id, number, column_letter from seat where id = ?";
        Seat actualSeat = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, seatID);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                actualSeat = new Seat (data.getInt("id"),
                        data.getInt("number"),
                        data.getString("column_letter"));
            }
            return actualSeat;
        } catch (SQLException e) {
            throw new ActualSeatException();
        }catch (SeatNumberException e) {
            throw  new SeatNumberException();
        }
    }

}
