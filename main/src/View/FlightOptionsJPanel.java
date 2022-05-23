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
    private ArrayList<FlightOption> flightOptions;
    private JLabel title;
    private FlightOptionsModel model;
    private JTable flightOptionsTable;

    public FlightOptionsJPanel(FlightResearch flight) throws ConnectionException {
        setLayout(new BorderLayout());

        controller = new ApplicationController();
        flightOptions = controller.getFlightOptions(flight);
        model = new FlightOptionsModel(flightOptions);
        flightOptionsTable = new JTable(model);

        flightOptionsTable.setPreferredScrollableViewportSize(new Dimension(900, 100));

        title = new JLabel("<html><h2 style='margin: 30px 0 15px 0'>Options disponibles pour ce vol</h2></html>", SwingConstants.CENTER);

        add(title, BorderLayout.NORTH);
        add(new JScrollPane(flightOptionsTable),BorderLayout.CENTER);
    }
}
