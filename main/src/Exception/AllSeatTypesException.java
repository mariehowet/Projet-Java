package Exception;

public class AllSeatTypesException extends Exception{
    @Override
    public String getMessage() {
        return "Problème pour récupérer la liste des types de sièges";
    }
}
