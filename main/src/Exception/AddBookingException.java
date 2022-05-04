package Exception;

public class AddBookingException extends Exception{
    @Override
    public String getMessage() {
        return "Erreur lors de l'ajout d'une réservation";
    }
}
