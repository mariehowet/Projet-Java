package View;

import Controller.ApplicationController;
import Model.FlightResearch;
import Model.Locality;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Exception.*;

public class FlightStopoverJPanel extends JPanel {
    private ApplicationController controller;
    private ArrayList<FlightResearch> flightsStopover;
    private JLabel title;

    public FlightStopoverJPanel(Locality departure, Locality arrival, boolean withStopover) {
        setLayout(new BorderLayout());
        try {
            controller = new ApplicationController();
            flightsStopover = controller.getFlightsStopover(departure, arrival, withStopover);
            if (flightsStopover.isEmpty()){
                JOptionPane.showMessageDialog(null, "Il n'y a pas de vols qui correspondent à vos critères de recherche");
            } else {
                FlightsModel model = new FlightsModel(flightsStopover);
                JTable flightsStopoverTable = new JTable(model);
                flightsStopoverTable.setPreferredScrollableViewportSize(new Dimension(900, 100));

                title = new JLabel("<html><h2 style='margin: 30px 0 15px 0'>Liste des vols " + (withStopover?"avec escales":"sans escale") + "</h2></html>", SwingConstants.CENTER);

                add(title, BorderLayout.NORTH);
                add(new JScrollPane(flightsStopoverTable), BorderLayout.CENTER);
            }

        }
        catch (FlightsStopover e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch (ConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (PriceException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
