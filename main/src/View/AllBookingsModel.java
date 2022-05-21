package View;

import Controller.ApplicationController;
import Model.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import Exception.*;

public class AllBookingsModel extends AbstractTableModel {
    private ArrayList <String> columnNames;
    private ArrayList<Booking> contents;
    private ApplicationController controller;

    public AllBookingsModel (ArrayList<Booking> contents, ApplicationController controller) {
        this.controller = controller;
        columnNames = new ArrayList<>();
        columnNames.add("Identifiant");
        columnNames.add("Date");
        columnNames.add("Payé ?");
        columnNames.add("Poids baggages");
        columnNames.add("Entreprise");
        columnNames.add("Repas");
        columnNames.add("Prix");
        columnNames.add("Vol");
        columnNames.add("Passager");
        columnNames.add("Place");
        this.contents = contents;
    }

    @Override
    public int getRowCount() {
        return contents.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    public String getColumnName (int column) { return columnNames.get(column);}

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Booking booking = contents.get(rowIndex);

        try {
            Passenger passenger = controller.getActualPassenger(booking.getPassengerID());
            Seat seat = controller.getActualSeat(booking.getSeatID());
            switch (columnIndex) {
                case 0:
                    return booking.getId();
                case 1:
                    return DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(booking.getDate().getTime());
                case 2:
                    return booking.getHasPaid();
                case 3:
                    return booking.getLuggageWeight();
                case 4:
                    return booking.getCompanyName();
                case 5:
                    return booking.getMealType();
                case 6:
                    return booking.getRealPrice();
                case 7:
                    return booking.getFlightID();
                case 8:
                    return passenger.getFirstName() + " " + passenger.getLastName() + " "
                            + (passenger.getInitialMiddleName() != null ? passenger.getInitialMiddleName() : "");
                case 9:
                    return seat.getNumber() + seat.getColumnLetter();
                default:
                    return null;
            }
        } catch (ActualSeatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (SeatNumberException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (ActualPassengerException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }
    public Class getColumClass(int column) {
        Class c;
        switch(column) {
            case 0:
            case 7:
            case 8 :
            case 9 :
                c = int.class;
            break;
            case 1 : c = Date.class;
            break;
            case 2 : c = Boolean.class;
            break;
            case 3 : c = Integer.class;
            break;
            case 6 : c = Double.class;
            break;
            default: c = String.class;
        }
        return c;
    }
}


