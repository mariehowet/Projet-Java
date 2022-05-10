package Exception;

public class FlightsStopover extends Exception {
    @Override
    public String getMessage() {
        return "Problème pour récupérer la liste des vols";
    }
}
