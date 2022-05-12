package View;

import Controller.ApplicationController;
import Model.Booking;
import javax.swing.*;
import Exception.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AllBookingsJPanel extends JPanel {
    private ApplicationController controller;
    private JButton delete, modification;
    private ListSelectionModel listSelect;
    private ArrayList<Booking> bookings;
    private Container frameContainer;

    public AllBookingsJPanel(Container frameContainer) {
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
            buttonsPanel.add(modification);
            delete = new JButton("Suppression");
            delete.addActionListener(new DeleteListener());
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

            int indiceLigneSelect = listSelect.getMinSelectionIndex();

            if(indiceLigneSelect != -1) {

                int reponse = JOptionPane.showConfirmDialog(null,"Etes-vous sûr de vouloir modifier ce type d'article ?", "Modification", JOptionPane.YES_NO_OPTION);

                if(reponse == 0) {
                    Booking booking = bookings.get(indiceLigneSelect);

                    try {
                        frameContainer.removeAll();
                        frameContainer.revalidate();
                        frameContainer.repaint();
                        frameContainer.add(new UpdateBookingJPanel(frameContainer, booking), BorderLayout.CENTER);

                    } catch (ConnectionException exception) {
                        JOptionPane.showMessageDialog(null, exception.getMessage());
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "Sélectionnez une ligne à modifier");
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
            frameContainer.add(new AllBookingsJPanel(frameContainer), BorderLayout.CENTER);
            setVisible(true);
        }
    }

}
