package View;

import Business.ConvertManager;
import Controller.ApplicationController;
import Model.Locality;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Exception.*;

public class MenuItemFlightsWithOrWhithoutStopover extends JPanel {
    private JPanel researchPanel, researchDisplay, displayPanel;
    private JButton researchButton;
    private JLabel departureCityLabel, arrivalCityLabel;
    private JComboBox departureCity, arrivalCity;
    private Container frameContainer;
    private ApplicationController controller;
    private ButtonGroup stopover;
    private JRadioButton withStopover, withoutStopover;

    public MenuItemFlightsWithOrWhithoutStopover(Container frameContainer) throws ConnectionException {
        this.controller = new ApplicationController();
        this.frameContainer = frameContainer;
        this.setLayout(new BorderLayout());

        // Panel de recherche
        researchPanel = new JPanel();
        researchPanel.setLayout(new FlowLayout());

        // Panel Affichage
        displayPanel = new JPanel();

        try {
            String[] localitiesValues;

            ArrayList<Locality> localitiesList = controller.getAllLocalities();
            ArrayList<String> localities = new ArrayList<>();

            for (Locality loc : localitiesList) {
                localities.add(loc.getCity()+ "-" + loc.getCountry() + "-" + loc.getPostCode());
            }
            int nbPassengers = localities.size();
            localitiesValues = new String[nbPassengers];

            for (int j = 0; j < nbPassengers; j++) {
                localitiesValues[j] = localities.get(j);
            }

            // Labels
            departureCityLabel = new JLabel("Ville de départ ");
            researchPanel.add(departureCityLabel);
            departureCity = new JComboBox(localitiesValues);
            researchPanel.add(departureCity);

            arrivalCityLabel = new JLabel("Ville de destination ");
            researchPanel.add(arrivalCityLabel);
            arrivalCity = new JComboBox(localitiesValues);
            researchPanel.add(arrivalCity);

        } catch (AllLocalitiesException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // Buttons stopover
        withoutStopover = new JRadioButton("Sans escale");
        researchPanel.add(withoutStopover);
        withStopover = new JRadioButton("Avec escale");
        researchPanel.add(withStopover);

        stopover = new ButtonGroup();
        stopover.add(withoutStopover);
        stopover.add(withStopover);
        withoutStopover.setSelected(true);

        // Button
        researchButton = new JButton("Rechercher");
        researchButton.addActionListener(new ResearchListener(displayPanel));
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
                Locality departure = ConvertManager.stringIntoLocality(departureCity.getSelectedItem().toString());
                Locality arrival = ConvertManager.stringIntoLocality(arrivalCity.getSelectedItem().toString());
                researchDisplay = new FlightStopoverJPanel(departure, arrival,withStopover.isSelected());
                panel.add(researchDisplay, BorderLayout.CENTER);
                frameContainer.revalidate();
                frameContainer.repaint();
            }
            catch (LocalityException localityException) {
                System.out.println("erreur");
            }
        }
    }
}
