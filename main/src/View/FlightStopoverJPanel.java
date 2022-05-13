package View;

import Controller.ApplicationController;
import Model.FlightStopover;
import Model.Locality;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import Exception.*;

public class FlightStopoverJPanel extends JPanel {
    private ApplicationController controller;
    private ListSelectionModel listSelect;
    private ArrayList<FlightStopover> flightsStopover;

    public FlightStopoverJPanel(Locality departure, Locality arrival, boolean withStopover) {
        try {

            controller = new ApplicationController();
            flightsStopover = controller.getFlightsStopover(departure, arrival, withStopover);
            FlightStopoverModel model = new FlightStopoverModel(flightsStopover);
            JTable flightsStopoverTable = new JTable(model);
            flightsStopoverTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listSelect = flightsStopoverTable.getSelectionModel();
            flightsStopoverTable.setPreferredScrollableViewportSize(new Dimension(900, 100));

            this.setLayout(new FlowLayout());
            this.add(new JScrollPane(flightsStopoverTable));


        }
        catch (FlightsStopover e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch (ConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
