package Model;

import java.util.Objects;

public class Passenger {
    private int id;
    private String lastName;
    private String firstName;
    private String initialMiddleName;

    public Passenger(int id, String lastName, String firstName, String initialMiddleName) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.initialMiddleName = initialMiddleName;
    }

    public int getId() {
        return id;
    }
    public String getLastName() {
        return lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getInitialMiddleName() {
        return initialMiddleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return id == passenger.id && Objects.equals(lastName, passenger.lastName) && Objects.equals(firstName, passenger.firstName) && Objects.equals(initialMiddleName, passenger.initialMiddleName);
    }
}
