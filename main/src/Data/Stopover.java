package Data;

public class Stopover {
    private Flight flight;
    private Airport airport;
    private int duration;

    public Stopover(Flight flight, Airport airport, int duration) {
        this.flight = flight;
        this.airport = airport;
        this.duration = duration;
    }
}
