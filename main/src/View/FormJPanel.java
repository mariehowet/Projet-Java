package View;

import Controller.ApplicationController;
import Model.Seat;
import Model.SeatType;
import Exception.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class FormJPanel extends JPanel{
    protected JLabel title, passengerLabel, weightLuggageLabel, companyNameLabel, mealTypeLabel, totalPriceLabel, flightLabel, seatLabel;
    protected JButton validationButton;
    protected JPanel formPanel, buttonPanel;
    protected JTextField  companyName, totalPriceText;
    protected JRadioButton buttonYesLuggage, buttonNoLuggage, buttonYesBusinessFlight, buttonNoBusinessFlight, buttonPayNow, buttonPayAfter;
    protected JComboBox passengerBox, flightBox,seatTypeBox, weightLuggageBox, mealTypeBox, seatBox;
    protected ApplicationController controller;
    protected Container frameContainer;
    protected ButtonGroup hasLuggage, isBusinessFlight, payment;
    protected Double flightPrice, seatTypePrice, luggagePrice, totalPrice;
    protected ArrayList<SeatType> seatTypeList;
    protected ArrayList<Seat> seatList;
    protected String seatTypeName;
    protected int flightID;
    protected String[] weights, mealTypes;


    public FormJPanel(Container frameContainer) throws ConnectionException {
        this.frameContainer = frameContainer;
        controller  = new ApplicationController();
        title = new JLabel();
        title.setHorizontalAlignment(SwingConstants.CENTER);
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(0,50,50,50));

        //----------------FormPanel--------------------------------------------------
        formPanel = new JPanel();
        formPanel.setBorder(new EmptyBorder(25, 150, 25, 150));
        formPanel.setLayout(new GridLayout(11, 2));

        //------------------Flight---------------------------------------------------
        flightLabel = new JLabel("Numéro de vol :");
        formPanel.add(flightLabel);
        flightBox = new JComboBox();
        formPanel.add(flightBox);

        //--------------------Passenger----------------------------------------------
        passengerLabel = new JLabel("Nom du passager : ");
        formPanel.add(passengerLabel);
        passengerBox = new JComboBox();
        formPanel.add(passengerBox);

        //--------------------SeatType-----------------------------------------------
        seatLabel = new JLabel("Classe du siège : ");
        formPanel.add(seatLabel);
        seatTypeBox = new JComboBox();
        formPanel.add(seatTypeBox);

        //-------------------------Seat----------------------------------------------
        seatLabel = new JLabel("Siège :");
        formPanel.add(seatLabel);
        seatBox = new JComboBox();
        formPanel.add(seatBox);

        //--------------------Luggage------------------------------------------------
        hasLuggage = new ButtonGroup();
        buttonYesLuggage = new JRadioButton("Je dispose de bagages");
        buttonNoLuggage = new JRadioButton("Je ne dispose pas de bagages");
        buttonYesLuggage.addItemListener(new LuggageListener());
        formPanel.add(buttonYesLuggage);
        formPanel.add(buttonNoLuggage);
        hasLuggage.add(buttonYesLuggage);
        hasLuggage.add(buttonNoLuggage);

        //-----------------------------Weight------------------------------------------
        weightLuggageLabel = new JLabel("Poids : ");
        formPanel.add(weightLuggageLabel);
        weights = new String[]{"","0 < 10 kg (+0€)","10 < 20 kg (+10€)","20 < 30 kg (+20€)","Max 35 kg (+25€)"};
        weightLuggageBox = new JComboBox(weights);
        formPanel.add(weightLuggageBox);

        //--------------------------------Business--------------------------------------
        isBusinessFlight = new ButtonGroup();
        buttonYesBusinessFlight = new JRadioButton("Je voyage pour affaire");
        buttonNoBusinessFlight = new JRadioButton("Je ne voyage pas pour affaire");
        buttonYesBusinessFlight.addItemListener(new CompanyListener());
        formPanel.add(buttonYesBusinessFlight);
        formPanel.add(buttonNoBusinessFlight);
        isBusinessFlight.add(buttonYesBusinessFlight);
        isBusinessFlight.add(buttonNoBusinessFlight);

        //---------------------------CompanyName-----------------------------------------
        companyNameLabel = new JLabel("Nom de la société : ");
        formPanel.add(companyNameLabel);
        companyName = new JTextField();
        formPanel.add(companyName);

        //----------------------------Meal-----------------------------------------------
        mealTypeLabel = new JLabel("Type de repas : ");
        formPanel.add(mealTypeLabel);
        mealTypes =  new String[] {"Poulet","Boeuf","Végétarien","Porc"};
        mealTypeBox = new JComboBox(mealTypes);
        formPanel.add(mealTypeBox);

        //------------------------------Payement-----------------------------------------
        payment = new ButtonGroup();
        buttonPayNow = new JRadioButton("Je paye maintenant");
        buttonPayAfter = new JRadioButton("Je paye le jour du départ");
        payment.add(buttonPayNow);
        payment.add(buttonPayAfter);
        formPanel.add(buttonPayNow);
        formPanel.add(buttonPayAfter);

        //--------------------ButtonPanel-----------------------------------
        validationButton = new JButton("Validation");
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(validationButton);

        //--------------------AddPanels---------------------
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
}
