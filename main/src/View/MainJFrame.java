package View;

import Controller.ApplicationController;
import Exception.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import Exception.ConnectionException;

public class MainJFrame extends JFrame {
    private Container frameContainer;
    private WelcomeJPanel welcomeJPanel;
    private JMenuBar menuBar;
    private JMenu marvinAirline,researchMenu, findFlightMenu, monitoringFlightMenu, form, bookings;
    private JMenuItem exitMenuItem, backToWelcomePanel, research1, research2, research3, menuIemFlightMenu, monitoringFlight, bookingsMenuItem;
    private JPanel BookingForm;


    public MainJFrame() {
        // fenetre
        super("Welcome");
        setBounds(0,0,1000,750);

        // Container
        frameContainer = this.getContentPane();
        frameContainer.setLayout(new BorderLayout());

        // Panneau de bienvenue
        welcomeJPanel = new WelcomeJPanel();
        frameContainer.add(welcomeJPanel, BorderLayout.CENTER);

        // Barre de menu
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Options menu
        marvinAirline = new JMenu("Marvin Airline");
        researchMenu = new JMenu("Recherches");
        findFlightMenu = new JMenu("Trouver un vol");
        monitoringFlightMenu = new JMenu("Suivi d'un vol");
        form = new JMenu("Formulaire");
        bookings = new JMenu("Réservations");

        menuBar.add(marvinAirline);
        menuBar.add(researchMenu);
        menuBar.add(findFlightMenu);
        menuBar.add(monitoringFlightMenu);
        menuBar.add(bookings);

        // Items : Marvin Airline
        exitMenuItem = new JMenuItem("Quitter");
        exitMenuItem.addActionListener(new ExitListener());
        backToWelcomePanel = new JMenuItem("Retour au menu principal");
        backToWelcomePanel.addActionListener(new BackToWelcomePanelListener());
        marvinAirline.add(exitMenuItem);
        marvinAirline.add(backToWelcomePanel);

        // Item : recherche 1
        research1 = new JMenuItem("Recherche 1");
        research1.addActionListener(new JMenuItemListener(1));
        researchMenu.add(research1);

        // Item : recherche 2
        research2 = new JMenuItem("Recherche 2");
        research2.addActionListener(new JMenuItemListener( 2));
        researchMenu.add(research2);

        // Item : recherche 3
        research3 = new JMenuItem("Recherche 3");
        research3.addActionListener(new JMenuItemListener(3));
        researchMenu.add(research3);

        // Find Flight
        menuIemFlightMenu = new JMenuItem("Aller à la recherche");
        menuIemFlightMenu.addActionListener(new JMenuItemListener(4));
        findFlightMenu.add(menuIemFlightMenu);

        // Monitoring
        monitoringFlight = new JMenuItem(" Suivez votre vol en direct !");
        monitoringFlight.addActionListener(new JMenuItemListener(5));
        monitoringFlightMenu.add(monitoringFlight);


        // Bookings
        bookingsMenuItem = new JMenuItem("Aller vers les réservations");
        bookingsMenuItem.addActionListener(new JMenuItemListener(6));
        bookings.add(bookingsMenuItem);



        // Ajout des données dans la BD
        BookingForm = new BookingForm();


        this.addWindowListener(new ClosingListener());


        // Affichage
        setVisible(true);
    }

    private class JMenuItemListener implements ActionListener {
        int numPanel;

        public JMenuItemListener(int numPanel) {
            this.numPanel = numPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frameContainer.removeAll();
            switch (numPanel) {
                case 1:
                    frameContainer.add(new Research1JPanel(frameContainer), BorderLayout.CENTER);
                    break;
                case 2:
                    frameContainer.add(new Research2JPanel(frameContainer), BorderLayout.CENTER);
                    break;
                case 3:
                    frameContainer.add(new Research3JPanel(frameContainer), BorderLayout.CENTER);
                    break;
                case 4:
                    frameContainer.add(new FindFlight(frameContainer), BorderLayout.CENTER);
                    break;
                case 5:
                    frameContainer.add(new FollowedFlightJPanel(frameContainer), BorderLayout.CENTER);
                    break;
                case 6:
                    frameContainer.add(new AllBookingsPanel(frameContainer), BorderLayout.CENTER);
                    break;
            }
            setVisible(true);
        }
    }

    private class BackToWelcomePanelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frameContainer.removeAll();
            WelcomeJPanel welcomeJPanel = new WelcomeJPanel();
            frameContainer.add(welcomeJPanel);
            setVisible(true);
        }
    }

    private class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ApplicationController controller = new ApplicationController();
                controller.closeConnection();
            }
            catch (ConnectionException connectionException) {
                JOptionPane.showMessageDialog(null, connectionException.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            catch (CloseDataException closeDataException) {
                JOptionPane.showMessageDialog(null, closeDataException.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            System.exit(0);
        }
    }

    private static class ClosingListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {

            try {
                ApplicationController controller = new ApplicationController();
                controller.closeConnection();
            }
            catch (ConnectionException connectionException) {
                JOptionPane.showMessageDialog(null, connectionException.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            catch (CloseDataException closeDataException) {
                JOptionPane.showMessageDialog(null, closeDataException.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            System.exit(0);
        }
    }

}


