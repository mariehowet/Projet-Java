package View;

import javax.swing.*;
import java.awt.*;

public class WelcomeJPanel extends JPanel {
    private JLabel label;
    public WelcomeJPanel()
    {
        label = new JLabel("Bienvenue sur Marvin Ailine !", SwingConstants.CENTER);
        setLayout(new BorderLayout());
        this.add(label,BorderLayout.CENTER);
    }
}
