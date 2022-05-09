package Exception;

public class AvailableSeatsException extends Exception{
    @Override
    public String getMessage() {
        return "Problème lors de la récupéaration des sièges disponibles";
    }
}
