package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistorySearch extends JPanel {
    JPanel researchPanel, researchDisplay;
    JButton researchButton;
    JLabel passenger;
    Container frameContainer;

    public HistorySearch() {

        this.setLayout(new BorderLayout());

        // Panel de recherche
        researchPanel = new JPanel();

        // Label
        passenger = new JLabel("Passager");

        // Button
        researchButton = new JButton("Rechercher");
        researchButton.addActionListener(new ResearchListener(this));

        // Panel de recherche
        researchPanel.add(passenger);
        researchPanel.add(researchButton);
        researchPanel.setLayout(new FlowLayout());
        researchPanel.setVisible(true);

        this.add(researchPanel, BorderLayout.NORTH);
    }

    private class ResearchListener implements ActionListener {
        JPanel jpanel;
        public ResearchListener(JPanel jpanel) {
            this.jpanel = jpanel;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            researchDisplay = new BookingsHistoryPanel();
            jpanel.add(researchDisplay, BorderLayout.CENTER);
        }
    }
}
