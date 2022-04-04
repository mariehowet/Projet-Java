package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainJFrame extends JFrame {
    private Container frameContainer;
    private WelcomeJPanel welcomeJPanel;
    private JMenuBar menuBar;
    private JMenu researchMenu, findFlightMenu, monitoringFlightMenu;
    private JMenuItem research1, research2, research3;
    private Research1JPanel research1JPanel;

    public MainJFrame() {
        // fenetre
        super("Welcome");
        setBounds(100,100,1000,750);

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
        research1.addActionListener(new Research1Listener());
        researchMenu.add(research1);

        // Items menu recherches

        research2 = new JMenuItem("Recherche 2");
        research3 = new JMenuItem("Recherche 3");

        researchMenu.add(research2);
        researchMenu.add(research3);


        // Affichage
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);
    }

    private class Research1Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frameContainer.removeAll();
            research1JPanel = new Research1JPanel(frameContainer);
            frameContainer.add(research1JPanel, BorderLayout.CENTER);
            setVisible(true);
        }
    }
}
