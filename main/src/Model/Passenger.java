package Model;

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
}
