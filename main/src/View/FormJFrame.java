package View;

import javax.swing.*;
import java.awt.*;

public class FormJFrame extends JFrame {
    private Container frameContainer;
    private BookingCreationPanel bookingCreationPanel;
    private ValidationFormJPanel validationFormPanel;
    public FormJFrame() {
        super("Formulaire de réservation");
        setBounds(1050,50,500,750);

        frameContainer = this.getContentPane();

        frameContainer.setLayout(new BorderLayout());

        // Ajout d'un pannel formulaire à la fenetre de formulaire
        bookingCreationPanel = new BookingCreationPanel();
        frameContainer.add(bookingCreationPanel, BorderLayout.CENTER);
        validationFormPanel = new ValidationFormJPanel();
        frameContainer.add(validationFormPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
