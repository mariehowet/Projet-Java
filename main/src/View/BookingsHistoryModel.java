package View;

import Model.PassengerBooking;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;

public class BookingsHistoryModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<PassengerBooking> contents;

    public BookingsHistoryModel (ArrayList<PassengerBooking> contents) {
        columnNames = new ArrayList<>();
        columnNames.add("Numéro de réservation");
        columnNames.add("Date de la réservation");
        columnNames.add("Numéro de vol");
        columnNames.add("Aéroport de départ");
        columnNames.add("Aéroport d'arrivée");
        columnNames.add("Date de départ");
        columnNames.add("Type de siège");
        columnNames.add("Prix (€)");

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
        PassengerBooking passengerBooking = contents.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return passengerBooking.getIdBooking();
            case 1:
                return passengerBooking.getDateBooking().getTime();
            case 2:
                return passengerBooking.getFlightID();
            case 3:
                return passengerBooking.getDepartureAirportName();
            case 4:
                return passengerBooking.getArrivalAirportName();
            case 5:
                return passengerBooking.getDepartureDate().getTime();
            case 6:
                return passengerBooking.getSeatType();
            case 7:
                return passengerBooking.getRealPrice();
            default:
                return null;
        }
    }

    public Class getColumnClass(int column) {
        Class c;
        switch(column) {
            case 0:
            case 2 :
                c = Integer.class;
                break;
            case 1 :
            case 5:
                c = Date.class;
                break;
            case 7: c = Double.class;
                break;
            default: c = String.class;
        }
        return c;
    }
}
