package View;

import DataAccess.SingletonConnexion;

import Exception.ConnectionException;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        MainJFrame mainJFrame = new MainJFrame();
        /* ******************* connection********************************** */
        try {
            SingletonConnexion.getInstance();
        }catch (ConnectionException connectException) {
            JOptionPane.showMessageDialog(null, connectException.getMessage()); 
        }
    }
    //si Modif
    //docker container rm projet
    //docker image rm projet
    //docker build -t projet .
    //docker run --name projet -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d projet
    //sinon
    //docker start projet
    //docker stop projet
}
