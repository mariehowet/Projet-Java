package DataAccess;

import java.sql.SQLException;

import Exception.*;

import javax.swing.*;

public class GeneralDBAccess implements GeneralDataAccess {

    public void closeConnection() throws CloseDataException {
        try {
            SingletonConnection.getInstance().close();
        }
        catch (SQLException e) {
            throw new CloseDataException(e.getMessage());
        }
        catch (ConnectionException connectionException) {
            JOptionPane.showMessageDialog(null, connectionException.getMessage(), "Problème", JOptionPane.WARNING_MESSAGE);
        }
    }


}
