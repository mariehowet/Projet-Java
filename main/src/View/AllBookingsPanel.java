package View;

import Controller.ApplicationController;

import javax.swing.*;
import Exception.*;

public class AllBookingsPanel extends JPanel {
    public AllBookingsPanel() {
        ApplicationController test = new ApplicationController();
        try {
            AllBookingsModel model = new AllBookingsModel(test.getAllBookings());
        }
        catch (AllBookingsException e) {
            System.out.println("erreur");
        }
    }
}
