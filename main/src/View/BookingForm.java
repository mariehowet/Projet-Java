package View;

import Controller.ApplicationController;
import Model.Booking;
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

import Exception.*;
import Model.Passenger;
import Model.SeatType;


public class BookingForm extends JPanel {
    private static final int NB_PASSENGERS = 8;
    private static final int NB_SEAT_TYPES = 3;

    private JLabel idPassengerLabel, seatTypeLabel, weightLuggageLabel,
            companyNameLabel, mealTypeLabel, totalPriceLabel;
    private JTextField  companyName, totalPrice;
    private JRadioButton buttonYesLuggage, buttonNoLuggage,
            buttonYesBusinessFlight, buttonNoBusinessFlight, buttonPayNow, buttonPayAfter;
    private JComboBox seatType, weightLuggage, mealType, idPassenger;
    private ButtonGroup hasLuggage, isBusinessFlight, payment;
    private ApplicationController controller;
    private Booking booking;

    public BookingForm() throws ConnectionException {
        this.setBorder(new EmptyBorder(25, 150, 25, 150));
        this.setLayout(new GridLayout(9,2));
        this.setController(new ApplicationController());

        //--------------------Passenger------------------------------
        idPassengerLabel = new JLabel("Votre nom : ");
        this.add(idPassengerLabel);
        String [] passengersValues = new String[NB_PASSENGERS];
        int nbPassangers = 0;
        try {
            ArrayList<Passenger> passengerList = controller.getAllPassengers();

            for(Passenger pas : passengerList) {
                passengersValues[nbPassangers] = pas.getId() + "-" + pas.getFirstName() + " " + pas.getLastName() + (pas.getInitialMiddleName() != null ? pas.getInitialMiddleName() : "" );
                nbPassangers++;
            }

        } catch (PassengerException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        idPassenger = new JComboBox(passengersValues);
        this.add(idPassenger);

        //--------------------SeatType------------------------------
        seatTypeLabel = new JLabel("Type de siège : ");
        this.add(seatTypeLabel);
        String[] seatTypes = new String[NB_SEAT_TYPES];
        int nbSeatTypes = 0;
        try {
            ArrayList<SeatType> seatTypeList = controller.getAllSeatTypes();

            for(SeatType st : seatTypeList) {
                seatTypes[nbSeatTypes] = st.getName() + " " + "(+ " + st.getAdditionalPrice() + ")";
                nbSeatTypes++;
            }

        } catch (SeatTypeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        seatType = new JComboBox(seatTypes);
        seatType.setSelectedItem(seatTypes[1]);
        this.add(seatType);

        hasLuggage = new ButtonGroup();
        buttonYesLuggage = new JRadioButton("Je dispose de bagages");
        this.add(buttonYesLuggage);
        buttonNoLuggage = new JRadioButton("Je ne dispose pas de bagages");
        LuggageListener luggageListener = new LuggageListener();
        buttonNoLuggage.addItemListener(luggageListener);
        this.add(buttonNoLuggage);
        hasLuggage.add(buttonYesLuggage);
        hasLuggage.add(buttonNoLuggage);

        weightLuggageLabel = new JLabel("Poids : ");
        this.add(weightLuggageLabel);
        String[] weights = {"","0 < 10 kg (+0€)","10 < 20 kg (+10€)","20 < 30 kg (+20€)","Max 35 kg (+25€)"};
        weightLuggage = new JComboBox(weights);
        //weightLuggage.setSelectedItem(weights[0]);
        //weightLuggage.setMaximumRowCount(2);
        this.add(weightLuggage);

        isBusinessFlight = new ButtonGroup();
        buttonYesBusinessFlight = new JRadioButton("Je voyage pour affaire");
        this.add(buttonYesBusinessFlight);
        buttonNoBusinessFlight = new JRadioButton("Je ne voyage pas pour affaire");
        CompanyListener companyListener = new CompanyListener();
        buttonNoBusinessFlight.addItemListener(companyListener);
        this.add(buttonNoBusinessFlight);
        isBusinessFlight.add(buttonYesBusinessFlight);
        isBusinessFlight.add(buttonNoBusinessFlight);

        companyNameLabel = new JLabel("Nom de la société : ");
        this.add(companyNameLabel);
        companyName = new JTextField();
        this.add(companyName);


        mealTypeLabel = new JLabel("Type de repas : ");
        this.add(mealTypeLabel);
        String[] mealTypes = {"Poulet","Boeuf","Végétarien","Porc"};
        mealType = new JComboBox(mealTypes);
       //mealType.setMaximumRowCount(2);
        this.add(mealType);

        payment = new ButtonGroup();
        buttonPayNow = new JRadioButton("Je paye maintenant");
        this.add(buttonPayNow);
        buttonPayAfter = new JRadioButton("Je paye le jour du départ");
        this.add(buttonPayAfter);
        payment.add(buttonPayNow);
        payment.add(buttonPayAfter);


        totalPriceLabel = new JLabel("Prix total : ");
        this.add(totalPriceLabel);
        totalPrice = new JTextField();
        totalPrice.setEnabled(false);
        this.add(totalPrice);

        // Ajout dans la BD : test
        /* A faire via le formulaire
        booking = new Booking(false, 15, "Odoo", "boeuf", 1000.0, 1, 1, 1);

        try {
            setController(new ApplicationController());
            controller.addBooking(booking);
        } catch (AddBookingException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
        catch (ConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());;
        }*/

    }

    public void setController(ApplicationController controller) {
        this.controller = controller;
    }


    private class LuggageListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                weightLuggage.setEnabled(false);
                weightLuggage.setSelectedItem("");
            } else {
                weightLuggage.setEnabled(true);
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

    private class ValidationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Booking booking;
            Pattern patternPassenger = Pattern.compile("\\d", Pattern.CASE_INSENSITIVE);
            Matcher matcher1 = patternPassenger.matcher(idPassenger.getSelectedItem().toString());
            //booking  = new Booking()
        }
    }

}
