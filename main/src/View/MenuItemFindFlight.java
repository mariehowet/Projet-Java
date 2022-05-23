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

import Exception.*;

public class MenuItemFindFlight extends JPanel {
    private JLabel departureCityLabel, arrivalCityLabel;
    private JComboBox departureCity, arrivalCity;
    private JDateChooser chooserStartDate, chooserEndDate;
    private ApplicationController controller;
    private JPanel researchPanel, displayPanel, researchDisplay, panelStartDate, panelEndDate, gridFields,
            buttonPanel, gridResearch, optionsPanel;
    private JButton researchButton;

    public MenuItemFindFlight() throws ConnectionException {
        setLayout(new BorderLayout());
        controller = new ApplicationController();

        researchPanel = new JPanel();
        researchPanel.setLayout(new FlowLayout());

        gridFields = new JPanel();
        gridFields.setLayout(new GridLayout(3,2));

        displayPanel = new JPanel(new FlowLayout());

        try {
            String[] localitiesValues;

            ArrayList<Locality> localitiesList = controller.getAllLocalities();

            int nbLocalities = localitiesList.size();
            localitiesValues = new String[nbLocalities];

            for (int j = 0; j < nbLocalities; j++) {
                localitiesValues[j] = localitiesList.get(j).getCity()+ "-" + localitiesList.get(j).getCountry() + "-" + localitiesList.get(j).getPostCode();
            }

            departureCity = new JComboBox(localitiesValues);
            arrivalCity = new JComboBox(localitiesValues);

            departureCityLabel = new JLabel("Ville de départ");
            gridFields.add(departureCityLabel);
            gridFields.add(departureCity);

            arrivalCityLabel = new JLabel("Ville de destination");
            gridFields.add(arrivalCityLabel);
            gridFields.add(arrivalCity);

        } catch (AllLocalitiesException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }


        chooserStartDate = new JDateChooser();
        chooserStartDate.setPreferredSize(new Dimension(150,30));

        panelStartDate = new JPanel();
        panelStartDate.add(new JLabel("Date de début "));
        panelStartDate.add(chooserStartDate);

        chooserEndDate = new JDateChooser();
        chooserEndDate.setPreferredSize(new Dimension(150,30));

        panelEndDate = new JPanel();
        panelEndDate.add(new JLabel("Date de fin "));
        panelEndDate.add(chooserEndDate);

        buttonPanel = new JPanel(new FlowLayout());
        researchButton = new JButton("Rechercher");
        researchButton.addActionListener(new ResearchListener());
        buttonPanel.add(researchButton);

        gridFields.add(panelStartDate);
        gridFields.add(panelEndDate);

        gridResearch = new JPanel(new GridLayout(2,1));
        gridResearch.add(gridFields);
        gridResearch.add(buttonPanel);

        researchPanel.add(gridResearch);

        optionsPanel = new JPanel(new FlowLayout());
        add(researchPanel, BorderLayout.NORTH);
        add(displayPanel, BorderLayout.CENTER);
        add(optionsPanel, BorderLayout.SOUTH);
    }

    private class ResearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            displayPanel.removeAll();
            displayPanel.revalidate();
            displayPanel.repaint();
            optionsPanel.removeAll();
            optionsPanel.revalidate();
            optionsPanel.repaint();
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

                researchDisplay = new FindFlightsJPanel(optionsPanel,departure, arrival, chooserStartDate.getDate(), chooserEndDate.getDate());
                displayPanel.add(researchDisplay);
            }
            catch (DatesException | LocalityException | DatesNullException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Problème", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

}
