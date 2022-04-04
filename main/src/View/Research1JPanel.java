package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.GenericDeclaration;

public class Research1JPanel extends JPanel {
    private JPanel researchPanel, displayPanel;
    private JLabel startDateLabel, endDateLabel, destinationAirportLabel, hello;
    private JSpinner startDateSpinner, endDateSpinner;
    private JComboBox destinationAirportBox;
    private JButton researchButton;
    private Container frameContainer;

    public Research1JPanel(Container frameContainer) {
        this.frameContainer = frameContainer;

        // Labels
        startDateLabel = new JLabel("Date de début ");
        endDateLabel = new JLabel("Date de fin ");
        destinationAirportLabel = new JLabel("Aéroport de destination ");

        // Spinners
        startDateSpinner = new JSpinner(new SpinnerDateModel());
        endDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(startDateSpinner, "dd/MM/yy");
        JSpinner.DateEditor editor1 = new JSpinner.DateEditor(startDateSpinner, "dd/MM/yy");
        startDateSpinner.setEditor(editor);
        endDateSpinner.setEditor(editor1);

        // ComboBox
        String [] values = {"NY34 - JFK New York", "NY34 - JFK New York"}; // !!! BD !!!
        destinationAirportBox = new JComboBox(values);

        // Button
        researchButton = new JButton("Rechercher");
        researchButton.addActionListener(new ResearchListener());

        // Panel de recherche
        researchPanel = new JPanel();
        researchPanel.add(startDateLabel);
        researchPanel.add(startDateSpinner);
        researchPanel.add(endDateLabel);
        researchPanel.add(endDateSpinner);
        researchPanel.add(destinationAirportLabel);
        researchPanel.add(destinationAirportBox);
        researchPanel.add(researchButton);

        // Panel d'affichage
        displayPanel = new JPanel();
        hello = new JLabel(" hellooooooooooooooooooooooooooooo");
        displayPanel.add(hello);

        // Affichage
        setBounds(100,100,1000,750);
        setLayout(new BorderLayout());
        //setVisible(true);

        // Affichage des panels
        researchPanel.setLayout(new FlowLayout());
        frameContainer.add(researchPanel, BorderLayout.NORTH);
        researchPanel.setVisible(true);
    }

    //Problème affichage panel d'affichage
    private class ResearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            displayPanel.setLayout(new FlowLayout());
            frameContainer.add(displayPanel, BorderLayout.SOUTH);
            displayPanel.setVisible(true);
        }
    }
}
