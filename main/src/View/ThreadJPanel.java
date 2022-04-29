package View;

import javax.swing.*;
import java.awt.*;


public class ThreadJPanel extends JPanel {
    private JLabel imageLabel, iconLabel;
    private ImageIcon imageIcon,icon;
    private JLayeredPane layeredPane;

    public ThreadJPanel() {


        icon = new ImageIcon("D:\\IESN informatique de gestion\\IG 2\\Q2\\PPOO avancé\\Projet\\Projet-GitHub\\main\\src\\Images\\aeroplane.png");
        imageIcon = new ImageIcon("D:\\IESN informatique de gestion\\IG 2\\Q2\\PPOO avancé\\Projet\\Projet-GitHub\\main\\src\\Images\\parisnyc.png");

        Image iconResizing = icon.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT);

        ImageIcon newIcon = new ImageIcon(iconResizing);
        imageLabel = new JLabel(imageIcon);
        iconLabel = new JLabel(newIcon);

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1000,700));


        imageLabel.setBounds(0,0,1000,1000);
        iconLabel.setBounds(750,427,50,50);

        layeredPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(iconLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.setOpaque(true);
        add(layeredPane);
        setVisible(true);


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
    void drawline(int x1, int y1, int x2, int y2)
    {
        int dx, dy, p, x, y, xend, m, b;
        dx=x2-x1;
        dy=y2-y1;

        m = dy /dx;
        b = y1 - m*x1;

        x = x1;
        y = y1;
        if (dx<0)
        {
            x=x2;
            y=y2;
            xend=x1;
        }
        else
        {
            x=x1;
            y=y1;
            xend=x2;
        }

        if (x <= xend) {
            iconLabel.setBounds(x, y, iconLabel.getBounds().width, iconLabel.getBounds().height);
            y++;
            y=(x*x) + b;
        }
    }

    public void fly(int x1, int y1, int x2, int y2) {
        int dx, dy, x, y, m, b;
        dx=x2-x1;
        dy=y2-y1;

        m = dy /dx;
        b = y1 - m*x1;

        x = x1;
        y = y1;
        if(y == m*x + b) {
            iconLabel.setBounds(x, y, iconLabel.getBounds().width, iconLabel.getBounds().height);
            y++;
        } else {
            x++;
        }
    }
}
