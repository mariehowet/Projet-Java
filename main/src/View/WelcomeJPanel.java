package View;

import javax.swing.*;
import java.awt.*;



public class WelcomeJPanel extends JPanel {
    private JLabel label;
    public WelcomeJPanel(){
        setLayout(new GridLayout(3,1));


        label = new JLabel("Bienvenue sur Marvin Airline !", SwingConstants.CENTER);

        this.add(label);
        JPanel test = new JPanel();
        JLabel image = new JLabel(new ImageIcon("main/src/images/plane2.jpg"));
        image.setSize(710,444);
        test.add(image);
        add(test);


    }


}
