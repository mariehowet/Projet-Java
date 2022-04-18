

package View;

import javax.swing.*;
import java.awt.*;

public class LayeredPaneDemo extends JPanel {
    private String[] layerStrings = { "Yellow (0)", "Magenta (1)",
            "Cyan (2)",   "Red (3)",
            "Green (4)" };
    private Color[] layerColors = { Color.yellow, Color.magenta,
            Color.cyan,   Color.red,
            Color.green };

    private JLayeredPane layeredPane;
    private JLabel dukeLabel;


    //Adjustments to put Duke's toe at the cursor's tip.
    private static final int XFUDGE = 40;
    private static final int YFUDGE = 57;

    public LayeredPaneDemo() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        //Create and set up the layered pane.
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(300, 310));
        layeredPane.setBorder(BorderFactory.createTitledBorder(
                "Move the Mouse to Move Duke"));

        //This is the origin of the first label added.
        Point origin = new Point(10, 20);

        //This is the offset for computing the origin for the next label.
        int offset = 35;

        //Add several overlapping, colored labels to the layered pane
        //using absolute positioning/sizing.
        for (int i = 0; i < layerStrings.length; i++) {
            JLabel label = createColoredLabel(layerStrings[i],
                    layerColors[i], origin);
            layeredPane.add(label, new Integer(i));
            origin.x += offset;
            origin.y += offset;
        }

        layeredPane.setVisible(true);
        setVisible(true);
    }
        ///Create and set up a colored label.
        private JLabel createColoredLabel(String text, Color color, Point origin) {
            JLabel label = new JLabel(text);
            label.setVerticalAlignment(JLabel.TOP);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setOpaque(true);
            label.setBackground(color);
            label.setForeground(Color.black);
            label.setBorder(BorderFactory.createLineBorder(Color.black));
            label.setBounds(origin.x, origin.y, 140, 140);
            return label;
        }
    }
