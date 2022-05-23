package Exception;

public class FlightsStopoverException extends Exception {
    @Override
    public String getMessage() {
        return "Problème pour récupérer la liste des vols";
    }
}
