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
    private JButton delete, modification,addition;
    private ListSelectionModel listSelect;
    private ArrayList<Booking> bookings;
    private Container frameContainer;

    public AllBookingsJPanel(Container frameContainer) {
        try {
            this.frameContainer = frameContainer;
            controller = new ApplicationController();
            bookings = controller.getAllBookings();
            this.setLayout(new BorderLayout());
            AllBookingsModel model = new AllBookingsModel(bookings);
            JTable bookingsTable = new JTable(model);
            bookingsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listSelect = bookingsTable.getSelectionModel();
            bookingsTable.setPreferredScrollableViewportSize(new Dimension(900, 100));

            this.add(new JScrollPane(bookingsTable), BorderLayout.NORTH);
            Panel buttonsPanel = new Panel();
            addition = new JButton("Ajout");
            addition.addActionListener(new AddListener());
            buttonsPanel.add(addition);
            modification = new JButton("Modification");
            modification.addActionListener(new ModifyListener());
            buttonsPanel.add(modification);
            delete = new JButton("Suppression");
            delete.addActionListener(new DeleteListener());

            buttonsPanel.add(delete);
            this.add(buttonsPanel, BorderLayout.CENTER);

            this.setVisible(true);
        }
        catch (AllBookingsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch (ConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int reponse = JOptionPane.showConfirmDialog(null,"Etes-vous sûr de vouloir ajouter cette réservation ?", "Modification", JOptionPane.YES_NO_OPTION);
            if(reponse == 0) {
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

                int reponse = JOptionPane.showConfirmDialog(null,"Etes-vous sûr de vouloir modifier cette réservation ?", "Modification", JOptionPane.YES_NO_OPTION);

                if(reponse == 0) {
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
                int reponse = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer cette réservation ?", "Suppression", JOptionPane.YES_NO_OPTION);
                if (reponse == 0) {
                    Booking booking = bookings.get(indSelectedLine);
                    try {
                        controller.deleteBooking(booking);
                        frameContainer.removeAll();
                        frameContainer.revalidate();
                        frameContainer.repaint();
                        frameContainer.add(new AllBookingsJPanel(frameContainer));
                    } catch (DeleteException exception) {
                        JOptionPane.showMessageDialog(null, exception.getMessage());
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "Sélectionnez une ligne à supprimer");
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
