package Exception;

public class AllBookingsException extends Throwable {

    public String getMessage() {
        return "erreur lecture des réservations";
    }
}
