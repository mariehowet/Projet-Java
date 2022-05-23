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

        researchPanel = new JPanel();
        researchPanel.setLayout(new FlowLayout());

        displayPanel = new JPanel();

        passengerLabel = new JLabel("Passager");

        try {
            ArrayList<Passenger> passengerList = controller.getAllPassengers();
            passenger = new JComboBox();

            for(Passenger pas : passengerList) {
                passenger.addItem(pas.getId() + "-" + pas.getFirstName() + " " + pas.getLastName() + (pas.getInitialMiddleName() != null ? " " + pas.getInitialMiddleName() : "" ));
            }

        } catch (PassengerException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        researchButton = new JButton("Rechercher");
        researchButton.addActionListener(new ResearchListener());

        researchPanel.add(passengerLabel);
        researchPanel.add(passenger);
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
                int idPassenger = ConvertManager.stringIntoId(passenger.getSelectedItem().toString());
                researchDisplay = new BookingsHistoryJPanel(idPassenger);
                displayPanel.add(researchDisplay, BorderLayout.CENTER);
                frameContainer.revalidate();
                frameContainer.repaint();
            }
            catch (IdException idException) {
                JOptionPane.showMessageDialog(null, idException.getMessage(), "Problème", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
