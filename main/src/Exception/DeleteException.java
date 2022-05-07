package Exception;

public class DeleteException extends Exception {
    @Override
    public String getMessage() {
        return " erreur à la suppression";
    }
}
