package Exception;

public class CloseDataException extends Exception {
    private String error;

    public CloseDataException(String error){
        this.error = error;
    }

    @Override
    public String getMessage() {
        return "Problème lors de la fermeture de l'accès aux données (erreur : " + error + ")";
    }
}
