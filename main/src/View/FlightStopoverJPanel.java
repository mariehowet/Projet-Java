package View;

import Controller.ApplicationController;
import Model.FlightWithOrWhithoutStopover;
import Model.Locality;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Exception.*;

public class FlightStopoverJPanel extends JPanel {
    private ApplicationController controller;
    private ListSelectionModel listSelect;
    private ArrayList<FlightWithOrWhithoutStopover> flightsStopover;
    private JLabel title;
    private JPanel stopoversPanel, flightStopoversPanel, researchStopoversPanel;
    private FlightsStopoverModel model;
    private JTable flightsStopoverTable;
    private JButton stopoversButton;

    public FlightStopoverJPanel(JPanel stopoversPanel, Locality departure, Locality arrival, boolean withStopover) {
        setLayout(new BorderLayout());
        this.stopoversPanel = stopoversPanel;
        try {
            controller = new ApplicationController();
            flightsStopover = controller.getFlightsStopover(departure, arrival, withStopover);

            if (flightsStopover.isEmpty()){
                JOptionPane.showMessageDialog(null, "Il n'y a pas de vols qui correspondent à vos critères de recherche");
            } else {
                model = new FlightsStopoverModel(flightsStopover);
                flightsStopoverTable = new JTable(model);
                listSelect = flightsStopoverTable.getSelectionModel();
                flightsStopoverTable.setPreferredScrollableViewportSize(new Dimension(950, 100));

                title = new JLabel("<html><h2 style='margin: 30px 0 15px 0'>Liste des vols " + (withStopover?"avec escales":"sans escale") + "</h2></html>", SwingConstants.CENTER);
                add(title, BorderLayout.NORTH);
                add(new JScrollPane(flightsStopoverTable), BorderLayout.CENTER);


                if(withStopover) {
                    researchStopoversPanel = new JPanel(new FlowLayout());
                    stopoversButton = new JButton("Voir toutes les options possible");
                    stopoversButton.addActionListener(new StopoversListener());
                    researchStopoversPanel.add(stopoversButton);
                    add(researchStopoversPanel, BorderLayout.SOUTH);
                }
            }
        }
        catch (FlightsStopoverException | ConnectionException | PriceException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private class StopoversListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int indSelectedLine = listSelect.getMinSelectionIndex();

            if(indSelectedLine == -1) {
                JOptionPane.showMessageDialog(null, "Vous n'avez pas sélectionné de vol", "Problème", JOptionPane.WARNING_MESSAGE);
            } else {
                int response = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir afficher toutes les escales pour ce vol ?", "Validation", JOptionPane.YES_NO_OPTION);

                if (response == 0) {
                    FlightWithOrWhithoutStopover flightStopover = flightsStopover.get(indSelectedLine);

                    try {
                        stopoversPanel.removeAll();
                        stopoversPanel.revalidate();
                        stopoversPanel.repaint();

                        flightStopoversPanel = new StopoversJPanel(flightStopover.getFlightId());
                        stopoversPanel.add(flightStopoversPanel);
                    }
                    catch (ConnectionException connectionException) {
                        JOptionPane.showMessageDialog(null, connectionException.getMessage(), "Problème", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }
    }
}
