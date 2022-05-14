package Exception;

public class FlightPriceException extends Exception{
    @Override
    public String getMessage() {
        return "Problème pour récupérer le prix du vol";
    }
}
