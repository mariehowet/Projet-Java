package View;

import com.sun.org.apache.xml.internal.security.utils.IgnoreAllErrorHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class ThreadJPanel extends JPanel {
    private JLabel imageLabel, iconLabel;
    private JPanel test;
    private ImageIcon imageIcon,icon;
    private JLayeredPane layeredPane;

    public ThreadJPanel() {
        //setLayout(new FlowLayout());
        setBounds(200,200,1000,750);

        icon = new ImageIcon("D:\\PRO\\Projet-Java\\main\\src\\Images\\aeroplane.png");
        imageIcon = new ImageIcon("D:\\PRO\\Projet-Java\\main\\src\\Images\\parisnyc.png");

        Image iconResizing = icon.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT);

        ImageIcon newIcon = new ImageIcon(iconResizing);
        imageLabel = new JLabel(imageIcon);
        iconLabel = new JLabel(newIcon);

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1000,700));


        imageLabel.setBounds(0,0,1000,1000);
        iconLabel.setBounds(750,427,50,50);
        //iconLabel.setBounds(225,505,50,50);

        /*
        System.out.println(iconLabel.getBounds().x);
        System.out.println(iconLabel.getBounds().y);
        */

        layeredPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(iconLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.setOpaque(true);
        add(layeredPane);
        //setVisible(true);;
        FlyThread flyThread = new FlyThread(this);
        flyThread.start();

    }

    public void move() {
        if (iconLabel.getBounds().x >= 225 && iconLabel.getBounds().y <= 505) {
            if ((iconLabel.getBounds().x ) % 10 == 0)
                iconLabel.setBounds(iconLabel.getBounds().x - 1 , iconLabel.getBounds().y + 1, iconLabel.getBounds().width, iconLabel.getBounds().height);
            else
                iconLabel.setBounds(iconLabel.getBounds().x - 1, iconLabel.getBounds().y , iconLabel.getBounds().width, iconLabel.getBounds().height);
        }
    }

    /*


    public void paint (Graphics g) {
        int dx, dy, p , x, y;
        int x1 = 750, y1 = 427, x2 = 225, y2 = 505;


        dx = Math.abs(x2 - x1);
        dy = Math.abs(y2 - y1);

        if(x1 > x2)
            x = x2;
            y = y2;

        g.drawRect(iconLabel.getBounds().x, iconLabel.getBounds().y, iconLabel.getBounds().width,iconLabel.getBounds().height);
    }

 */
}
