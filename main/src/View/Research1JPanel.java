package View;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import Exception.*;


public class Research1JPanel extends ResearchJPanel {
    private JLabel startDateLabel, endDateLabel, departureAirportLabel;
    private JDateChooser chooser, chooser2;

    private JSpinner startDateSpinner, endDateSpinner;
    private JComboBox departureAirports;

    public Research1JPanel(Container frameContainer) {
        super(frameContainer);
        // Labels
            startDateLabel = new JLabel("Date de début ");
            endDateLabel = new JLabel("Date de fin ");
            departureAirportLabel = new JLabel("Aéroport de départ ");

        // Spinners
            startDateSpinner = new JSpinner(new SpinnerDateModel());
            endDateSpinner = new JSpinner(new SpinnerDateModel());

            JSpinner.DateEditor startEditor = new JSpinner.DateEditor(startDateSpinner, "dd/MM/yy");
            JSpinner.DateEditor endEditor = new JSpinner.DateEditor(endDateSpinner, "dd/MM/yy");
            startDateSpinner.setEditor(startEditor);
            endDateSpinner.setEditor(endEditor);

        // ComboBox
            String [] values = new String[]{"NY34 - JFK New York", "NY34 - JFK New York"}; // !!! BD !!!
            departureAirports = new JComboBox(values);


        // Panel de recherche
            researchPanel.add(startDateLabel);
            researchPanel.add(startDateSpinner);
            researchPanel.add(endDateLabel);
            researchPanel.add(endDateSpinner);
            researchPanel.add(departureAirportLabel);
            researchPanel.add(departureAirports);
            researchPanel.add(researchButton);


            chooser = new JDateChooser();
            chooser.setLocale(Locale.FRENCH);

            JPanel panel = new JPanel();
            panel.add(new JLabel("Date 1:"));
            panel.add(chooser);

            chooser2 = new JDateChooser();
            chooser2.setLocale(Locale.FRENCH);

            JPanel panel2 = new JPanel();
            panel2.add(new JLabel("Date 2:"));
            panel2.add(chooser2);

            researchPanel.add(panel);
            researchPanel.add(panel2);

            researchButton = new JButton("Rechercher");
            researchButton.addActionListener(new ButtonListener());
            researchPanel.add(researchButton);

            this.add(researchPanel, BorderLayout.NORTH);





        // Panel d'affichage
            //  !!! BD !!!
             String [][] data = new String[][]{
                    { "1Numéro vol", "Aéroport arrivée", "Date départ", "Date Arrivée", "Nb de places restantes" },
                    { "2Numéro vol", "Aéroport arrivée", "Date départ", "Date Arrivée", "Nb de places restantes" },
                    {"3Numéro vol", "Aéroport arrivée", "Date départ", "Date Arrivée", "Nb de places restantes" },
                    { "4Numéro vol", "Aéroport arrivée", "Date départ", "Date Arrivée", "Nb de places restantes" },
                    { "5Numéro vol", "Aéroport arrivée", "Date départ", "Date Arrivée", "Nb de places restantes" },
                    { "6Numéro vol", "Aéroport arrivée", "Date départ", "Date Arrivée", "Nb de places restantes" },
                    { "7Numéro vol", "Aéroport arrivée", "Date départ", "Date Arrivée", "Nb de places restantes" },
                    { "8Numéro vol", "Aéroport arrivée", "Date départ", "Date Arrivée", "Nb de places restantes" }
            };
            String [] columnNames = new String[]{ "Numéro vol", "Aéroport arrivée", "Date départ", "Date Arrivée", "Nb de places restantes                                                                     "}  ;
            answersJPanel = new AnswersJPanel(data, columnNames);
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                if (chooser.getDate().after(chooser2.getDate())) {
                    throw new DatesException();

                }
                System.out.println("j'arrive bien");

            }
            catch (DatesException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Problème", JOptionPane.WARNING_MESSAGE);
            }

        }
    }


}
