package View;

import Controller.ApplicationController;
import Exception.*;
import Model.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddBookingJPanel extends JPanel {
    private JLabel title;
    private JButton validationButton;
    private JPanel formPanel, buttonPanel;
    private JLabel passengerLabel, weightLuggageLabel, companyNameLabel, mealTypeLabel, totalPriceLabel, flightLabel, seatLabel;
    private JTextField  companyName, totalPriceText;
    private JRadioButton buttonYesLuggage, buttonNoLuggage, buttonYesBusinessFlight, buttonNoBusinessFlight, buttonPayNow, buttonPayAfter;
    private JComboBox seatTypeBox, weightLuggageBox, mealTypeBox, passengerBox, flightBox, seatBox;
    private ButtonGroup hasLuggage, isBusinessFlight, payment;
    private ApplicationController controller;
    private Double flightPrice, seatTypePrice, luggagePrice, totalPrice;
    private ArrayList<Double> flightPrices;
    private ArrayList<Integer> flightIDs, passengerIDs, seatIDs;
    private String seatTypeName;
    private Integer flightID;
    private Container frameContainer;
    private ArrayList<SeatType> seatTypeList;

    public AddBookingJPanel(Container frameContainer) throws ConnectionException{

        this.frameContainer = frameContainer;
        controller  = new ApplicationController();

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(0,50,50,50));
        title = new JLabel("<html><h1 style='margin: 30px 0 15px 0'>Créer une réservation</h1></html>", SwingConstants.CENTER);

        flightPrice = 0.0;
        seatTypePrice = 0.0;
        luggagePrice = 0.0;

        //----------------FormPanel--------------------------------------------------
        formPanel = new JPanel();
        formPanel.setBorder(new EmptyBorder(25, 150, 25, 150));
        formPanel.setLayout(new GridLayout(11,2));

        //------------------Flight----------------------------------
        flightLabel = new JLabel("<html><h3>Votre vol : </h3></html>");
        formPanel.add(flightLabel);
        String [] flightValues;
        try {
            ArrayList<Flight> flightList = controller.getAllFlights();
            flightIDs = new ArrayList<>();
            flightPrices = new ArrayList<>();

            for(Flight fl : flightList) {
                flightIDs.add(fl.getId());
                flightPrices.add(fl.getPrice());
            }
            int nb = flightIDs.size();
            flightValues = new String[nb];

            for (int j = 0; j < nb; j++) {
                flightValues[j] = flightIDs.get(j).toString();
            }

            flightBox = new JComboBox(flightValues);
            flightBox.addActionListener(new SearchSeatListener());
            flightBox.addActionListener(new CalculateListener());
            formPanel.add(flightBox);

        } catch (AllFlightsException | PriceException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //--------------------Passenger------------------------------
        passengerLabel = new JLabel("Votre nom : ");
        formPanel.add(passengerLabel);
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

        //--------------------SeatType------------------------------REFERENCE
        seatLabel = new JLabel("Type de siège : ");
        formPanel.add(seatLabel);
        try {
            seatTypeList = controller.getAllSeatTypes();
            seatTypeBox = new JComboBox();

            for (SeatType st : seatTypeList) {
                seatTypeBox.addItem(st.getName() + " " + "(+ " + st.getAdditionalPrice() + "€)");
            }
            seatTypeBox.addActionListener(new CalculateListener());
            seatTypeBox.addActionListener(new SearchSeatListener());
            formPanel.add(seatTypeBox);

        } catch (AllSeatTypesException | PriceException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //-------------------------Seat--------------------------------------
        seatLabel = new JLabel("Votre siège :");
        formPanel.add(seatLabel);

        seatBox = new JComboBox();
        seatBox.setEnabled(false);
        formPanel.add(seatBox);

        //--------------------Luggage------------------------------------------
        hasLuggage = new ButtonGroup();
        buttonYesLuggage = new JRadioButton("Je dispose de bagages");
        formPanel.add(buttonYesLuggage);
        buttonNoLuggage = new JRadioButton("Je ne dispose pas de bagages");
        buttonYesLuggage.addItemListener(new LuggageListener());
        buttonNoLuggage.setSelected(true);
        formPanel.add(buttonNoLuggage);
        hasLuggage.add(buttonYesLuggage);
        hasLuggage.add(buttonNoLuggage);

        //-----------------------------Weight---------------------------------------------
        weightLuggageLabel = new JLabel("Poids : ");
        formPanel.add(weightLuggageLabel);
        String[] weights = {"","0 < 10 kg (+0€)","10 < 20 kg (+10€)","20 < 30 kg (+20€)","Max 35 kg (+25€)"};
        weightLuggageBox = new JComboBox(weights);
        weightLuggageBox.addActionListener(new CalculateListener());
        weightLuggageBox.setEnabled(false);
        formPanel.add(weightLuggageBox);

        //--------------------------------Business------------------------------------------
        isBusinessFlight = new ButtonGroup();
        buttonYesBusinessFlight = new JRadioButton("Je voyage pour affaire");
        formPanel.add(buttonYesBusinessFlight);
        buttonNoBusinessFlight = new JRadioButton("Je ne voyage pas pour affaire");
        buttonYesBusinessFlight.addItemListener(new CompanyListener());
        buttonNoBusinessFlight.setSelected(true);
        formPanel.add(buttonNoBusinessFlight);
        isBusinessFlight.add(buttonYesBusinessFlight);
        isBusinessFlight.add(buttonNoBusinessFlight);

        companyNameLabel = new JLabel("Nom de la société : ");
        formPanel.add(companyNameLabel);
        companyName = new JTextField();
        companyName.setEnabled(false);
        formPanel.add(companyName);

        //----------------------------Meal----------------------------------------------
        mealTypeLabel = new JLabel("Type de repas : ");
        formPanel.add(mealTypeLabel);
        String[] mealTypes = {"Poulet","Boeuf","Végétarien","Porc"};
        mealTypeBox = new JComboBox(mealTypes);
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

        //------------------------------Price---------------------------------------------
        totalPriceLabel = new JLabel("Prix total en €: ");
        formPanel.add(totalPriceLabel);
        totalPriceText = new JTextField("0");
        totalPriceText.setEnabled(false);
        formPanel.add(totalPriceText);

        //--------------------ButtonPanel-----------------------------------

        validationButton = new JButton("Validation");

        validationButton.addActionListener(new ValidationListener());
        validationButton.addActionListener(new CalculateListener());

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(validationButton);

        //--------------------AddPanels----------------------

        add(title, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private class LuggageListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                weightLuggageBox.setEnabled(true);
            } else {
                weightLuggageBox.setEnabled(false);
                weightLuggageBox.setSelectedItem("");
            }
        }
    }

    private class CompanyListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                companyName.setEnabled(true);
            } else {
                companyName.setEnabled(false);
                companyName.setText("");
            }
        }
    }

    private class SearchSeatListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            seatTypeName = seatTypeList.get(seatTypeBox.getSelectedIndex()).getName();
            flightID = flightIDs.get(flightBox.getSelectedIndex());
            seatBox.removeAllItems();
            try {
                ArrayList<Seat> seatList = controller.getAvailableSeats(seatTypeName, flightID);
                ArrayList<String> seats = new ArrayList<>();
                seatIDs = new ArrayList<>();

                for(Seat st : seatList) {
                    seats.add(st.getNumber() + st.getColumnLetter());
                    seatIDs.add(st.getId());
                }
                int nb = seats.size();

                for (int j = 0; j < nb; j++) {
                    seatBox.addItem(seats.get(j));
                }
                seatBox.setEnabled(true);
            } catch (AvailableSeatsException | SeatNumberException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
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
            if (seatBox.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner un vol et le type de siège " +
                        "\nAvant de choisir votre siège ", "Problème", JOptionPane.WARNING_MESSAGE);
            }
            else if (buttonYesLuggage.isSelected() && weightLuggageBox.getSelectedItem().toString() == "") {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner le poids de vos bagages", "Problème", JOptionPane.WARNING_MESSAGE);

            }
            else if (buttonYesBusinessFlight.isSelected() && companyName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Veuillez entrer le nom de votre société", "Problème", JOptionPane.WARNING_MESSAGE);
            }
            else {
                String luggageWeight = null;
                if (buttonYesLuggage.isSelected()) {
                    Pattern patternLuggage = Pattern.compile("(^.*kg)", Pattern.CASE_INSENSITIVE);
                    Matcher matcherLuggage = patternLuggage.matcher(weightLuggageBox.getSelectedItem().toString());
                    if (matcherLuggage.find())
                        luggageWeight = matcherLuggage.group(1);
                }
                String company_name = buttonYesBusinessFlight.isSelected() ? companyName.getText() : null;
                Booking booking;
                try {
                    booking = new Booking(
                            buttonPayNow.isSelected(),
                            luggageWeight,
                            company_name,
                            mealTypeBox.getSelectedItem().toString(),
                            totalPrice,
                            flightIDs.get(flightBox.getSelectedIndex()),
                            seatIDs.get(seatBox.getSelectedIndex()),
                            passengerIDs.get(passengerBox.getSelectedIndex())
                    );

                    controller.addBooking(booking);
                    JOptionPane.showMessageDialog(null, "Ajout effectué avec succès");
                    frameContainer.removeAll();
                    frameContainer.revalidate();
                    frameContainer.repaint();
                    frameContainer.add(new AddBookingJPanel(frameContainer));

                } catch (AddBookingException | ConnectionException | PriceException exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        }
    }
}


