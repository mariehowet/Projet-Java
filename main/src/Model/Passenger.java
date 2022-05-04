package Model;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Optional;

public class Passenger {
    private int id;
    private String lastName;
    private String firstName;
    private String initialMiddleName;
    private GregorianCalendar birthDate;
    private String email;
    private String phoneNumber;
    private String streetAndNumber;
    private String city;
    private String postCode;
    private String country;


    public Passenger(String lastName, String firstName, String initialMiddleName, GregorianCalendar birthDate, String email, String phoneNumber, String streetAndNumber, String city, String postCode, String country) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.initialMiddleName = initialMiddleName;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.streetAndNumber = streetAndNumber;
        this.city = city;
        this.postCode = postCode;
        this.country = country;
    }
}
