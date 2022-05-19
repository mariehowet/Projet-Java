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
    private ListSelectionModel listSelect;
    private ArrayList<FlightResearch> flightsStopover;

    public FlightStopoverJPanel(Locality departure, Locality arrival, boolean withStopover) {
        try {
            controller = new ApplicationController();
            flightsStopover = controller.getFlightsStopover(departure, arrival, withStopover);
            if (flightsStopover.isEmpty()){
                JOptionPane.showMessageDialog(null, "Il n'y a pas de vols qui correspondent à vos critères de recherche");
            } else {
                FlightsModel model = new FlightsModel(flightsStopover);
                JTable flightsStopoverTable = new JTable(model);
                flightsStopoverTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                listSelect = flightsStopoverTable.getSelectionModel();
                flightsStopoverTable.setPreferredScrollableViewportSize(new Dimension(900, 100));

                this.setLayout(new FlowLayout());
                this.add(new JScrollPane(flightsStopoverTable));
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
