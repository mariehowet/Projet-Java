package View;

import javax.swing.*;
import java.awt.*;

public class AnswersJPanel extends JPanel {
    private String [][] data;
    private String [] columnsNames;
    private JTable table;

    public AnswersJPanel(String [][] data, String [] columnsNames) {
        this.data = data;
        this.columnsNames = columnsNames;

        table = new JTable(data, columnsNames);
        table.setPreferredScrollableViewportSize(new Dimension(900, 100));

        this.add(new JScrollPane(table));
        this.setLayout(new FlowLayout());
        this.setVisible(true);
    }
}
