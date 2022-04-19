package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThreadJFrame extends JFrame {
    private JPanel researchPanel;
    private JButton researchButton;
    private JLabel flightNumberLabel;
    private JTextField flightNumber;
    private Container frameContainer;

    public ThreadJFrame(){
        // fenetre
        super("Suivi d'un vol");
        setBounds(300,0,1000,1000);

        frameContainer = this.getContentPane();
        frameContainer.setLayout(new BorderLayout());

        // JLabel
        flightNumberLabel = new JLabel("Numéro de vol ");
        flightNumber = new JTextField(10);

        // Button
        researchButton = new JButton("Rechercher");
        researchButton.addActionListener(new ResearchListener());

        // Panel de recherche
        researchPanel = new JPanel();
        researchPanel.add(flightNumberLabel);
        researchPanel.add(flightNumber);
        researchPanel.add(researchButton);

        researchPanel.setLayout(new FlowLayout());
        researchPanel.setVisible(true);

        frameContainer.add(researchPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    private class ResearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frameContainer.revalidate();
            frameContainer.repaint();
            frameContainer.add(new ThreadJPanel(), BorderLayout.CENTER);
        }
    }
}
