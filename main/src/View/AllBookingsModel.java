package View;

import Model.Booking;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;

public class AllBookingsModel extends AbstractTableModel {
    private ArrayList <String> columnNames;
    private ArrayList<Booking> contents;

    public AllBookingsModel (ArrayList<Booking> contents) {
        columnNames = new ArrayList<>();
        columnNames.add("Identifiant");
        columnNames.add("Date");
        columnNames.add("Payée ?");
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

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Booking booking = contents.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return booking.getId();
            case 1:
                return booking.getDate().getTime();
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
                return booking.getPassengerID();
            case 9:
                return booking.getSeatID();
            default:
                return null;
        }
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


