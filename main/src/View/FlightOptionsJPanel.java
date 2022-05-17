package View;

import Controller.ApplicationController;
import Model.FlightOption;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Exception.*;
import Model.FlightResearch;

public class FlightOptionsJPanel extends JPanel {
    private ApplicationController controller;
    private ListSelectionModel listSelect;
    private ArrayList<FlightOption> flightOptions;
    public FlightOptionsJPanel(FlightResearch flight) throws ConnectionException {
        controller = new ApplicationController();
        flightOptions = controller.getFlightOptions(flight);
        FlightOptionsModel model = new FlightOptionsModel(flightOptions);
        JTable flightOptionsTable = new JTable(model);
        flightOptionsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelect = flightOptionsTable.getSelectionModel();
        flightOptionsTable.setPreferredScrollableViewportSize(new Dimension(900, 100));
        this.setLayout(new FlowLayout());
        this.add(new JScrollPane(flightOptionsTable));
    }
}
