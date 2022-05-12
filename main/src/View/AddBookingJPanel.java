package View;

import Controller.ApplicationController;
import Exception.*;
import Model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddBookingJPanel extends JPanel {
    private JLabel title;
    private JButton validationButton, calculateButton, searchSeatButton;
    private JPanel formPanel, buttonPanel;
    private JLabel idPassengerLabel, seatTypeLabel, weightLuggageLabel, companyNameLabel, mealTypeLabel, totalPriceLabel, flightLabel, seatLabel;
    private JTextField  companyName, totalPriceText;
    private JRadioButton buttonYesLuggage, buttonNoLuggage, buttonYesBusinessFlight, buttonNoBusinessFlight, buttonPayNow, buttonPayAfter;
    private JComboBox seatTypeBox, weightLuggageBox, mealTypeBox, passengerBox, flightBox, seatBox;
    private ButtonGroup hasLuggage, isBusinessFlight, payment;
    private ApplicationController controller;
    private Double flightPrice = 0.0, seatTypePrice = 0.0, luggagePrice = 0.0, totalPrice;
    private ArrayList<Double> flightPrices;
    private ArrayList<Integer> flightIDs, passengerIDs, seatIDs, airplaneIDS;
    private ArrayList<String> seatTypeIDs;
    private String seatTypeName;
    private int airplaneID;
    private  String [] seatValues = {""};
    private  ArrayList<Seat> seatList ;
    private Container frameContainer;

    public AddBookingJPanel(Container frameContainer) throws ConnectionException{
        this.frameContainer = frameContainer;
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(0,50,50,50));
        title = new JLabel("<html><h1 style='margin: 30px 0 15px 0'>Créer une réservation</h1></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        this.controller  = new ApplicationController();

        //----------------FormPanel--------------------------------------------------
        formPanel = new JPanel();
        formPanel.setBorder(new EmptyBorder(25, 150, 25, 150));
        formPanel.setLayout(new GridLayout(11,2));

        //------------------Flight----------------------------------
        flightLabel = new JLabel("Votre vol :");
        formPanel.add(flightLabel);
        String [] flightValues;
        try {
            ArrayList<Flight> flightList = controller.getAllFlights();
            flightIDs = new ArrayList<>();
            flightPrices = new ArrayList<>();
            airplaneIDS = new ArrayList<>();

            for(Flight fl : flightList) {
                flightIDs.add(fl.getId());
                flightPrices.add(fl.getPrice());
                airplaneIDS.add(fl.getAirplaneId());
            }
            int nb = flightIDs.size();
            flightValues = new String[nb];

            for (int j = 0; j < nb; j++) {
                flightValues[j] = flightIDs.get(j).toString();
            }

            flightBox = new JComboBox(flightValues);
            formPanel.add(flightBox);

        } catch (AllFlightsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //--------------------Passenger------------------------------
        idPassengerLabel = new JLabel("Votre nom : ");
        formPanel.add(idPassengerLabel);
        String [] passengersValues;
        try {
            ArrayList<Passenger> passengerList = controller.getAllPassengers();
            ArrayList<String> passengers = new ArrayList<>();
            passengerIDs = new ArrayList<>();

            for(Passenger pas : passengerList) {
                passengers.add(pas.getFirstName() + " " + pas.getLastName() + (pas.getInitialMiddleName() != null ? pas.getInitialMiddleName() : "" ));
                passengerIDs.add(pas.getId());
            }
            int nbPassengers = passengers.size();
            passengersValues = new String[nbPassengers];

            for (int j = 0; j < nbPassengers; j++) {
                passengersValues[j] = passengers.get(j);
            }

            passengerBox = new JComboBox(passengersValues);
            formPanel.add(passengerBox);

        } catch (PassengerException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //--------------------SeatType------------------------------
        seatLabel = new JLabel("Type de siège : ");
        formPanel.add(seatLabel);
        String[] seatTypesValues;
        try {
            ArrayList<SeatType> seatTypeList = controller.getAllSeatTypes();
            ArrayList<String> seatTypes = new ArrayList<>();
            seatTypeIDs = new ArrayList<>();

            for(SeatType st : seatTypeList) {
                seatTypes.add(st.getName() + " " + "(+ " + st.getAdditionalPrice() + "€)");
                seatTypeIDs.add(st.getName());
            }

            int nbSeatTypes = seatTypes.size();
            seatTypesValues = new String[nbSeatTypes];

            for (int j = 0; j < nbSeatTypes; j++) {
                seatTypesValues[j] = seatTypes.get(j);
            }

            seatTypeBox = new JComboBox(seatTypesValues);
            //seatType.setSelectedItem(seatTypes[1]);
            formPanel.add(seatTypeBox);

        } catch (SeatTypeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }


        //-------------------------Seat--------------------------------------
        seatLabel = new JLabel("Votre siège :");
        formPanel.add(seatLabel);


        seatBox = new JComboBox(seatValues);
        seatBox.setEnabled(false);
        formPanel.add(seatBox);

        //--------------------Luggage------------------------------------------
        hasLuggage = new ButtonGroup();
        buttonYesLuggage = new JRadioButton("Je dispose de bagages");
        formPanel.add(buttonYesLuggage);
        buttonNoLuggage = new JRadioButton("Je ne dispose pas de bagages");
        LuggageListener luggageListener = new LuggageListener();
        buttonNoLuggage.addItemListener(luggageListener);
        buttonYesLuggage.setSelected(true);
        formPanel.add(buttonNoLuggage);
        hasLuggage.add(buttonYesLuggage);
        hasLuggage.add(buttonNoLuggage);


        //-----------------------------Weight---------------------------------------------
        weightLuggageLabel = new JLabel("Poids : ");
        formPanel.add(weightLuggageLabel);
        String[] weights = {"","0 < 10 kg (+0€)","10 < 20 kg (+10€)","20 < 30 kg (+20€)","Max 35 kg (+25€)"};
        weightLuggageBox = new JComboBox(weights);
        formPanel.add(weightLuggageBox);


        //--------------------------------Business------------------------------------------
        isBusinessFlight = new ButtonGroup();
        buttonYesBusinessFlight = new JRadioButton("Je voyage pour affaire");
        formPanel.add(buttonYesBusinessFlight);
        buttonNoBusinessFlight = new JRadioButton("Je ne voyage pas pour affaire");
        CompanyListener companyListener = new CompanyListener();
        buttonNoBusinessFlight.addItemListener(companyListener);
        buttonYesBusinessFlight.setSelected(true);
        formPanel.add(buttonNoBusinessFlight);
        isBusinessFlight.add(buttonYesBusinessFlight);
        isBusinessFlight.add(buttonNoBusinessFlight);

        companyNameLabel = new JLabel("Nom de la société : ");
        formPanel.add(companyNameLabel);
        companyName = new JTextField();
        formPanel.add(companyName);

        //----------------------------Meal----------------------------------------------
        mealTypeLabel = new JLabel("Type de repas : ");
        formPanel.add(mealTypeLabel);
        String[] mealTypes = {"Poulet","Boeuf","Végétarien","Porc"};
        mealTypeBox = new JComboBox(mealTypes);
        //mealType.setMaximumRowCount(2);
        formPanel.add(mealTypeBox);

        //------------------------------payement--------------------------------------------
        payment = new ButtonGroup();
        buttonPayNow = new JRadioButton("Je paye maintenant");
        formPanel.add(buttonPayNow);
        buttonPayAfter = new JRadioButton("Je paye le jour du départ");
        buttonPayNow.setSelected(true);
        formPanel.add(buttonPayAfter);
        payment.add(buttonPayNow);
        payment.add(buttonPayAfter);

        //------------------------------Price--------------------------------------------
        totalPriceLabel = new JLabel("Prix total en €: ");
        formPanel.add(totalPriceLabel);
        totalPriceText = new JTextField("0");
        totalPriceText.setEnabled(false);
        formPanel.add(totalPriceText);


        //--------------------ButtonPanel-----------------------------------
        searchSeatButton = new JButton("Chercher les places disponibles");
        calculateButton = new JButton("Calculer le prix");
        validationButton = new JButton("Validation");
        searchSeatButton.addActionListener(new SearchSeatListener());
        validationButton.addActionListener(new ValidationListener());
        validationButton.addActionListener(new CalculateListener());
        calculateButton.addActionListener( new CalculateListener());
        //validationButton.setEnabled(false);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(searchSeatButton);
        buttonPanel.add(calculateButton);
        buttonPanel.add(validationButton);

        this.add(title, BorderLayout.NORTH);
        this.add(formPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private class SearchSeatListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            /*
            try {

                seatTypeName = seatTypeIDs.get(seatTypeBox.getSelectedIndex());
                airplaneID = airplaneIDS.get(flightBox.getSelectedIndex());
                seatList = controller.getAvailableSeats(seatTypeName, airplaneID);
                ArrayList<String> seats = new ArrayList<>();

                seatIDs = new ArrayList<>();

                for(Seat st : seatList) {
                    seats.add(st.getNumber() + st.getColumnLetter());
                    seatIDs.add(st.getId());
                }
                int nb = seats.size();
                seatValues = new String[nb];

                for (int j = 0; j < nb; j++) {
                    seatValues[j] = seats.get(j);
                }
                seatBox.setSelectedItem(seatValues);
                seatBox.setEnabled(true);
            } catch (AvailableSeatsException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            */
            String seatValues[] = {"1", "2", "3"};
            seatBox.setSelectedItem(seatValues);
            seatBox.setEnabled(true);
        }
    }

    private class CalculateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Pattern patternSTPrice = Pattern.compile("(\\d+)", Pattern.CASE_INSENSITIVE);
            Matcher matcherST = patternSTPrice.matcher(seatTypeBox.getSelectedItem().toString());

            Pattern patternWLPrice = Pattern.compile("(\\d+)€", Pattern.CASE_INSENSITIVE);
            Matcher matcherWL = patternWLPrice.matcher(weightLuggageBox.getSelectedItem().toString());

            flightPrice = flightPrices.get(flightBox.getSelectedIndex());
            if(matcherST.find())
                seatTypePrice = Double.parseDouble(matcherST.group(1));
            if(matcherWL.find())
                luggagePrice = Double.parseDouble(matcherWL.group(1));
            totalPrice = flightPrice + seatTypePrice + luggagePrice;
            totalPriceText.setText(totalPrice + "");
        }
    }

    private class ValidationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(buttonYesLuggage.isSelected() && weightLuggageBox.getSelectedItem().toString() == ""){
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner le poids de vos bagages", "Problème", JOptionPane.WARNING_MESSAGE);

            }else if (buttonYesBusinessFlight.isSelected() && companyName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vous devez entrer le nom de votre société", "Problème", JOptionPane.WARNING_MESSAGE);

            } else {
                String luggageWeight = null;
                if (buttonYesLuggage.isSelected()) {
                    Pattern patternLuggage = Pattern.compile("(^.*kg)", Pattern.CASE_INSENSITIVE);
                    Matcher matcher = patternLuggage.matcher(weightLuggageBox.getSelectedItem().toString());
                    if (matcher.find())
                        luggageWeight = matcher.group(1);
                }
                String company_name = buttonYesBusinessFlight.isSelected() ? companyName.getText() : null;
                Booking booking = new Booking(
                        buttonPayNow.isSelected(), //has_paid
                        luggageWeight,//luggage_weight
                        company_name, // companyName
                        mealTypeBox.getSelectedItem().toString(),  // mealType
                        Double.parseDouble(totalPriceText.getText()), // real_price
                        flightIDs.get(flightBox.getSelectedIndex()), // flight_id
                        seatIDs.get(seatBox.getSelectedIndex()), // seat_id
                        passengerIDs.get(passengerBox.getSelectedIndex()) // passenger_id
                );

                try {
                    controller.addBooking(booking);
                    JOptionPane.showMessageDialog(null, "Ajout effectué avec succès");
                    frameContainer.removeAll();
                    frameContainer.revalidate();
                    frameContainer.repaint();
                    frameContainer.add(new AddBookingJPanel(frameContainer));
                } catch (AddBookingException | ConnectionException exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        }
    }

    private class LuggageListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                weightLuggageBox.setEnabled(false);
                weightLuggageBox.setSelectedItem("");
            } else {
                weightLuggageBox.setEnabled(true);
            }
        }
    }

    private class CompanyListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                companyName.setEnabled(false);
                companyName.setText("");
            } else {
                companyName.setEnabled(true);
            }
        }
    }
}


