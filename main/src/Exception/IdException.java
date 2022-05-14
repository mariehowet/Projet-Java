package Exception;

public class IdException extends Exception {
    @Override
    public String getMessage() {
        return "Problème pour récupérer l'id";
    }
}
