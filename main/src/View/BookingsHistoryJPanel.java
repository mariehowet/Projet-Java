package View;

import Controller.ApplicationController;
import Model.PassengerBooking;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Exception.*;

public class BookingsHistoryJPanel extends JPanel {
    private ApplicationController controller;
    //private ListSelectionModel listSelect;
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
                bookingsHistoryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                //listSelect = bookingsHistoryTable.getSelectionModel();
                bookingsHistoryTable.setPreferredScrollableViewportSize(new Dimension(900, 100));
                this.setLayout(new FlowLayout());
                this.add(new JScrollPane(bookingsHistoryTable));
            }

        }
        catch (BookingsHistoryException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch (ConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (PriceException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
