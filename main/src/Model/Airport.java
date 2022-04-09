package Model;

import java.util.HashSet;

public class Airport {
    private int code;
    private String name;
    private Locality locality;
    private HashSet<Flight> departureFlights;
    private HashSet<Flight> arrivalFlights;
    private HashSet<Stopover> stopovers;

    public Airport(int code, String name, Locality locality, HashSet<Flight> departureFlights, HashSet<Flight> arrivalFlights, HashSet<Stopover> stopovers) {
        this.code = code;
        this.name = name;
        this.locality = locality;
        this.departureFlights = new HashSet<Flight>();
        this.arrivalFlights = new HashSet<Flight>();
        this.stopovers = new HashSet<Stopover>();
    }
}
