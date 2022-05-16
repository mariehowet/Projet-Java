package View;

import Controller.ApplicationController;
import Model.FlightResearch;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

import Exception.*;
import Model.Locality;

public class FindFlightsJPanel extends JPanel {
    private ApplicationController controller;
    private ListSelectionModel listSelect;
    private ArrayList<FlightResearch> flightResearches;
    public FindFlightsJPanel(Locality departure, Locality arrival, Date startDate, Date endDate) {

        try {

            controller = new ApplicationController();
            flightResearches = controller.getFlights(departure, arrival, startDate, endDate);
            FlightsModel model = new FlightsModel(flightResearches);
            JTable flightsTable = new JTable(model);
            flightsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listSelect = flightsTable.getSelectionModel();
            flightsTable.setPreferredScrollableViewportSize(new Dimension(900, 100));

            this.setLayout(new FlowLayout());
            this.add(new JScrollPane(flightsTable));


        }
        catch (FlightsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch (ConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (PriceException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }
}
