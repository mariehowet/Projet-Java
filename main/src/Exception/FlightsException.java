package Exception;

public class FlightsException extends Exception {
    @Override
    public String getMessage() {
        return " erreur lors de la récupération des vols";
    }
}
