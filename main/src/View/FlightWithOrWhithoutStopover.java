package View;

import Controller.ApplicationController;
import Model.Locality;
import Model.Passenger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Exception.*;

public class FlightWithOrWhithoutStopover extends JPanel {
    private JPanel researchPanel, researchDisplay, displayPanel;
    private JButton researchButton;
    private JLabel departureCityLabel, arrivalCityLabel;
    private JComboBox departureCity, arrivalCity;
    private Container frameContainer;
    private ApplicationController controller;
    private ButtonGroup stopover;
    private JRadioButton withStopover, withoutStopover;

    public FlightWithOrWhithoutStopover(Container frameContainer) throws ConnectionException {
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

            try {
                Locality departure = stringIntoLocality(departureCity.getSelectedItem().toString());
                Locality arrival = stringIntoLocality(arrivalCity.getSelectedItem().toString());
                System.out.println(departure.getCity());
                System.out.println(departure.getCountry());
                System.out.println(departure.getPostCode());

                System.out.println(arrival.getCity());
                System.out.println(arrival.getCountry());
                System.out.println(arrival.getPostCode());




                researchDisplay = new FlightStopoverPanel(departure, arrival,withStopover.isSelected());
                frameContainer.revalidate();
                frameContainer.repaint();
                panel.add(researchDisplay, BorderLayout.CENTER);

            }
            catch (LocalityException localityException) {
                System.out.println("erreur");
            }
        }

        public Locality stringIntoLocality(String localite) throws LocalityException {
            Pattern patternCity = Pattern.compile("^([\\w\\s]+)", Pattern.CASE_INSENSITIVE);
            Matcher matcherCity = patternCity.matcher(localite);
            Pattern patternCountry = Pattern.compile("-(.*)-", Pattern.CASE_INSENSITIVE);
            Matcher matcherCountry = patternCountry.matcher(localite);
            Pattern patternPostCode = Pattern.compile("([\\w]+)$", Pattern.CASE_INSENSITIVE);
            Matcher matcherPostCode = patternPostCode.matcher(localite);
            if (matcherCity.find() && matcherCountry.find() && matcherPostCode.find()) {
                return new Locality (matcherCity.group(1),matcherPostCode.group(1),matcherCountry.group(1));
            } else {
                throw new LocalityException();
            }

        }
    }
}
