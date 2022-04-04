package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainJFrame extends JFrame {
    private Container frameContainer;
    private WelcomeJPanel welcomeJPanel;
    private JMenuBar menuBar;
    private JMenu menu1, menu2, menu3;
    private JMenuItem item1, item2, item3;

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
        menu1 = new JMenu("Menu 1");
        menu2 = new JMenu("Menu 2");
        menu3 = new JMenu("Menu 3");
        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);

        // Items menu 1
        item1 = new JMenuItem("Item 1");
        item2 = new JMenuItem("Item 1");
        item3 = new JMenuItem("Item 1");
        menu1.add(item1);
        menu1.add(item2);
        menu1.add(item3);

        // Affichage
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);
    }
}
