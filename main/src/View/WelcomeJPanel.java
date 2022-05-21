package View;

import javax.swing.*;
import java.awt.*;

public class WelcomeJPanel extends JPanel {
    private JLabel label, image;
    private JPanel test;

    public WelcomeJPanel(){
        setLayout(new GridLayout(3,1));

        label = new JLabel("<html><h1 style='margin: 30px 0 15px 0'>Bienvenue sur Marvin Airline !</h1></html>", SwingConstants.CENTER);
        this.add(label);
        test = new JPanel();
        image = new JLabel(new ImageIcon("main/src/images/plane2.jpg"));
        image.setSize(710,444);
        test.add(image);
        this.add(test);
    }
}
