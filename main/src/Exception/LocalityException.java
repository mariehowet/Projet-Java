package Exception;

public class LocalityException extends Exception {
    @Override
    public String getMessage() {
        return "Problème pour récupérer la localité";
    }
}
