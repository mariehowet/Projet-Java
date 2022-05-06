package View;

import Controller.ApplicationController;
import Model.Booking;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.GregorianCalendar;
import Exception.AddBookingException;


public class BookingForm extends JPanel {
    private JLabel idPassengerLabel, seatTypeLabel, weightLuggageLabel,
            companyNameLabel, mealTypeLabel, totalPriceLabel;
    private JTextField idPassenger, companyName, totalPrice;
    private JRadioButton buttonYesLuggage, buttonNoLuggage,
            buttonYesBusinessFlight, buttonNoBusinessFlight, buttonPayNow, buttonPayAfter;
    private JComboBox seatType, weightLuggage, mealType;
    private ButtonGroup hasLuggage, isBusinessFlight, payment;


    private ApplicationController controller;
    private Booking booking;
    public BookingForm() {
        this.setBorder(new EmptyBorder(25, 150, 25, 150));
        this.setLayout(new GridLayout(9,2));

        idPassengerLabel = new JLabel("Votre identifiant : ");
        this.add(idPassengerLabel);
        idPassenger = new JTextField();
        this.add(idPassenger);

        seatTypeLabel = new JLabel("Type de siège : ");
        this.add(seatTypeLabel);
        String[] seatTypes = {"Classe économique (+30€)","Classe business (+ 100€)","Première classe (+200€)"};
        seatType = new JComboBox(seatTypes);
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
        weightLuggage.setSelectedItem(weights[0]);
        weightLuggage.setMaximumRowCount(2);
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
        mealType.setMaximumRowCount(2);
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
        setController(new ApplicationController());
        booking = new Booking(false, 15, "Odoo", "boeuf", 1000.0, 1, 1, 1);

        try {
            controller.addBooking(booking);
        } catch (AddBookingException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }

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

}
