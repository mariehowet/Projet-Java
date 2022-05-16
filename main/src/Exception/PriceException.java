package Exception;

public class PriceException extends Exception{
    @Override
    public String getMessage() {
        return "le prix ne peut pas être négatif !";
    }
}
