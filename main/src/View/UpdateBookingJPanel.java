package View;

import Model.*;
import Exception.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateBookingJPanel extends FormJPanel {
    private int bookingID;
    private SeatType actualSeatType;
    private Seat actualSeat;

    public UpdateBookingJPanel(Container frameContainer, Booking booking) throws ConnectionException {
        super(frameContainer);
        title.setText("<html><h1 style='margin: 30px 0 15px 0'>Modifier une réservation</h1></html>");
        bookingID = booking.getId();
        flightID = booking.getFlightID();

        //------------------Flight----------------------------------
        flightBox.addItem(booking.getFlightID());
        flightBox.setEnabled(false);

        //--------------------Passenger------------------------------
        try {
            ArrayList<Passenger> passengerList = controller.getAllPassengers();

            for (Passenger pas : passengerList) {
                if (pas.getId() == booking.getPassengerID()) {
                    passengerBox.addItem(pas.getFirstName() + " " + pas.getLastName() + (pas.getInitialMiddleName() != null ? " " + pas.getInitialMiddleName() : ""));
                }
            }
            passengerBox.setEnabled(false);
        } catch (PassengerException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //--------------------SeatType------------------------------
        actualSeatType = null;
        try {
            actualSeatType = controller.getActualSeatType(booking.getSeatID());
            seatTypeList = controller.getAllSeatTypes();

            for (SeatType st : seatTypeList) {
                seatTypeBox.addItem(st.getName() + " " + "(+ " + st.getAdditionalPrice() + "€)");
            }
            int i = 0;
            for (SeatType st : seatTypeList) {
                if (st.getName().equals(actualSeatType.getName()))
                    i = seatTypeList.indexOf(st);
            }
            seatTypeBox.setSelectedIndex(i);
            seatTypeBox.addActionListener(new CalculateListener());
            seatTypeBox.addActionListener(new SearchSeatListener());
        } catch (AllSeatTypesException | SeatTypeException | PriceException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //-------------------------Seat--------------------------------------
        try {
            seatList = controller.getAvailableSeats(controller.getActualSeatType(booking.getSeatID()).getName(), booking.getFlightID());
            actualSeat = controller.getActualSeat(booking.getSeatID());
            seatList.add(actualSeat);
            for (Seat st : seatList) {
                seatBox.addItem(st.getNumber() + st.getColumnLetter());
            }
            int ind = 0;
            for (Seat s : seatList) {
                if (s.getId() == booking.getSeatID()) {
                    ind = seatList.indexOf(s);
                }
            }
            seatBox.setSelectedIndex(ind);
        } catch(AvailableSeatsException |SeatTypeException |ActualSeatException|SeatNumberException| PriceException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }

        //-----------------------------Weight--------------------------------------------
        int indSelected = 0;
        for (int i = 0; i < weights.length; i++) {
            Pattern patternLuggage = Pattern.compile("(^.*kg)", Pattern.CASE_INSENSITIVE);
            Matcher matcher = patternLuggage.matcher(weights[i]);
            if (matcher.find()) {
                if (matcher.group(1).equals(booking.getLuggageWeight()))
                    indSelected = i;
            }
        }
        if (booking.getLuggageWeight() != null) {
            buttonYesLuggage.setSelected(true);
            weightLuggageBox.setSelectedItem(weights[indSelected]);
        } else {
            buttonNoLuggage.setSelected(true);
            weightLuggageBox.setEnabled(false);
        }
        weightLuggageBox.addActionListener(new CalculateListener());

        //---------------------------CompanyName--------------------------------------------
        if (booking.getCompanyName() != null) {
            buttonYesBusinessFlight.setSelected(true);
            companyName.setText(booking.getCompanyName());
        } else {
            buttonNoBusinessFlight.setSelected(true);
            companyName.setEnabled(false);
        }
        //----------------------------Meal----------------------------------------------
        int indSelectedMeal = 0;
        for (int i = 0; i < mealTypes.length; i++) {
            if (mealTypes[i].equals(booking.getMealType()))
                indSelectedMeal = i;
        }
        mealTypeBox.setSelectedItem(mealTypes[indSelectedMeal]);

        //------------------------------Payement--------------------------------------------
        if (booking.getHasPaid()) {
            buttonPayNow.setSelected(true);
        } else {
            buttonPayAfter.setSelected(true);
        }

        //------------------------------Price--------------------------------------------
        totalPriceLabel = new JLabel("Prix total en €: ");
        formPanel.add(totalPriceLabel);
        totalPrice = booking.getRealPrice();
        totalPriceText = new JTextField(totalPrice + "");
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
            seatBox.removeAllItems();
            try {
                seatList = controller.getAvailableSeats(seatTypeName, flightID);
                if(seatTypeName.equals(actualSeatType.getName()))
                    seatList.add(actualSeat);
                for (Seat st : seatList) {
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

            try {
                flightPrice = controller.getFlightPrice(flightID);
            } catch (FlightPriceException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            if (matcherST.find())
                seatTypePrice = Double.parseDouble(matcherST.group(1));

            if (matcherWL.find())
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
            if (buttonYesLuggage.isSelected() && weightLuggageBox.getSelectedItem().toString() == "") {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner le poids de vos bagages", "Problème", JOptionPane.WARNING_MESSAGE);

            } else if (buttonYesBusinessFlight.isSelected() && companyName.getText().isEmpty()) {
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
                    controller.updateBooking(bookingID, new GregorianCalendar(), buttonPayNow.isSelected(),buttonNoLuggage.isSelected()? null : luggageWeight, company_name, mealTypeBox.getSelectedItem().toString(), totalPrice, seatList.get(seatBox.getSelectedIndex()).getId());
                    JOptionPane.showMessageDialog(null, "Modification effectué avec succès");
                    frameContainer.removeAll();
                    frameContainer.revalidate();
                    frameContainer.repaint();
                    frameContainer.add(new MenuItemAllBookings(frameContainer));
                } catch (UpdateException | PriceException exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        }
    }
}
