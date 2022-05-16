package View;

import Business.ConvertManager;
import Controller.ApplicationController;
import Model.Locality;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Exception.*;

public class FindFlight extends JPanel {
    private JLabel departureCityLabel, arrivalCityLabel, startDateLabel, endDateLabel;
    private JComboBox departureCity, arrivalCity;
    private JDateChooser chooserStartDate, chooserEndDate;
    private Container frameContainer;
    private ApplicationController controller;
    private JPanel researchPanel, displayPanel, researchDisplay, panelStartDate, panelEndDate, gridFields, buttonPanel, gridResearch;
    private JButton researchButton;

    public FindFlight(Container frameContainer) throws ConnectionException {
        this.controller = new ApplicationController();
        this.frameContainer = frameContainer;
        this.setLayout(new BorderLayout());


        // Panel de recherche
        researchPanel = new JPanel();
        researchPanel.setLayout(new FlowLayout());

        // Panel grille des champs de remplissage
        gridFields = new JPanel();
        gridFields.setLayout(new GridLayout(4,2));

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
            departureCityLabel = new JLabel("Ville de départ        ");
            //departureCityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            gridFields.add(departureCityLabel);
            departureCity = new JComboBox(localitiesValues);
            gridFields.add(departureCity);

            arrivalCityLabel = new JLabel("Ville de destination        ");
            //arrivalCityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            gridFields.add(arrivalCityLabel);
            arrivalCity = new JComboBox(localitiesValues);
            gridFields.add(arrivalCity);

        } catch (AllLocalitiesException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }


        // Création des dates
        chooserStartDate = new JDateChooser();
        chooserStartDate.setLocale(Locale.FRENCH);

        panelStartDate = new JPanel();
        panelStartDate.add(new JLabel("Date de début "));
        panelStartDate.add(chooserStartDate);

        chooserEndDate = new JDateChooser();
        chooserEndDate.setLocale(Locale.FRENCH);

        panelEndDate = new JPanel();
        panelEndDate.add(new JLabel("Date de fin "));
        panelEndDate.add(chooserEndDate);


        // Button
        buttonPanel = new JPanel(new FlowLayout());
        researchButton = new JButton("Rechercher");
        researchButton.addActionListener(new ResearchListener(displayPanel));
        buttonPanel.add(researchButton);

        // Panel de recherche
        gridFields.add(panelStartDate);
        gridFields.add(panelEndDate);

        gridResearch = new JPanel(new GridLayout(2,1));
        gridResearch.add(gridFields);
        gridResearch.add(buttonPanel);


        researchPanel.add(gridResearch);

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
                Locality departure = ConvertManager.stringIntoLocality(departureCity.getSelectedItem().toString());
                Locality arrival = ConvertManager.stringIntoLocality(arrivalCity.getSelectedItem().toString());

                researchDisplay = new FindFlightsJPanel(departure, arrival, chooserStartDate.getDate(), chooserEndDate.getDate());
                frameContainer.revalidate();
                frameContainer.repaint();
                panel.add(researchDisplay, BorderLayout.CENTER);
                System.out.println("test");
            }
            catch (DatesException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Problème", JOptionPane.WARNING_MESSAGE);
            }
            catch (LocalityException localityException) {
                JOptionPane.showMessageDialog(null, localityException.getMessage(), "Problème", JOptionPane.WARNING_MESSAGE);
            }
            catch (DatesNullException dateNullException) {
                JOptionPane.showMessageDialog(null, dateNullException.getMessage(), "Problème", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

}
