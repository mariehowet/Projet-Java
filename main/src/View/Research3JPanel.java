package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Research3JPanel extends ResearchJPanel{
    private JLabel passenger;
    private JComboBox passengerBox;
    private JButton delete, modification;


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
        researchButton.addActionListener(new ResearchListener(this)); // redéfinis son évenement
        this.add(researchPanel, BorderLayout.NORTH);

        // Panel d'affichage
        //  !!! BD !!!
        String [][] data = new String[][]{ // devra etre du JTable grace à la BD
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
            Panel buttonsPanel = new Panel();
            modification = new JButton("Modification");
            buttonsPanel.add(modification);
            delete = new JButton("Suppression");
            buttonsPanel.add(delete);
            jpanel.add(buttonsPanel, BorderLayout.SOUTH);
        }
    }
}
