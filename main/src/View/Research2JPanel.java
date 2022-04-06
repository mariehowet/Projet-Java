package View;

import javax.swing.*;
import java.awt.*;

public class Research2JPanel extends ResearchJPanel {
    private JLabel departureCity, arrivalCity;
    private JComboBox departureCityBox, arrivalCityBox;
    private JRadioButton withoutStopover, withStopover;
    private ButtonGroup buttonGroup;

    public Research2JPanel(Container frameContainer) {
        super(frameContainer);

        // Labels
            departureCity = new JLabel("Ville de départ ");
            arrivalCity = new JLabel("Ville de destination ");

        // ComboBox
            String []values =  {"Bruxelles - 1000 - Belgique", "Paris", "Amsterdam", "Berlin", "Londres", "Barcerlone", "Madrid", "Rome", "Compenhague"};
            departureCityBox = new JComboBox(values);
            arrivalCityBox = new JComboBox(values);

        // RadioButton
            withoutStopover = new JRadioButton("Sans escale");
            withStopover = new JRadioButton("Avec escale");
            buttonGroup = new ButtonGroup();

            buttonGroup.add(withoutStopover);
            buttonGroup.add(withStopover);

            // Panel de recherche
            researchPanel.add(departureCity);
            researchPanel.add(departureCityBox);
            researchPanel.add(arrivalCity);
            researchPanel.add(arrivalCityBox);
            researchPanel.add(withoutStopover);
            researchPanel.add(withStopover);
            researchPanel.add(researchButton);
            this.add(researchPanel, BorderLayout.NORTH);

            // Panel d'affichage
            //  !!! BD !!!
            String [][] data = new String[][]{
                    { "Numéro vol",	 "Aéroport départ",	"Aéroport arrivée", "Date départ", " Date Arrivée"," Heure départ",  "Heure d'arrivée"},
                    { "Numéro vol",	 "Aéroport départ",	"Aéroport arrivée", "Date départ", " Date Arrivée"," Heure départ",  "Heure d'arrivée"},
                    { "Numéro vol",	 "Aéroport départ",	"Aéroport arrivée", "Date départ", " Date Arrivée"," Heure départ",  "Heure d'arrivée"},
                    { "Numéro vol",	 "Aéroport départ",	"Aéroport arrivée", "Date départ", " Date Arrivée"," Heure départ",  "Heure d'arrivée"},
                    { "Numéro vol",	 "Aéroport départ",	"Aéroport arrivée", "Date départ", " Date Arrivée"," Heure départ",  "Heure d'arrivée"},
                    { "Numéro vol",	 "Aéroport départ",	"Aéroport arrivée", "Date départ", " Date Arrivée"," Heure départ",  "Heure d'arrivée"},
                    { "Numéro vol",	 "Aéroport départ",	"Aéroport arrivée", "Date départ", " Date Arrivée"," Heure départ",  "Heure d'arrivée"},
                    { "Numéro vol",	 "Aéroport départ",	"Aéroport arrivée", "Date départ", " Date Arrivée"," Heure départ",  "Heure d'arrivée"},
            };
            String [] columnNames = new String[]{ "Numéro vol",	 "Aéroport départ",	"Aéroport arrivée", "Date départ", " Date Arrivée"," Heure départ",  "Heure d'arrivée"} ;
            answersJPanel = new AnswersJPanel(data, columnNames);

    }
}
