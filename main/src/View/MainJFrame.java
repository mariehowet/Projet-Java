package View;

import Controller.ApplicationController;
import Model.Booking;
import Exception.*;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.GregorianCalendar;


public class MainJFrame extends JFrame {
    private Container frameContainer;
    private WelcomeJPanel welcomeJPanel;
    private JMenuBar menuBar;
    private JMenu researchMenu, findFlightMenu, monitoringFlightMenu, form;
    private JMenuItem research1, research2, research3, menuIemFlightMenu, monitoringFlight, booking;
    private JPanel BookingForm;

    public MainJFrame(){
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
        researchMenu = new JMenu("Recherches");
        findFlightMenu = new JMenu("Trouver un vol");
        monitoringFlightMenu = new JMenu("Suivi d'un vol");
        form = new JMenu("Formulaire");


        menuBar.add(researchMenu);
        menuBar.add(findFlightMenu);
        menuBar.add(monitoringFlightMenu);
        menuBar.add(form);

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

        // Form
        booking = new JMenuItem("Réservation d'un vol");
        booking.addActionListener(new JMenuItemListener(6));
        form.add(booking);

        // Ajout des données dans la BD
        BookingForm = new BookingForm();

        // Listing des réservations
        /*ApplicationController controller = new ApplicationController();

        try {
           ArrayList<Booking> bookings = controller.getAllBookings();
            for (Booking b: bookings) {
                System.out.println(b);
            }
        } catch (AllBookingsException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());

        }
*/

        // Affichage
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
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
                    frameContainer.add(new BookingCreationPanel(), BorderLayout.CENTER);
                    break;
            }
            setVisible(true);
        }
    }

}


