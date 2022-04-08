package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResearchJPanel extends JPanel {
    protected JPanel researchPanel;
    protected JButton researchButton;
    protected Container frameContainer;
    protected AnswersJPanel answersJPanel;

    public ResearchJPanel(Container frameContainer) {
        this.frameContainer = frameContainer;

        // Panel de recherche
        researchPanel = new JPanel();

        // Button
        researchButton = new JButton("Rechercher");
        researchButton.addActionListener(new ResearchListener(this));

        // Panel de recherche
        researchPanel.setLayout(new FlowLayout());
        researchPanel.setVisible(true);


        // Affichage
        this.setBounds(100,100,1000,750);
        setLayout(new BorderLayout());
        setVisible(true);
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
            jpanel.add(answersJPanel, BorderLayout.CENTER);

        }
    }
}
