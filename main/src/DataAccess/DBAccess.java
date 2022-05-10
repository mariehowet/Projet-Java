package DataAccess;

import Model.*;

import Exception.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DBAccess implements DataAccess {
    private Connection connection;

    public DBAccess() throws ConnectionException {
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

            connection.setAutoCommit(true);
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
    public void updateBooking(int id, GregorianCalendar date, Boolean hasPaid, Integer luggageWeight, String companyName, String mealType, Double realPrice, int seatID) throws UpdateException{
        String sqlInstruction = "update booking set date_booking = ?, has_paid = ?, luggage_weight = ?, company_name = ?, meal_type = ?,real_price = ?,  seat_id = ? where id = ? ";
        GregorianCalendar calendar = date;
        java.sql.Date sqlDate = new Date(calendar.getTimeInMillis());
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setDate(1, sqlDate);
            preparedStatement.setInt(2,(hasPaid ? 1: 0));
            preparedStatement.setInt(3,luggageWeight);
            preparedStatement.setString(4, companyName);
            preparedStatement.setString(5, mealType);
            preparedStatement.setDouble(6, realPrice);
            preparedStatement.setInt(7,seatID);
            preparedStatement.setInt(8, id);
            preparedStatement.executeUpdate();

            connection.setAutoCommit(true);
        }
        catch (SQLException exception) {
            System.out.println(exception.getMessage());
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

    @Override
    public ArrayList<SeatType> getAllSeatTypes() throws SeatTypeException {
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
        }
    }

    public ArrayList<Flight> getAllFlights() throws AllFlightsException {
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
        }
    }

    public ArrayList<Seat> getAvailableSeats(String seatType) throws AvailableSeatsException {
        String sqlInstruction = "select * from seat where seat_type = ?"; // changer requete sql pour trouver tous les vols dispo
        ArrayList<Seat>  availableSeats = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, seatType);
            ResultSet data = preparedStatement.executeQuery();
            Seat seat;

            while(data.next()) {
                seat = new Seat(
                        data.getInt("id"),
                        data.getInt("number"),
                        data.getString("column_letter"),
                        data.getString("seat_type"),
                        data.getInt("airplane_id")
                );
                availableSeats.add(seat);
            }
            return availableSeats;

        } catch (SQLException exception) {
            throw new AvailableSeatsException();
        }
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
