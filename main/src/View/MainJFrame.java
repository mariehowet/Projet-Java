package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainJFrame extends JFrame {
    private Container frameContainer;
    private WelcomeJPanel welcomeJPanel;
    private JMenuBar menuBar;
    private JMenu researchMenu, findFlightMenu, monitoringFlightMenu;
    private JMenuItem research1, research2, research3, menuIemFlightMenu;

    public MainJFrame(){
        // fenetre
        super("Welcome");
        setBounds(100,50,1000,750);

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

        menuBar.add(researchMenu);
        menuBar.add(findFlightMenu);
        menuBar.add(monitoringFlightMenu);

        // Item : recherche 1
        research1 = new JMenuItem("Recherche 1");
        research1.addActionListener(new ResearchListener(1));
        researchMenu.add(research1);

        // Item : recherche 2
        research2 = new JMenuItem("Recherche 2");
        research2.addActionListener(new ResearchListener( 2));
        researchMenu.add(research2);

        // Item : recherche 3
        research3 = new JMenuItem("Recherche 3");

        research3.addActionListener(new ResearchListener(3));
        researchMenu.add(research3);

        // Find Flight
        menuIemFlightMenu = new JMenuItem("Aller à la recherche");
        menuIemFlightMenu.addActionListener(new FindFlightListener());
        findFlightMenu.add(menuIemFlightMenu);

        // Affichage
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);

        new FormJFrame();
    }

    private class ResearchListener implements ActionListener {
        int  numPanel;
        public ResearchListener(int numPanel) {
            this.numPanel = numPanel;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            frameContainer.removeAll();
            switch (numPanel) {
                case 1 :  frameContainer.add(new Research1JPanel(frameContainer), BorderLayout.CENTER);
                break;
                case 2 :  frameContainer.add(new Research2JPanel(frameContainer), BorderLayout.CENTER);
                    break;
                case 3 :  frameContainer.add(new Research3JPanel(frameContainer), BorderLayout.CENTER);
                    break;
            }
            setVisible(true);


        }
    }

    private class FindFlightListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frameContainer.removeAll();
            frameContainer.add(new FindFlight(frameContainer), BorderLayout.CENTER);
            setVisible(true);
        }
    }

}
