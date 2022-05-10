package Exception;

public class AllLocalitiesException extends Exception {
    @Override
    public String getMessage() {
        return "Problème pouyr récupérer la liste des localitées";
    }
}
