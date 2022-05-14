package Exception;

public class DatesException extends Exception {
    public String getMessage() {
        return "erreur la date de début est inférieur à la date de fin";
    }
}
