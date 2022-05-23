package Exception;

public class FlightStopoversException extends Exception {
    @Override
    public String getMessage() {
        return "Problème pour récupérer la liste des escales du vol";
    }
}
