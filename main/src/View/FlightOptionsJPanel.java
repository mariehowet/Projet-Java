package View;

import Controller.ApplicationController;
import Model.FlightOption;


import javax.swing.*;
import java.util.ArrayList;
import Exception.*;

public class FlightOptionsJPanel extends JPanel {
    private ApplicationController controller;
    private ListSelectionModel listSelect;
    private ArrayList<FlightOption> flightOptions;
    public FlightOptionsJPanel(int flightId) throws ConnectionException {
        controller = new ApplicationController();
        flightOptions = controller.getFlightOptions(flightId);
    }
}
