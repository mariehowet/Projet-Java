package View;

import javax.swing.*;
import java.awt.*;

public class ValidationFormPanel extends JPanel {
    JButton validation;
    public ValidationFormPanel() {
        validation = new JButton("Valider");
        add(validation);
    }
}
