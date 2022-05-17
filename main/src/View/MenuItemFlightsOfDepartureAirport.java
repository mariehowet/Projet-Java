package View;

import Business.ConvertManager;
import Controller.ApplicationController;
import Model.Airport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;

import Exception.*;
import com.toedter.calendar.JDateChooser;

public class MenuItemFlightsOfDepartureAirport extends JPanel {
    private JLabel startDateLabel, endDateLabel, departureAirportLabel;
    private JComboBox airport;
    private JPanel researchPanel, displayPanel,researchDisplay;
    private JButton researchButton;
    private Container frameContainer;
    private ApplicationController controller;
    private JDateChooser chooserStartDate, chooserEndDate;

    public MenuItemFlightsOfDepartureAirport(Container frameContainer) throws ConnectionException {
        this.controller = new ApplicationController();
        this.frameContainer = frameContainer;
        this.setLayout(new BorderLayout());

        // Panel de recherche
        researchPanel = new JPanel();
        researchPanel.setLayout(new FlowLayout());
        // Panel Affichage
        displayPanel = new JPanel();




        String [] airportsValues;

        try {
            ArrayList<Airport> airportsList = controller.getAllAirports();
            ArrayList<String> airports = new ArrayList<>();

            for(Airport a : airportsList) {
                airports.add(a.getCode() + "-" + a.getName());
            }
            int nbPassengers = airports.size();
            airportsValues = new String[nbPassengers];

            for (int j = 0; j < nbPassengers; j++) {
                airportsValues[j] = airports.get(j);
            }

            airport = new JComboBox(airportsValues);

        } catch (AllAirportsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // Création des dates
        chooserStartDate = new JDateChooser();
        chooserStartDate.setLocale(Locale.FRENCH);

        JPanel panelStartDate = new JPanel();
        panelStartDate.add(new JLabel("Date de début "));
        panelStartDate.add(chooserStartDate);

        chooserEndDate = new JDateChooser();
        chooserEndDate.setLocale(Locale.FRENCH);

        JPanel panelEndDate = new JPanel();
        panelEndDate.add(new JLabel("Date de fin "));
        panelEndDate.add(chooserEndDate);


        departureAirportLabel = new JLabel("Aéroport de départ ");


        // Button
        researchButton = new JButton("Rechercher");
        researchButton.addActionListener(new ResearchListener(displayPanel));

        // Ajout dans panel de recherche
        researchPanel.add(panelStartDate);
        researchPanel.add(panelEndDate);
        researchPanel.add(departureAirportLabel);
        researchPanel.add(airport);
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
                if (chooserStartDate.getDate() == null) {
                    throw new DatesNullException("début");
                }
                if (chooserEndDate.getDate() == null) {
                    throw new DatesNullException("fin");
                }
                if (chooserStartDate.getDate().after(chooserEndDate.getDate())) {
                    throw new DatesException();
                }

                try {
                    int idAirport = ConvertManager.stringIntoId(airport.getSelectedItem().toString());
                    researchDisplay = new FlightsOfDepartureAirportJPanel(chooserStartDate.getDate(),chooserEndDate.getDate(),idAirport);
                    frameContainer.revalidate();
                    frameContainer.repaint();
                    panel.add(researchDisplay, BorderLayout.CENTER);
                }
                catch (IdException idException) {
                    JOptionPane.showMessageDialog(null, idException.getMessage(), "Problème", JOptionPane.WARNING_MESSAGE);
                }

            }
            catch (DatesException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Problème", JOptionPane.WARNING_MESSAGE);
            }
            catch (DatesNullException dateNullException) {
                JOptionPane.showMessageDialog(null, dateNullException.getMessage(), "Problème", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
