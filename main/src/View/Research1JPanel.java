package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Research1JPanel extends ResearchJPanel {
    private JLabel startDateLabel, endDateLabel, destinationAirportLabel;
    private JSpinner startDateSpinner, endDateSpinner;
    private JComboBox destinationAirportBox;

    public Research1JPanel(Container frameContainer) {
        super(frameContainer);
        // Labels
            startDateLabel = new JLabel("Date de début ");
            endDateLabel = new JLabel("Date de fin ");
            destinationAirportLabel = new JLabel("Aéroport de destination ");

        // Spinners
            startDateSpinner = new JSpinner(new SpinnerDateModel());
            endDateSpinner = new JSpinner(new SpinnerDateModel());

            JSpinner.DateEditor startEditor = new JSpinner.DateEditor(startDateSpinner, "dd/MM/yy");
            JSpinner.DateEditor endEditor = new JSpinner.DateEditor(endDateSpinner, "dd/MM/yy");
            startDateSpinner.setEditor(startEditor);
            endDateSpinner.setEditor(endEditor);

        // ComboBox
            String [] values = new String[]{"NY34 - JFK New York", "NY34 - JFK New York"}; // !!! BD !!!
            destinationAirportBox = new JComboBox(values);

        // Panel de recherche
            researchPanel.add(startDateLabel);
            researchPanel.add(startDateSpinner);
            researchPanel.add(endDateLabel);
            researchPanel.add(endDateSpinner);
            researchPanel.add(destinationAirportLabel);
            researchPanel.add(destinationAirportBox);
            researchPanel.add(researchButton);
            this.add(researchPanel, BorderLayout.NORTH);

        // Panel d'affichage
            //  !!! BD !!!
             String [][] data = new String[][]{
                    { "1Numéro vol", "Aéroport départ", "Date départ", "Date Arrivée", "Nb de places restantes" },
                    { "2Numéro vol", "Aéroport départ", "Date départ", "Date Arrivée", "Nb de places restantes" },
                    {"3Numéro vol", "Aéroport départ", "Date départ", "Date Arrivée", "Nb de places restantes" },
                    { "4Numéro vol", "Aéroport départ", "Date départ", "Date Arrivée", "Nb de places restantes" },
                    { "5Numéro vol", "Aéroport départ", "Date départ", "Date Arrivée", "Nb de places restantes" },
                    { "6Numéro vol", "Aéroport départ", "Date départ", "Date Arrivée", "Nb de places restantes" },
                    { "7Numéro vol", "Aéroport départ", "Date départ", "Date Arrivée", "Nb de places restantes" },
                    { "8Numéro vol", "Aéroport départ", "Date départ", "Date Arrivée", "Nb de places restantes" }
            };
            String [] columnNames = new String[]{ "Numéro vol", "Aéroport départ", "Date départ", "Date Arrivée", "Nb de places restantes                                                                     "}  ;
            answersJPanel = new AnswersJPanel(data, columnNames);
    }
}
