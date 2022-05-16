package Exception;

public class DatesNullException extends Exception {
    String wrongDate;
    public DatesNullException(String wrongDate) {
        this.wrongDate = wrongDate;
    }
    public String getMessage() {
        return "erreur de la date de " + wrongDate + " n'est pas valide";
    }

}
