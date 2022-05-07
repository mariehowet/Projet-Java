package View;

import Controller.ApplicationController;
import Model.Booking;
import javax.swing.*;
import Exception.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AllBookingsPanel extends JPanel {
    private ApplicationController controller;
    private JButton delete, modification;
    private ListSelectionModel listSelect;
    private ArrayList<Booking> bookings;
    private Container frameContainer;

    public AllBookingsPanel(Container frameContainer) {
        try {
            this.frameContainer = frameContainer;
            controller = new ApplicationController();
            bookings = controller.getAllBookings();
            AllBookingsModel model = new AllBookingsModel(bookings);
            JTable bookingsTable = new JTable(model);
            bookingsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listSelect = bookingsTable.getSelectionModel();
            bookingsTable.setPreferredScrollableViewportSize(new Dimension(900, 100));

            this.add(new JScrollPane(bookingsTable));
            Panel buttonsPanel = new Panel();
            modification = new JButton("Modification");
            modification.addActionListener(new ModifyListener());
            modification.addActionListener(new RefreshListener());
            buttonsPanel.add(modification);
            delete = new JButton("Suppression");
            delete.addActionListener(new DeleteListener());
            delete.addActionListener(new RefreshListener());
            buttonsPanel.add(delete);
            this.add(buttonsPanel, BorderLayout.SOUTH);
            this.setLayout(new FlowLayout());
            this.setVisible(true);
        }
        catch (AllBookingsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch (ConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private class ModifyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // MAJ dans la bd : test
                // A faire via le formulaire
                int indSelectedLine = listSelect.getMinSelectionIndex();
                Booking booking = bookings.get(indSelectedLine);
                controller.updateBooking(booking.getId(), booking.getDate(), true, 35, null, booking.getMealType(), 600.0, 2);
            } catch(UpdateException exception) {
                JOptionPane.showMessageDialog(null,exception.getMessage());
            }
        }
    }

    private class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int indSelectedLine = listSelect.getMinSelectionIndex();
                Booking booking = bookings.get(indSelectedLine);
                controller.deleteBooking(booking);
                setVisible(true);
            } catch (DeleteException exception) {
                JOptionPane.showMessageDialog(null,exception.getMessage());
            }
        }
    }

    private class RefreshListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frameContainer.removeAll();
            frameContainer.add(new AllBookingsPanel(frameContainer), BorderLayout.CENTER);
            setVisible(true);
        }
    }

}
