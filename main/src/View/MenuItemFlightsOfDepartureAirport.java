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
    private JLabel departureAirportLabel;
    private JComboBox airport;
    private JPanel researchPanel, displayPanel,researchDisplay,panelStartDate, panelEndDate;
    private JButton researchButton;
    private Container frameContainer;
    private ApplicationController controller;
    private JDateChooser chooserStartDate, chooserEndDate;
    private ArrayList<Airport> airportsList;

    public MenuItemFlightsOfDepartureAirport(Container frameContainer) throws ConnectionException {

        setLayout(new BorderLayout());
        this.frameContainer = frameContainer;
        controller = new ApplicationController();

        researchPanel = new JPanel();
        researchPanel.setLayout(new FlowLayout());

        displayPanel = new JPanel();

        try {
            airportsList = controller.getAllAirports();
            airport = new JComboBox();;

            for(Airport a : airportsList) {
                airport.addItem(a.getCode() + "-" + a.getName());
            }

            departureAirportLabel = new JLabel("Aéroport de départ ");
        } catch (AllAirportsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        chooserStartDate = new JDateChooser();
        chooserStartDate.setLocale(Locale.FRENCH);
        chooserStartDate.setPreferredSize(new Dimension(125,25));

        panelStartDate = new JPanel();
        panelStartDate.add(new JLabel("Date de début "));
        panelStartDate.add(chooserStartDate);

        chooserEndDate = new JDateChooser();
        chooserEndDate.setLocale(Locale.FRENCH);
        chooserEndDate.setPreferredSize(new Dimension(125,25));

        panelEndDate = new JPanel();
        panelEndDate.add(new JLabel("Date de fin "));
        panelEndDate.add(chooserEndDate);

        researchButton = new JButton("Rechercher");
        researchButton.addActionListener(new ResearchListener());

        researchPanel.add(panelStartDate);
        researchPanel.add(panelEndDate);
        researchPanel.add(departureAirportLabel);
        researchPanel.add(airport);
        researchPanel.add(researchButton);

        add(researchPanel, BorderLayout.NORTH);
        add(displayPanel, BorderLayout.CENTER);
    }

    private class ResearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            displayPanel.removeAll();
            displayPanel.revalidate();
            displayPanel.repaint();
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

                int idAirport = ConvertManager.stringIntoId(airport.getSelectedItem().toString());
                researchDisplay = new FlightsOfDepartureAirportJPanel(chooserStartDate.getDate(),chooserEndDate.getDate(),idAirport);
                frameContainer.revalidate();
                frameContainer.repaint();
                displayPanel.add(researchDisplay, BorderLayout.CENTER);

            }
            catch (DatesException | DatesNullException | IdException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Problème", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
