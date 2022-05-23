package Exception;

public class ActualPassengerException extends Exception{

    @Override
    public String getMessage() {
        return "Problème pour récurpérer le passager actuel ";
    }
}
