package View;

import javax.swing.*;
import java.awt.*;

public class ThreadJPanel extends JPanel {
    private JLabel imageLabel, iconLabel;
    private ImageIcon imageIcon, icon;
    private JLayeredPane layeredPane;

    public ThreadJPanel() {
        icon = new ImageIcon("main/src/images/aeroplane.png");
        imageIcon = new ImageIcon("main/src/images/parisnyc.png");

        Image iconResizing = icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon newIcon = new ImageIcon(iconResizing);
        imageLabel = new JLabel(imageIcon);
        iconLabel = new JLabel(newIcon);

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1000, 700));

        imageLabel.setBounds(0, 0, 1000, 1000);
        iconLabel.setBounds(750, 427, 50, 50);

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
            if ((iconLabel.getBounds().x) % 10 == 0)
                iconLabel.setBounds(iconLabel.getBounds().x - 1, iconLabel.getBounds().y + 1, iconLabel.getBounds().width, iconLabel.getBounds().height);
            else
                iconLabel.setBounds(iconLabel.getBounds().x - 1, iconLabel.getBounds().y, iconLabel.getBounds().width, iconLabel.getBounds().height);
        }
    }
}