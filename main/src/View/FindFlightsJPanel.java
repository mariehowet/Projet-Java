package View;

import Controller.ApplicationController;
import Model.Booking;
import Model.FlightResearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import Exception.*;
import Model.Locality;

public class FindFlightsJPanel extends JPanel {
    private ApplicationController controller;
    private ListSelectionModel listSelect;
    private ArrayList<FlightResearch> flightResearches;
    private JButton optionsButton;
    private JPanel researchOptionsPanel, flightOptionsJPanel, optionsPanel;
    private FlightsModel model;
    private JTable flightsTable;
    private JLabel title;

    public FindFlightsJPanel(JPanel optionsPanel,Locality departure, Locality arrival, Date startDate, Date endDate) {
        setLayout(new BorderLayout());
        this.optionsPanel = optionsPanel;


        try {
            controller = new ApplicationController();
            flightResearches = controller.getFlights(departure, arrival, startDate, endDate);

            if (flightResearches.isEmpty()){
                JOptionPane.showMessageDialog(null, "Il n'y a pas de vols qui correspondent à vos critères de recherche");
            } else {
                model = new FlightsModel(flightResearches);
                flightsTable = new JTable(model);
                flightsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                listSelect = flightsTable.getSelectionModel();
                flightsTable.setPreferredScrollableViewportSize(new Dimension(900, 100));

                researchOptionsPanel = new JPanel(new FlowLayout());
                optionsButton = new JButton("Voir toutes les options possible");
                optionsButton.addActionListener(new OptionsListener());

                researchOptionsPanel.add(optionsButton);

                title = new JLabel("<html><h2>Liste des vols</h2></html>", SwingConstants.CENTER);

                add(title, BorderLayout.NORTH);
                add(new JScrollPane(flightsTable), BorderLayout.CENTER);
                add(researchOptionsPanel, BorderLayout.SOUTH);
            }
        }
        catch (FlightsException | ConnectionException | PriceException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }

    private class OptionsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int indSelectedLine = listSelect.getMinSelectionIndex();

            if(indSelectedLine == -1) {
                JOptionPane.showMessageDialog(null, "Vous n'avez pas sélectionné de vol", "Problème", JOptionPane.WARNING_MESSAGE);
            } else {
                int response = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir afficher toutes les options pour ce vol ?", "Validation", JOptionPane.YES_NO_OPTION);

                if (response == 0) {
                    FlightResearch flightResearch = flightResearches.get(indSelectedLine);

                    try {
                        optionsPanel.removeAll();
                        optionsPanel.revalidate();
                        optionsPanel.repaint();

                        flightOptionsJPanel = new FlightOptionsJPanel(flightResearch);
                        optionsPanel.add(flightOptionsJPanel);
                    }
                    catch (ConnectionException connectionException) {
                        JOptionPane.showMessageDialog(null, connectionException.getMessage(), "Problème", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }
    }
}
