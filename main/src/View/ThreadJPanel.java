package View;

import javax.swing.*;
import java.awt.*;

public class ThreadJPanel extends JPanel {
    private JLabel imageLabel, iconLabel;
    private JPanel test;
    private ImageIcon imageIcon,icon;

    public ThreadJPanel() {
        setLayout(new GridLayout(2,1));

        icon = new ImageIcon("D:\\PRO\\Projet-Java\\main\\src\\Images\\aeroplane.png");
        imageIcon = new ImageIcon("D:\\PRO\\Projet-Java\\main\\src\\Images\\parisnyc.png");
        imageLabel = new JLabel(imageIcon);
        iconLabel = new JLabel(icon);

        imageLabel.setBounds(0,0,500,500);
        iconLabel.setBounds(400,400,1,1);


        add(imageLabel);
        add(iconLabel);
    }
}
