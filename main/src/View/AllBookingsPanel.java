package View;

import Controller.ApplicationController;

import javax.swing.*;
import Exception.*;

import java.awt.*;

public class AllBookingsPanel extends JPanel {
    private ApplicationController controller;
    public AllBookingsPanel() {
        try {
            controller = new ApplicationController();
            AllBookingsModel model = new AllBookingsModel(controller.getAllBookings());
            JTable bookings = new JTable(model);
            bookings.setPreferredScrollableViewportSize(new Dimension(900, 100));

            this.add(new JScrollPane(bookings));
            this.setLayout(new FlowLayout());
            this.setVisible(true);
        }
        catch (AllBookingsException e) {
            System.out.println("erreur");
        }
        catch (ConnectionException e) {
            System.out.println("erreur 2");
        }
    }
}
