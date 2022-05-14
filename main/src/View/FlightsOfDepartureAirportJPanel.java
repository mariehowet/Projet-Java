package View;

import Controller.ApplicationController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

import Exception.*;
import Model.FlightOfDepartureAirport;


public class FlightsOfDepartureAirportJPanel extends JPanel {
    private ApplicationController controller;
    private ListSelectionModel listSelect;
    private ArrayList<FlightOfDepartureAirport> flightsOfDepartureAirport;
    public FlightsOfDepartureAirportJPanel(Date startDate, Date endDate, int idAirport) {
        try {

            controller = new ApplicationController();
            flightsOfDepartureAirport = controller.getFlightsOfDepartureAirport(startDate,endDate,idAirport);
            FlightsOfDepartureAirportModel model = new FlightsOfDepartureAirportModel(flightsOfDepartureAirport);
            JTable flightsTable = new JTable(model);
            flightsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listSelect = flightsTable.getSelectionModel();
            flightsTable.setPreferredScrollableViewportSize(new Dimension(900, 100));

            this.setLayout(new FlowLayout());
            this.add(new JScrollPane(flightsTable));


        }
        catch (FlightsOfDepartureAirportException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch (ConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
