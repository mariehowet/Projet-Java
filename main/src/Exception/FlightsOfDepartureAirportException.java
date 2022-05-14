package Exception;

public class FlightsOfDepartureAirportException extends Exception {
    public String getMessage() {
        return "erreur de lecture des vols en direction de l'aéroport";
    }
}
