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
        JLabel image = new JLabel(new ImageIcon("jetbrains://idea/navigate/reference?project=Projet-GitHub&path=Images/plane2.jpg"));
        image.setBounds(0,0,710,444);
        test.add(image);
        add(test);

    }

    /*
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0 , 0,null);
    }
    */

}
