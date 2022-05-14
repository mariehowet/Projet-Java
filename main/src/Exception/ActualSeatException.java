package Exception;

public class ActualSeatException extends Exception {

    @Override
    public String getMessage() {
        return "Problème pour récupérer le siège actuel";
    }
}
