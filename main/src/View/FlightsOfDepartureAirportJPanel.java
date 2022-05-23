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
    private JLabel title;
    private FlightsOfDepartureAirportModel model;
    private JTable flightsTable;

    private ArrayList<FlightOfDepartureAirport> flightsOfDepartureAirport;

    public FlightsOfDepartureAirportJPanel(Date startDate, Date endDate, int idAirport) {
        setLayout(new BorderLayout());
        try {
            controller = new ApplicationController();
            flightsOfDepartureAirport = controller.getFlightsOfDepartureAirport(startDate,endDate,idAirport);

            if (flightsOfDepartureAirport.isEmpty()){
                JOptionPane.showMessageDialog(null, "Il n'y a pas de vols qui correspondent à vos critères de recherche");
            } else {
                model = new FlightsOfDepartureAirportModel(flightsOfDepartureAirport);
                flightsTable = new JTable(model);

                flightsTable.setPreferredScrollableViewportSize(new Dimension(900, 100));

                title = new JLabel("<html><h2 style='margin: 30px 0 15px 0'>Liste des vols</h2></html>", SwingConstants.CENTER);

                add(title, BorderLayout.NORTH);
                add(new JScrollPane(flightsTable), BorderLayout.CENTER);
            }
        }
        catch (FlightsOfDepartureAirportException | ConnectionException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }
}
