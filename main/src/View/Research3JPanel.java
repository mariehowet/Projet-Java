package View;

import javax.swing.*;
import java.awt.*;

public class Research3JPanel extends ResearchJPanel{
    private JLabel passenger;
    private JComboBox passengerBox;

    public Research3JPanel(Container frameContainer) {
        super(frameContainer);

        // Label
        passenger = new JLabel("Passager ");

        // ComboBox
        //  !!! BD !!!
         String [] values = new String[]{"ParMelJ", "HowMarD","ParMelJ", "HowMarD","ParMelJ", "HowMarD"};
         passengerBox = new JComboBox(values);

        // Panel de recherche
        researchPanel.add(passenger);
        researchPanel.add(passengerBox);
        researchPanel.add(researchButton);
        this.add(researchPanel, BorderLayout.NORTH);

        // Panel d'affichage
        //  !!! BD !!!
        String [][] data = new String[][]{
                { "ID Booking" , "Date Booking", "Numéro vol", 	"Aéroport départ", 	"Aéroport arrivée", " Date départ", "Type Siège", "Prix Siège",	"Prix Vol",	"Prix Total"},
                { "ID Booking" , "Date Booking", "Numéro vol", 	"Aéroport départ", 	"Aéroport arrivée", " Date départ", "Type Siège", "Prix Siège",	"Prix Vol",	"Prix Total"},
                { "ID Booking" , "Date Booking", "Numéro vol", 	"Aéroport départ", 	"Aéroport arrivée", " Date départ", "Type Siège", "Prix Siège",	"Prix Vol",	"Prix Total"},
                { "ID Booking" , "Date Booking", "Numéro vol", 	"Aéroport départ", 	"Aéroport arrivée", " Date départ", "Type Siège", "Prix Siège",	"Prix Vol",	"Prix Total"},
                { "ID Booking" , "Date Booking", "Numéro vol", 	"Aéroport départ", 	"Aéroport arrivée", " Date départ", "Type Siège", "Prix Siège",	"Prix Vol",	"Prix Total"},
                { "ID Booking" , "Date Booking", "Numéro vol", 	"Aéroport départ", 	"Aéroport arrivée", " Date départ", "Type Siège", "Prix Siège",	"Prix Vol",	"Prix Total"},
                { "ID Booking" , "Date Booking", "Numéro vol", 	"Aéroport départ", 	"Aéroport arrivée", " Date départ", "Type Siège", "Prix Siège",	"Prix Vol",	"Prix Total"},
                { "ID Booking" , "Date Booking", "Numéro vol", 	"Aéroport départ", 	"Aéroport arrivée", " Date départ", "Type Siège", "Prix Siège",	"Prix Vol",	"Prix Total"},
                { "ID Booking" , "Date Booking", "Numéro vol", 	"Aéroport départ", 	"Aéroport arrivée", " Date départ", "Type Siège", "Prix Siège",	"Prix Vol",	"Prix Total"},
                { "ID Booking" , "Date Booking", "Numéro vol", 	"Aéroport départ", 	"Aéroport arrivée", " Date départ", "Type Siège", "Prix Siège",	"Prix Vol",	"Prix Total"},

        };
        String [] columnNames = new String[]{ "ID Booking" , "Date Booking", "Numéro vol", 	"Aéroport départ", 	"Aéroport arrivée", " Date départ", "Type Siège", "Prix Siège",	"Prix Vol",	"Prix Total"} ;
        answersJPanel = new AnswersJPanel(data, columnNames);

    }
}
