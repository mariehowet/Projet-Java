package View;

import javax.swing.*;
import java.awt.*;

public class FormJPanel extends JPanel {
    private JLabel idPassengerLabel, seatTypeLabel, hasLuggageLabel, weightLuggageLabel,
            isBusinessFlightLabel, companyNameLabel, mealTypeLabel, paymentLabel, totalPriceLabel;
    private JTextField idPassenger, companyName, totalPrice;
    private JRadioButton buttonYesLuggage, buttonNoLuggage,
            buttonYesBusinessFlight, buttonNoBusinessFlight, buttonPayNow, buttonPayAfter;
    private JComboBox seatType, weightLuggage, mealType;
    private ButtonGroup hasLuggage, isBusinessFlight, payment;
    public FormJPanel() {
        setLayout(new GridLayout(7,1));
        Panel first = new Panel();
        first.setLayout(new GridLayout(2,2));

        idPassengerLabel = new JLabel("Votre identifiant : ");
        idPassengerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        first.add(idPassengerLabel);
        idPassenger = new JTextField();
        first.add(idPassenger);

        seatTypeLabel = new JLabel("Type de siège : ");
        seatTypeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        first.add(seatTypeLabel);
        String[] seatTypes = {"Classe économique (+30€)","Classe business (+ 100€)","Première classe (+200€)"};
        seatType = new JComboBox(seatTypes);
        first.add(seatType);

        add(first);


        Panel second = new Panel();
        second.setLayout(new GridLayout(1,3));
        hasLuggageLabel = new JLabel("Avez vous des bagages ? ");
        hasLuggageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        second.add(hasLuggageLabel);
        hasLuggage = new ButtonGroup();
        buttonYesLuggage = new JRadioButton("Oui");
        second.add(buttonYesLuggage);
        buttonNoLuggage = new JRadioButton("Non");
        second.add(buttonNoLuggage);
        hasLuggage.add(buttonYesLuggage);
        hasLuggage.add(buttonNoLuggage);

        add(second);
        Panel third = new Panel();
        third.setLayout(new GridLayout(1,2));
        weightLuggageLabel = new JLabel("Poids : ");
        weightLuggageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        third.add(weightLuggageLabel);
        String[] weights = {"0 < 10 kg (+0€)","10 < 20 kg (+10€)","20 < 30 kg (+20€)","Max 35 kg (+25€)"};
        weightLuggage = new JComboBox(weights);
        weightLuggage.setSelectedItem(weights[0]);
        weightLuggage.setMaximumRowCount(2);
        third.add(weightLuggage);

        add(third);

        Panel fourth = new Panel();
        fourth.setLayout(new GridLayout(1,3));

        isBusinessFlightLabel = new JLabel("Voyagez vous pour affaire ? ");
        isBusinessFlightLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        fourth.add(isBusinessFlightLabel);
        isBusinessFlight = new ButtonGroup();
        buttonYesBusinessFlight = new JRadioButton("Oui");
        fourth.add(buttonYesBusinessFlight);
        buttonNoBusinessFlight = new JRadioButton("Non");
        fourth.add(buttonNoBusinessFlight);
        isBusinessFlight.add(buttonYesBusinessFlight);
        isBusinessFlight.add(buttonNoBusinessFlight);

        add(fourth);


        Panel fifth = new Panel();
        fifth.setLayout(new GridLayout(2,2));
        companyNameLabel = new JLabel("Nom de la société : ");
        companyNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        fifth.add(companyNameLabel);
        companyName = new JTextField();
        fifth.add(companyName);


        mealTypeLabel = new JLabel("Type de repas : ");
        mealTypeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        fifth.add(mealTypeLabel);
        String[] mealTypes = {"Poulet","Boeuf","Végétarien","Porc"};
        mealType = new JComboBox(mealTypes);
        mealType.setMaximumRowCount(2);
        fifth.add(mealType);

        add(fifth);

        Panel sixth = new Panel();
        sixth.setLayout(new GridLayout(1,3));

        paymentLabel = new JLabel("Payement : ");
        paymentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        sixth.add(paymentLabel);
        payment = new ButtonGroup();
        buttonPayNow = new JRadioButton("Maintenant");
        sixth.add(buttonPayNow);
        buttonPayAfter = new JRadioButton("Le jour du départ");
        sixth.add(buttonPayAfter);
        payment.add(buttonPayNow);
        payment.add(buttonPayAfter);

        add(sixth);

        Panel seventh = new Panel();
        seventh.setLayout(new GridLayout(1,2));


        totalPriceLabel = new JLabel("Prix total : ");
        totalPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        seventh.add(totalPriceLabel);
        totalPrice = new JTextField();
        totalPrice.setEnabled(false);
        seventh.add(totalPrice);

        add(seventh);


    }
}
