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
    private JLabel title;

    public BookingsHistoryJPanel(int idPassenger) {
        setLayout(new BorderLayout());
        try {

            controller = new ApplicationController();
            bookingsHistory = controller.getBookingsHistory(idPassenger);
            if (bookingsHistory.isEmpty()){
                JOptionPane.showMessageDialog(null, "Ce passagé n'as pas d'historique de réservation");
            } else {
                BookingsHistoryModel model = new BookingsHistoryModel(bookingsHistory);
                JTable bookingsHistoryTable = new JTable(model);
                bookingsHistoryTable.setPreferredScrollableViewportSize(new Dimension(900, 100));

                title = new JLabel("<html><h2 style='margin: 30px 0 15px 0'>Historique des réservations</h2></html>", SwingConstants.CENTER);

                add(title, BorderLayout.NORTH);
                add(new JScrollPane(bookingsHistoryTable), BorderLayout.CENTER);
            }

        }
        catch (BookingsHistoryException | ConnectionException | PriceException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }
}
