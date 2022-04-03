package Data;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Optional;

public class Passenger {
    private int id;
    private String lastName;
    private String firstName;
    private Optional<String> initialMiddleName;
    private GregorianCalendar birthDate;
    private String gender;
    private String email;
    private int phoneNumber;
    private String streetAndNumber;
    private HashSet<Booking> bookings;

    public Passenger(int id, String lastName, String firstName, Optional<String> initialMiddleName, GregorianCalendar birthDate, String gender, String email, int phoneNumber, String streetAndNumber, HashSet<Booking> bookings) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.initialMiddleName = initialMiddleName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.streetAndNumber = streetAndNumber;
        this.bookings = new HashSet<Booking>();
    }
}
