package View;

import Business.ConvertManager;
import Controller.ApplicationController;
import Model.Passenger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Exception.*;

public class MenuItemHistorySearch extends JPanel {
    private JPanel researchPanel, researchDisplay, displayPanel;
    private JButton researchButton;
    private JLabel passengerLabel;
    private JComboBox passenger;
    private Container frameContainer;
    private ApplicationController controller;

    public MenuItemHistorySearch(Container frameContainer) throws ConnectionException {
        this.controller = new ApplicationController();
        this.frameContainer = frameContainer;
        this.setLayout(new BorderLayout());


        // Panel de recherche
        researchPanel = new JPanel();
        researchPanel.setLayout(new FlowLayout());
        // Panel Affichage
        displayPanel = new JPanel();

        // Label
        passengerLabel = new JLabel("Passager");

        String [] passengersValues;

        try {
            ArrayList<Passenger> passengerList = controller.getAllPassengers();
            ArrayList<String> passengers = new ArrayList<>();

            for(Passenger pas : passengerList) {
                passengers.add(pas.getId() + "-" + pas.getFirstName() + " " + pas.getLastName() + (pas.getInitialMiddleName() != null ? pas.getInitialMiddleName() : "" ));
            }
            int nbPassengers = passengers.size();
            passengersValues = new String[nbPassengers];

            for (int j = 0; j < nbPassengers; j++) {
                passengersValues[j] = passengers.get(j);
            }

            passenger = new JComboBox(passengersValues);

        } catch (PassengerException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }


        // Button
        researchButton = new JButton("Rechercher");
        researchButton.addActionListener(new ResearchListener(displayPanel));

        // Panel de recherche
        researchPanel.add(passengerLabel);
        researchPanel.add(passenger);
        researchPanel.add(researchButton);

        this.add(researchPanel, BorderLayout.NORTH);
        this.add(displayPanel, BorderLayout.CENTER);
    }

    private class ResearchListener implements ActionListener {
        private JPanel panel;
        public ResearchListener(JPanel panel) {
            this.panel = panel;
        };
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            panel.revalidate();
            panel.repaint();
            try {
                int idPassenger = ConvertManager.stringIntoId(passenger.getSelectedItem().toString());
                researchDisplay = new BookingsHistoryJPanel(idPassenger);
                panel.add(researchDisplay, BorderLayout.CENTER);
                frameContainer.revalidate();
                frameContainer.repaint();
            }
            catch (IdException idException) {
                JOptionPane.showMessageDialog(null, idException.getMessage(), "Problème", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
