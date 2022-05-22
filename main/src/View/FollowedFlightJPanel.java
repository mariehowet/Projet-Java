package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FollowedFlightJPanel extends JPanel{
    private JButton researchButton;
    private JLabel label;
    private Container frameContainer;


    public FollowedFlightJPanel(Container frameContainer){
        this.frameContainer = frameContainer;

        // Button
        researchButton = new JButton("Afficher");

        // JLabel
        label = new JLabel("<html><h3>Suivez en direct le vol Paris -> New-York</h3><html>");
        setLayout(new FlowLayout());
        // Panel de recherche
        this.add(label);
        this.add(researchButton);
        researchButton.addActionListener(new ResearchListener());
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
