package View;

import javax.swing.*;
import java.awt.*;

public class FindFlight extends ResearchJPanel{
    private JLabel departureCity, arrivalCity, startDateLabel, endDateLabel;
    private JComboBox departureCityBox, arrivalCityBox;
    private JSpinner startDateSpinner, endDateSpinner;
    public FindFlight(Container frameContainer) {
        super(frameContainer);
        // Labels
        departureCity = new JLabel("Ville de départ ");
        arrivalCity = new JLabel("Ville de destination ");

        // ComboBox
        //  !!! BD !!!
        String []values =  {"Bruxelles - 1000 - Belgique", "Paris", "Amsterdam", "Berlin", "Londres", "Barcerlone", "Madrid", "Rome", "Compenhague"};
        departureCityBox = new JComboBox(values);
        arrivalCityBox = new JComboBox(values);


        // Labels
        startDateLabel = new JLabel("Date de début ");
        endDateLabel = new JLabel("Date de fin ");

        // Spinners
        startDateSpinner = new JSpinner(new SpinnerDateModel());
        endDateSpinner = new JSpinner(new SpinnerDateModel());

        JSpinner.DateEditor startEditor = new JSpinner.DateEditor(startDateSpinner, "dd/MM/yy");
        JSpinner.DateEditor endEditor = new JSpinner.DateEditor(endDateSpinner, "dd/MM/yy");
        startDateSpinner.setEditor(startEditor);
        endDateSpinner.setEditor(endEditor);


        // Panel de recherche
        researchPanel.add(departureCity);
        researchPanel.add(departureCityBox);
        researchPanel.add(arrivalCity);
        researchPanel.add(arrivalCityBox);
        researchPanel.add(startDateLabel);
        researchPanel.add(startDateSpinner);
        researchPanel.add(endDateLabel);
        researchPanel.add(endDateSpinner);
        researchPanel.add(researchButton);
        this.add(researchPanel, BorderLayout.NORTH);

        // Panel d'affichage
        //  !!! BD !!!
        String [][] data = new String[][]{
                { "Numéro vol",	 "Aéroport départ",	"Aéroport arrivée", "Date départ", " Date Arrivée", "Heure départ", "Heure d'arrivée", "Prix du vol"},
                { "Numéro vol",	 "Aéroport départ",	"Aéroport arrivée", "Date départ", " Date Arrivée", "Heure départ", "Heure d'arrivée", "Prix du vol"},
                { "Numéro vol",	 "Aéroport départ",	"Aéroport arrivée", "Date départ", " Date Arrivée", "Heure départ", "Heure d'arrivée", "Prix du vol"},
                { "Numéro vol",	 "Aéroport départ",	"Aéroport arrivée", "Date départ", " Date Arrivée", "Heure départ", "Heure d'arrivée", "Prix du vol"},
                { "Numéro vol",	 "Aéroport départ",	"Aéroport arrivée", "Date départ", " Date Arrivée", "Heure départ", "Heure d'arrivée", "Prix du vol"},
                { "Numéro vol",	 "Aéroport départ",	"Aéroport arrivée", "Date départ", " Date Arrivée", "Heure départ", "Heure d'arrivée", "Prix du vol"},
                { "Numéro vol",	 "Aéroport départ",	"Aéroport arrivée", "Date départ", " Date Arrivée", "Heure départ", "Heure d'arrivée", "Prix du vol"},
                { "Numéro vol",	 "Aéroport départ",	"Aéroport arrivée", "Date départ", " Date Arrivée", "Heure départ", "Heure d'arrivée", "Prix du vol"},
        };
        String [] columnNames = new String[]{ "Numéro vol",	 "Aéroport départ",	"Aéroport arrivée", "Date départ", " Date Arrivée", "Heure départ", "Heure d'arrivée", "Prix du vol"} ;
        answersJPanel = new AnswersJPanel(data, columnNames);


    }
}
