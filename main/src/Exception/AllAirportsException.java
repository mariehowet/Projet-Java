package Exception;

public class AllAirportsException extends Exception {
    public String getMessage() {
        return "erreur lecture des aéroports";
    }
}
