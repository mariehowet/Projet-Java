package View;

import Controller.ApplicationController;
import Model.Stopover;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Exception.FlightStopoversException;
import Exception.ConnectionException;

public class StopoversJPanel extends JPanel {
    private ApplicationController controller;
    private ArrayList<Stopover> stopovers;
    private JLabel title;

    public StopoversJPanel(int flightID) throws ConnectionException {
        setLayout(new BorderLayout());

        controller = new ApplicationController();
        try {
            stopovers = controller.getStopoversOfFlight(flightID);
            StopoversModel model = new StopoversModel(stopovers);
            JTable flightOptionsTable = new JTable(model);

            flightOptionsTable.setPreferredScrollableViewportSize(new Dimension(900, 100));

            title = new JLabel("<html><h2 style='margin: 30px 0 15px 0'>Escales de ce vol</h2></html>", SwingConstants.CENTER);

            add(title, BorderLayout.NORTH);
            add(new JScrollPane(flightOptionsTable), BorderLayout.CENTER);

        } catch (FlightStopoversException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }
}
