package Exception;

public class ConnectionException extends Exception {

    @Override
    public String getMessage() {
        return "erreur connexion à la bd";
    }
}
