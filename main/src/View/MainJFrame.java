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
    private JMenuItem exitMenuItem, backToWelcomePanel, research1, research2, research3, menuIemFlightMenu, monitoringFlight, bookingsMenuItem, addBookingMenuItem;

    public MainJFrame() {
        // fenetre
        super("Marvin Airline");
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
        backToWelcomePanel = new JMenuItem("Page d'acceuil");
        backToWelcomePanel.addActionListener(new BackToWelcomePanelListener());
        marvinAirline.add(backToWelcomePanel);
        marvinAirline.add(exitMenuItem);

        // Item : recherche 1
        research1 = new JMenuItem("Vols partant d'un aéroport");
        research1.addActionListener(new Research1Listener());
        researchMenu.add(research1);

        // Item : recherche 2
        research2 = new JMenuItem("Vols avec et sans escales");
        research2.addActionListener(new Research2Listener());
        researchMenu.add(research2);

        // Item : recherche 3
        research3 = new JMenuItem("Historique des réservations d'un passager");
        research3.addActionListener(new Research3Listener());
        researchMenu.add(research3);

        // Find Flight
        menuIemFlightMenu = new JMenuItem("Aller à la recherche");
        menuIemFlightMenu.addActionListener(new FindFlightListener());
        findFlightMenu.add(menuIemFlightMenu);

        // Monitoring
        monitoringFlight = new JMenuItem(" Suivez un vol en direct !");
        monitoringFlight.addActionListener(new ThreadListener());
        monitoringFlightMenu.add(monitoringFlight);

        // Bookings
        bookingsMenuItem = new JMenuItem("Liste des réservations");
        addBookingMenuItem = new JMenuItem("Ajouter une réservation");
        addBookingMenuItem.addActionListener(new AddBookingListener());
        bookingsMenuItem.addActionListener(new ListeningListener());
        bookings.add(bookingsMenuItem);
        bookings.add(addBookingMenuItem);

        addWindowListener(new ClosingListener());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public class Research1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frameContainer.removeAll();
            try {
                frameContainer.add(new MenuItemFlightsOfDepartureAirport(frameContainer), BorderLayout.CENTER);
            }
            catch (ConnectionException connectionFindFlightsOfDepartureAirportException) {
                JOptionPane.showMessageDialog(null, connectionFindFlightsOfDepartureAirportException.getMessage());
            }
            setVisible(true);

        }
    }

    public class Research2Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frameContainer.removeAll();
            try {
                frameContainer.add(new MenuItemFlightsWithOrWhithoutStopover(frameContainer), BorderLayout.CENTER);
            } catch (ConnectionException stopoverConnectionException) {
                JOptionPane.showMessageDialog(null, stopoverConnectionException.getMessage());
            }
            setVisible(true);
        }
    }
    public class Research3Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frameContainer.removeAll();
            try{
                frameContainer.add(new MenuItemHistorySearch(frameContainer), BorderLayout.CENTER);
            } catch (ConnectionException historyConnectionException) {
                JOptionPane.showMessageDialog(null, historyConnectionException.getMessage());
            }
            setVisible(true);
        }
    }

    public class FindFlightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frameContainer.removeAll();
            try {
                frameContainer.add(new MenuItemFindFlight(frameContainer), BorderLayout.CENTER);
            }
            catch (ConnectionException findFlightException) {
                JOptionPane.showMessageDialog(null, findFlightException.getMessage(), "Problème", JOptionPane.WARNING_MESSAGE);
            }
            setVisible(true);
        }
    }

    public class ThreadListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frameContainer.removeAll();
            frameContainer.revalidate();
            frameContainer.repaint();
            frameContainer.add(new FollowedFlightJPanel(frameContainer), BorderLayout.NORTH);
            setVisible(true);
        }
    }

    public class ListeningListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frameContainer.removeAll();
            frameContainer.revalidate();
            frameContainer.repaint();
            frameContainer.add(new MenuItemAllBookings(frameContainer), BorderLayout.CENTER);
            setVisible(true);
        }
    }

    public class AddBookingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frameContainer.removeAll();
            try {
                frameContainer.add(new AddBookingJPanel(frameContainer), BorderLayout.CENTER);
            } catch(ConnectionException exception) {
                JOptionPane.showMessageDialog(null,exception.getMessage());
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


