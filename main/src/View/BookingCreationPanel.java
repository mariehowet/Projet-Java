package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BookingCreationPanel extends JPanel {
    private JLabel title;
    private JButton validationButton;

    private BookingForm bookingForm;
    public BookingCreationPanel() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(0,50,50,50));
        title = new JLabel("<html><h1 style='margin: 30px 0 15px 0'>Créer une réservation</h1></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        bookingForm = new BookingForm();

        validationButton = new JButton("Validation");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(validationButton);

        this.add(title, BorderLayout.NORTH);
        this.add(bookingForm, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

    }
}
