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

import Exception.*;

public class MenuItemFindFlight extends JPanel {
    private JLabel departureCityLabel, arrivalCityLabel;
    private JComboBox departureCity, arrivalCity;
    private JDateChooser chooserStartDate, chooserEndDate;
    private Container frameContainer;
    private ApplicationController controller;
    private JPanel researchPanel, displayPanel, researchDisplay, panelStartDate, panelEndDate, gridFields, buttonPanel, gridResearch;
    private JButton researchButton;

    public MenuItemFindFlight(Container frameContainer) throws ConnectionException {
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
                Locality departure = ConvertManager.stringIntoLocality(departureCity.getSelectedItem().toString());
                Locality arrival = ConvertManager.stringIntoLocality(arrivalCity.getSelectedItem().toString());

                researchDisplay = new FindFlightsJPanel(frameContainer,panel ,departure, arrival, chooserStartDate.getDate(), chooserEndDate.getDate());
                frameContainer.revalidate();
                frameContainer.repaint();
                panel.add(researchDisplay, BorderLayout.CENTER);
            }
            catch (DatesException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Problème", JOptionPane.WARNING_MESSAGE);
            }
            catch (LocalityException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Problème", JOptionPane.WARNING_MESSAGE);
            }
            catch (DatesNullException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Problème", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

}