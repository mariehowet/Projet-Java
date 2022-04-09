package Model;

import java.util.HashSet;

public class Locality {
    private String city;
    private int postCode;
    private String country;
    private HashSet<Airport> airports;
    private HashSet<Passenger> passengers;

    public Locality(String city, int postCode, String country, HashSet<Airport> airports, HashSet<Passenger> passengers) {
        this.city = city;
        this.postCode = postCode;
        this.country = country;
        this.airports = new HashSet<Airport>();
        this.passengers = new HashSet<Passenger>();
    }
}
