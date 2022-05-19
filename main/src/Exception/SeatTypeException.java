package Exception;

public class SeatTypeException extends Exception{
    @Override
    public String getMessage() {
        return "Problème pour récupérer le type de siège pour un siège donnée";
    }
}
