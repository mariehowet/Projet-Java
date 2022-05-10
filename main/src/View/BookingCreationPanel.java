package View;

import Exception.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

public class  BookingCreationPanel extends JPanel {
    private JLabel title;
    private JButton validationButton;
    private BookingForm bookingForm;

    public BookingCreationPanel() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(0,50,50,50));
        title = new JLabel("<html><h1 style='margin: 30px 0 15px 0'>Créer une réservation</h1></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        try{
            bookingForm = new BookingForm();
        } catch (ConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        validationButton = new JButton("Validation");
        //validationButton.addActionListener(new AddInDBListener());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(validationButton);

        this.add(title, BorderLayout.NORTH);
        this.add(bookingForm, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private class AddInDBListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}


