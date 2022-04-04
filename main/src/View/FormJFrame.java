package View;

import javax.swing.*;
import java.awt.*;

public class FormJFrame extends JFrame {
    private Container frameContainer;
    private FormJPanel formJPanel;
    private ValidationFormPanel validationFormPanel;
    public FormJFrame() {
        super("Formulaire de réservation");
        setBounds(1050,50,500,750);

        frameContainer = this.getContentPane();

        frameContainer.setLayout(new BorderLayout());

        // Ajout d'un pannel formulaire à la fenetre de formulaire
        formJPanel = new FormJPanel();
        frameContainer.add(formJPanel, BorderLayout.CENTER);
        validationFormPanel = new ValidationFormPanel();
        frameContainer.add(validationFormPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
