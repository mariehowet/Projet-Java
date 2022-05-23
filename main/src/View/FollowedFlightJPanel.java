package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FollowedFlightJPanel extends JPanel{
    private JButton researchButton;
    private JLabel label;

    private JPanel researchPanel, displayPanel;


    public FollowedFlightJPanel(){
        setLayout(new BorderLayout());

        label = new JLabel("<html><h3>Suivez en direct le vol Paris -> New-York</h3><html>");
        researchButton = new JButton("Afficher");
        researchButton.addActionListener(new ResearchListener());

        researchPanel = new JPanel(new FlowLayout());
        researchPanel.add(label);
        researchPanel.add(researchButton);

        displayPanel = new JPanel(new FlowLayout());
        JPanel test = new JPanel();
        JLabel test2 = new JLabel("");
        test.add(test2);

        add(researchPanel, BorderLayout.NORTH);
        add(displayPanel, BorderLayout.CENTER);
        add(test, BorderLayout.SOUTH);
    }

    private class ResearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            displayPanel.removeAll();
            displayPanel.revalidate();
            displayPanel.repaint();
            displayPanel.add(new ThreadJPanel());
        }
    }
}
