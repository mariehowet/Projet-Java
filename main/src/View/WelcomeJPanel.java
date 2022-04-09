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
        JLabel image = new JLabel(new ImageIcon("D:\\IESN informatique de gestion\\IG 2\\Q2\\PPOO avancé\\Projet\\Projet-GitHub\\main\\src\\Images\\plane2.jpg"));
        image.setSize(710,444);
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
