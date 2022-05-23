package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import Exception.ConnectionException;

public class SingletonConnection {
    private static Connection uniqueConnexion;

    public static Connection getInstance() throws ConnectionException { // il faut créer une classe qui extends Exception
        if (uniqueConnexion == null) {
            try {
            uniqueConnexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/marvinairline", "root", "root");
            }
            catch (SQLException exception) {
                throw new ConnectionException();
            }
        }
        return uniqueConnexion;
    }
}
