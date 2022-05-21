package View;

import Controller.ApplicationController;
import Model.PassengerBooking;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Exception.*;

public class BookingsHistoryJPanel extends JPanel {
    private ApplicationController controller;
    private ArrayList<PassengerBooking> bookingsHistory;

    public BookingsHistoryJPanel(int idPassenger) {
        try {

            controller = new ApplicationController();
            bookingsHistory = controller.getBookingsHistory(idPassenger);
            if (bookingsHistory.isEmpty()){
                JOptionPane.showMessageDialog(null, "Ce passagé n'as pas d'historique de vol");
            } else {
                BookingsHistoryModel model = new BookingsHistoryModel(bookingsHistory);
                JTable bookingsHistoryTable = new JTable(model);

                bookingsHistoryTable.setPreferredScrollableViewportSize(new Dimension(900, 100));
                this.setLayout(new FlowLayout());
                this.add(new JScrollPane(bookingsHistoryTable));
            }

        }
        catch (BookingsHistoryException | ConnectionException | PriceException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }
}
