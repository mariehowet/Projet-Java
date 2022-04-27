package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FollowedFlightJPanel extends ResearchJPanel {
    private JButton researchButton;
    private JLabel flightNumberLabel;
    private JTextField flightNumber;

    public FollowedFlightJPanel(Container frameContainer){
        super(frameContainer);

        // Button
        researchButton = new JButton("Rechercher");

        // JLabel
        flightNumberLabel = new JLabel("Numéro de vol ");
        flightNumber = new JTextField(10);

        // Panel de recherche
        researchPanel.add(flightNumberLabel);
        researchPanel.add(flightNumber);
        researchPanel.add(researchButton);
        researchButton.addActionListener(new ResearchListener(this));
        add(researchPanel, BorderLayout.NORTH);

    }

    private class ResearchListener implements ActionListener {
        JPanel jpanel;
        public ResearchListener(JPanel jpanel) {
            this.jpanel = jpanel;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            frameContainer.revalidate();
            frameContainer.repaint();
            jpanel.add(new ThreadJPanel(), BorderLayout.CENTER);
        }
    }
}
