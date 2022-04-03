package Data;

import java.util.HashSet;

public class Airplane {
    private int id;
    private String airplaneType;
    private int nbSeats;
    private HashSet<Flight> flights;

    public Airplane(int id, String airplaneType, int nbSeats, HashSet<Flight> flights) {
        this.id = id;
        this.airplaneType = airplaneType;
        this.nbSeats = nbSeats;
        this.flights = new HashSet<Flight>();
    }
}
