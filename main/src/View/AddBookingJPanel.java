package View;

import Exception.*;
import Model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddBookingJPanel extends FormJPanel {
    private String seatTypeName;
    private ArrayList<Flight> flightList;
    private ArrayList<Passenger> passengerList;

    public AddBookingJPanel(Container frameContainer) throws ConnectionException{
        super(frameContainer);
        title.setText("<html><h1 style='margin: 30px 0 15px 0'>Créer une réservation</h1></html>");
        flightPrice = 0.0;
        seatTypePrice = 0.0;
        luggagePrice = 0.0;

        //------------------Flight----------------------------------
        try {
            flightList = controller.getAllFlights();
            for(Flight fl : flightList) {
                flightBox.addItem(fl.getId());
            }
            flightBox.addActionListener(new SearchSeatListener());
            flightBox.addActionListener(new CalculateListener());

        } catch (AllFlightsException | PriceException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //--------------------Passenger------------------------------
        try {
            passengerList = controller.getAllPassengers();
            for(Passenger pas : passengerList) {
                passengerBox.addItem(pas.getFirstName() + " " + pas.getLastName() +  (pas.getInitialMiddleName() != null ? " "+pas.getInitialMiddleName() : "" ));
            }
        } catch (PassengerException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //--------------------SeatType------------------------------REFERENCE
        try {
            seatTypeList = controller.getAllSeatTypes();

            for (SeatType st : seatTypeList) {
                seatTypeBox.addItem(st.getName() + " " + "(+ " + st.getAdditionalPrice() + "€)");
            }
            seatTypeBox.addActionListener(new CalculateListener());
            seatTypeBox.addActionListener(new SearchSeatListener());
        } catch (AllSeatTypesException | PriceException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //-------------------------Seat--------------------------------------
        seatBox.setEnabled(false);

        //--------------------Luggage------------------------------------------
        buttonNoLuggage.setSelected(true);

        //-----------------------------Weight---------------------------------------------
        weightLuggageBox.addActionListener(new CalculateListener());
        weightLuggageBox.setEnabled(false);

        //--------------------------------Business------------------------------------------
        buttonNoBusinessFlight.setSelected(true);

        //---------------------------CompanyName-------------------------------------------
        companyName.setEnabled(false);

        //------------------------------Payement--------------------------------------------
        buttonPayNow.setSelected(true);

        //------------------------------Price---------------------------------------------
        totalPriceLabel = new JLabel("Prix total en €: ");
        formPanel.add(totalPriceLabel);
        totalPriceText = new JTextField("0");
        totalPriceText.setEnabled(false);
        formPanel.add(totalPriceText);

        //--------------------ButtonPanel-----------------------------------
        validationButton.addActionListener(new ValidationListener());
        validationButton.addActionListener(new CalculateListener());

    }

    private class SearchSeatListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            seatTypeName = seatTypeList.get(seatTypeBox.getSelectedIndex()).getName();
            flightID = flightList.get(flightBox.getSelectedIndex()).getId();
            seatBox.removeAllItems();
            try {
                seatList = controller.getAvailableSeats(seatTypeName, flightID);
                for(Seat st : seatList) {
                    seatBox.addItem(st.getNumber() + st.getColumnLetter());
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

            flightPrice = flightList.get(flightBox.getSelectedIndex()).getPrice();
            if(matcherST.find())
                seatTypePrice = Double.parseDouble(matcherST.group(1));
            if(matcherWL.find())
                luggagePrice = Double.parseDouble(matcherWL.group(1));
            else
                luggagePrice = 0.0;
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
                            flightList.get(flightBox.getSelectedIndex()).getId(),
                            seatList.get(seatBox.getSelectedIndex()).getId(),
                            passengerList.get(passengerBox.getSelectedIndex()).getId()
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


