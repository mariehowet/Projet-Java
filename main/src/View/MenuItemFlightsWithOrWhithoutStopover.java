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
    private JPanel researchPanel, researchDisplay, displayPanel, stopoversPanel;
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
        displayPanel = new JPanel(new FlowLayout());

        // Récupération des différentes localitées
        try {
            ArrayList<Locality> localitiesList = controller.getAllLocalities();
            departureCity = new JComboBox();
            arrivalCity = new JComboBox();

            for (Locality loc : localitiesList) {
                departureCity.addItem(loc.getCity()+ "-" + loc.getCountry() + "-" + loc.getPostCode());
                arrivalCity.addItem(loc.getCity()+ "-" + loc.getCountry() + "-" + loc.getPostCode());
            }

            // Ville de départ
            departureCityLabel = new JLabel("Ville de départ ");
            researchPanel.add(departureCityLabel);
            researchPanel.add(departureCity);

            // Ville de destination
            arrivalCityLabel = new JLabel("Ville de destination ");
            researchPanel.add(arrivalCityLabel);
            researchPanel.add(arrivalCity);

        } catch (AllLocalitiesException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // Boutons escale
        withoutStopover = new JRadioButton("Sans escale");
        researchPanel.add(withoutStopover);
        withStopover = new JRadioButton("Avec escales");
        researchPanel.add(withStopover);

        stopover = new ButtonGroup();
        stopover.add(withoutStopover);
        stopover.add(withStopover);
        withoutStopover.setSelected(true);

        // Bouton de recherche
        researchButton = new JButton("Rechercher");
        researchButton.addActionListener(new ResearchListener());
        researchPanel.add(researchButton);

        stopoversPanel = new JPanel(new FlowLayout());
        add(researchPanel, BorderLayout.NORTH);
        add(displayPanel, BorderLayout.CENTER);
        add(stopoversPanel, BorderLayout.SOUTH);
    }

    private class ResearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            displayPanel.removeAll();
            displayPanel.revalidate();
            displayPanel.repaint();
            stopoversPanel.removeAll();
            stopoversPanel.revalidate();
            stopoversPanel.repaint();

            try {
                Locality departure = ConvertManager.stringIntoLocality(departureCity.getSelectedItem().toString());
                Locality arrival = ConvertManager.stringIntoLocality(arrivalCity.getSelectedItem().toString());

                researchDisplay = new FlightStopoverJPanel(stopoversPanel, departure, arrival,withStopover.isSelected());
                displayPanel.add(researchDisplay);
            }
            catch (LocalityException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Problème", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
