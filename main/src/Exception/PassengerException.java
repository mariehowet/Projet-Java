package Exception;

public class PassengerException extends Exception{
    @Override
    public String getMessage() {
        return "Problème pouyr récupérer la liste des passagers";
    }
}
