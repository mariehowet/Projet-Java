package View;

import Controller.ApplicationController;
import Model.*;
import Exception.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateBookingJPanel extends JPanel {
    private JLabel title, companyNameLabel, mealTypeLabel, totalPriceLabel;
    private JPanel formPanel, buttonPanel;
    private JLabel flightLabel, passengerlabel, seatLabel, weightLuggageLabel;
    private JTextField flightText, passengerText, companyName, totalPriceText;
    private JComboBox seatTypeBox, weightLuggageBox, mealTypeBox;
    private ButtonGroup hasLuggage, isBusinessFlight, payment;
    private JRadioButton buttonYesLuggage, buttonNoLuggage, buttonYesBusinessFlight, buttonNoBusinessFlight, buttonPayNow, buttonPayAfter;
    private ApplicationController controller;
    private Container frameContainer;
    private JButton searchSeatButton, validationButton, calculateButton;
    private Double flightPrice , seatTypePrice , luggagePrice, totalPrice;
    private int bookingID;


    public UpdateBookingJPanel(Container frameContainer, Booking booking) throws ConnectionException {
        this.frameContainer = frameContainer;
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(0, 50, 50, 50));
        title = new JLabel("<html><h1 style='margin: 30px 0 15px 0'>Modifier une réservation</h1></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        this.controller = new ApplicationController();
        bookingID = booking.getId();

        //----------------FormPanel--------------------------------------------------
        formPanel = new JPanel();
        formPanel.setBorder(new EmptyBorder(25, 150, 25, 150));
        formPanel.setLayout(new GridLayout(11, 2));

        //------------------Flight----------------------------------
        flightLabel = new JLabel("Votre vol :");
        formPanel.add(flightLabel);
        flightText = new JTextField(booking.getFlightID() + "");
        flightText.setEnabled(false);
        formPanel.add(flightText);


        //--------------------Passenger------------------------------

        passengerlabel = new JLabel("Votre nom : ");
        formPanel.add(passengerlabel);
        try {
            ArrayList<Passenger> passengerList = controller.getAllPassengers();

            for (Passenger pas : passengerList) {
                if (pas.getId() == booking.getPassengerID()) {
                    passengerText = new JTextField(pas.getFirstName() + " " + pas.getLastName() + (pas.getInitialMiddleName() != null ? pas.getInitialMiddleName() : ""));
                }
            }
            passengerText.setEnabled(false);
            formPanel.add(passengerText);
        } catch (PassengerException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //--------------------SeatType------------------------------

        //-------------------------Seat--------------------------------------

        //--------------------Luggage------------------------------------------
        hasLuggage = new ButtonGroup();
        buttonYesLuggage = new JRadioButton("Je dispose de bagages");
        formPanel.add(buttonYesLuggage);
        buttonNoLuggage = new JRadioButton("Je ne dispose pas de bagages");
        LuggageListener luggageListener = new LuggageListener();
        buttonNoLuggage.addItemListener(luggageListener);
        if (booking.getLuggageWeight() != null)
            buttonYesLuggage.setSelected(true);
        else {
            buttonYesLuggage.setSelected(false);
        }
        formPanel.add(buttonNoLuggage);
        hasLuggage.add(buttonYesLuggage);
        hasLuggage.add(buttonNoLuggage);

        //-----------------------------Weight---------------------------------------------
        weightLuggageLabel = new JLabel("Poids : ");
        formPanel.add(weightLuggageLabel);
        String[] weights = {"", "0 < 10 kg (+0€)", "10 < 20 kg (+10€)", "20 < 30 kg (+20€)", "Max 35 kg (+25€)"};
        int indSelected = 0;
        for (int i = 0; i < weights.length; i++) {
            Pattern patternLuggage = Pattern.compile("(^.*kg)", Pattern.CASE_INSENSITIVE);
            Matcher matcher = patternLuggage.matcher(weights[i]);
            if (matcher.find()) {
                if(matcher.group(1).equals(booking.getLuggageWeight()))
                    indSelected = i;
            }
        }
        weightLuggageBox = new JComboBox(weights);
        weightLuggageBox.setSelectedItem(weights[indSelected]);
        formPanel.add(weightLuggageBox);

        //--------------------------------Business------------------------------------------
        isBusinessFlight = new ButtonGroup();
        buttonYesBusinessFlight = new JRadioButton("Je voyage pour affaire");
        formPanel.add(buttonYesBusinessFlight);
        buttonNoBusinessFlight = new JRadioButton("Je ne voyage pas pour affaire");
        CompanyListener companyListener = new CompanyListener();
        buttonNoBusinessFlight.addItemListener(companyListener);
        if (booking.getCompanyName() != null)
            buttonYesBusinessFlight.setSelected(true);
        else {
            buttonNoBusinessFlight.setSelected(false);
        }
        formPanel.add(buttonNoBusinessFlight);
        isBusinessFlight.add(buttonYesBusinessFlight);
        isBusinessFlight.add(buttonNoBusinessFlight);

        //---------------------------CompanyName--------------------------
        companyNameLabel = new JLabel("Nom de la société : ");
        formPanel.add(companyNameLabel);
        companyName = new JTextField(booking.getCompanyName());
        formPanel.add(companyName);

        //----------------------------Meal----------------------------------------------
        mealTypeLabel = new JLabel("Type de repas : ");
        formPanel.add(mealTypeLabel);
        String[] mealTypes = {"Poulet","Boeuf","Végétarien","Porc"};
        int indSelectedMeal = 0;
        for (int i = 0; i < mealTypes.length; i++) {
            if(mealTypes[i].equals(booking.getMealType()))
                indSelectedMeal = i;
        }
        mealTypeBox = new JComboBox(mealTypes);
        mealTypeBox.setSelectedItem(mealTypes[indSelectedMeal]);
        formPanel.add(mealTypeBox);

        //------------------------------payement--------------------------------------------
        payment = new ButtonGroup();
        buttonPayNow = new JRadioButton("Je paye maintenant");
        formPanel.add(buttonPayNow);
        buttonPayAfter = new JRadioButton("Je paye le jour du départ");
        if(booking.getHasPaid()) {
            buttonPayNow.setSelected(true);
        } else {
            buttonPayAfter.setSelected(true);
        }
        formPanel.add(buttonPayAfter);
        payment.add(buttonPayNow);
        payment.add(buttonPayAfter);

        //------------------------------Price--------------------------------------------
        totalPriceLabel = new JLabel("Prix total en €: ");
        formPanel.add(totalPriceLabel);
        totalPrice = booking.getRealPrice();
        totalPriceText = new JTextField(totalPrice + "");
        totalPriceText.setEnabled(false);
        formPanel.add(totalPriceText);

        //--------------------ButtonPanel-----------------------------------
        searchSeatButton = new JButton("Chercher les places disponibles");
        calculateButton = new JButton("Calculer le prix");
        validationButton = new JButton("Validation");
        //searchSeatButton.addActionListener(new SearchSeatListener());
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
/*
    private class SearchSeatListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
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

        }
    }
*/
    private class CalculateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Pattern patternSTPrice = Pattern.compile("(\\d+)", Pattern.CASE_INSENSITIVE);
            Matcher matcherST = patternSTPrice.matcher(seatTypeBox.getSelectedItem().toString());

            Pattern patternWLPrice = Pattern.compile("(\\d+)€", Pattern.CASE_INSENSITIVE);
            Matcher matcherWL = patternWLPrice.matcher(weightLuggageBox.getSelectedItem().toString());

            // faire une recherche DAO
           // flightPrice = flightPrices.get(flightBox.getSelectedIndex());
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


                try {
                    controller.updateBooking(bookingID, new GregorianCalendar(),buttonPayNow.isSelected(),luggageWeight.toString(), company_name, mealTypeBox.getSelectedItem().toString(), Double.parseDouble(totalPriceText.getText()),1);
                    JOptionPane.showMessageDialog(null, "Ajout effectué avec succès");
                    frameContainer.removeAll();
                    frameContainer.revalidate();
                    frameContainer.repaint();
                    frameContainer.add(new AddBookingJPanel(frameContainer));
                } catch (UpdateException | ConnectionException exception) {
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
