package Exception;

public class SeatNumberException extends Exception{
    @Override
    public String getMessage() {
        return "Le numéro du siège ne peut pas être négatif !";
    }
}
