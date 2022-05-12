package DataAccess;

import java.sql.SQLException;
import Exception.*;

public interface GeneralDataAccess {
    public void closeConnection() throws CloseDataException;
}
