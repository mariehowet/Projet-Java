package Exception;

public class AllFlightsException extends Exception{
    @Override
    public String getMessage() {
        return " Problème lors de la récuparation de tous les vols";
    }
}
