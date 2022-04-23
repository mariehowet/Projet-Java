package Exception;

import java.sql.SQLException;

public class ConnectionException extends Exception {
    private SQLException exception;

    public ConnectionException(SQLException exception) {
        this.exception = exception;
    }

    @Override
    public String getMessage() {
        return "erreur connexion à la bd";
    }
}
