package Model;

public class Stopover {
    private String airport;
    private int duration;

    public Stopover(String airport, int duration) {
        this.airport = airport;
        this.duration = duration;
    }

    public String getAirport() {
        return airport;
    }

    public int getDuration() {
        return duration;
    }
}
