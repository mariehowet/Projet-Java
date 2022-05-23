package View;

import Controller.ApplicationController;
import Model.Booking;
import javax.swing.*;
import Exception.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuItemAllBookings extends JPanel {
    private ApplicationController controller;
    private JButton delete, modification,addition;
    private ListSelectionModel listSelect;
    private ArrayList<Booking> bookings;
    private Container frameContainer;
    private AllBookingsModel model;
    private JTable bookingsTable;
    private JPanel buttonsPanel, displayPanel, bookingsPanel;
    private JLabel title;

    public MenuItemAllBookings(Container frameContainer) {
        setLayout(new BorderLayout());
        title = new JLabel("<html><h1 style='margin: 30px 0 15px 0'>Liste des réservations</h1></html>", SwingConstants.CENTER);
        try {


            this.frameContainer = frameContainer;
            controller = new ApplicationController();
            bookings = controller.getAllBookings();
            model = new AllBookingsModel(bookings, controller);
            bookingsTable = new JTable(model);
            listSelect = bookingsTable.getSelectionModel();

            bookingsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            bookingsTable.setPreferredScrollableViewportSize(new Dimension(900, 100));

            //-------------ButtonPanel----------------------------------
            buttonsPanel = new JPanel();

            addition = new JButton("Ajout");
            addition.addActionListener(new AddListener());
            buttonsPanel.add(addition);

            modification = new JButton("Modification");
            modification.addActionListener(new ModifyListener());
            buttonsPanel.add(modification);

            delete = new JButton("Suppression");
            delete.addActionListener(new DeleteListener());
            buttonsPanel.add(delete);

            bookingsPanel = new JPanel(new BorderLayout());

            displayPanel = new JPanel(new FlowLayout());
            displayPanel.add(new JScrollPane(bookingsTable));

            bookingsPanel.add(displayPanel, BorderLayout.NORTH);
            bookingsPanel.add(buttonsPanel, BorderLayout.CENTER);

            add(title, BorderLayout.NORTH);
            add(bookingsPanel, BorderLayout.CENTER);
        }
        catch (AllBookingsException | ConnectionException | PriceException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int response = JOptionPane.showConfirmDialog(null,"Etes-vous sûr de vouloir ajouter une réservation ?", "Modification", JOptionPane.YES_NO_OPTION);
            if(response == 0) {
                try {
                    frameContainer.removeAll();
                    frameContainer.revalidate();
                    frameContainer.repaint();
                    frameContainer.add(new AddBookingJPanel(frameContainer), BorderLayout.CENTER);

                } catch (ConnectionException exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        }
    }




    private class ModifyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int indSelectedLine = listSelect.getMinSelectionIndex();

            if(indSelectedLine != -1) {
                int response = JOptionPane.showConfirmDialog(null,"Etes-vous sûr de vouloir modifier cette réservation ?", "Modification", JOptionPane.YES_NO_OPTION);

            if(response == 0) {
                Booking booking = bookings.get(indSelectedLine);
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
            int indSelectedLine = listSelect.getMinSelectionIndex();

            if (indSelectedLine != -1) {
                int response = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer cette réservation ?", "Suppression", JOptionPane.YES_NO_OPTION);

            if (response == 0) {
                Booking booking = bookings.get(indSelectedLine);
                try {
                    controller.deleteBooking(booking);
                    frameContainer.removeAll();
                    frameContainer.revalidate();
                    frameContainer.repaint();
                    frameContainer.add(new MenuItemAllBookings(frameContainer));
                } catch (DeleteException exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
            } else {
                JOptionPane.showMessageDialog(null, "Sélectionnez une ligne à supprimer");
            }
        }
    }
}
