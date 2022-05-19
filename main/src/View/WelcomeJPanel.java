package View;

import javax.swing.*;
import java.awt.*;

public class WelcomeJPanel extends JPanel {
    private JLabel label, image;
    private JPanel test;

    public WelcomeJPanel(){
        setLayout(new GridLayout(3,1));
        label = new JLabel("Bienvenue sur Marvin Airline !", SwingConstants.CENTER);
        this.add(label);
        test = new JPanel();
        image = new JLabel(new ImageIcon("main/src/images/plane2.jpg"));
        image.setSize(710,444);
        test.add(image);
        this.add(test);
    }
}
