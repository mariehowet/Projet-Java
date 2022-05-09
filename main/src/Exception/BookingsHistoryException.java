package Exception;

public class BookingsHistoryException extends Exception {
    public String getMessage() {
        return "erreur lecture de l'historique des réservations";
    }
}
