package View;

import Model.Airport;
import Model.Booking;
import Model.Airplane;
import Model.PassengerBooking;

import javax.swing.table.AbstractTableModel;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BookingsHistoryModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<PassengerBooking> contents;

    public BookingsHistoryModel (ArrayList<PassengerBooking> contents) {
        columnNames = new ArrayList<>();
        columnNames.add("ID");
        columnNames.add("Date réservation");
        columnNames.add("Numéro vol");
        columnNames.add("Aéroport départ");
        columnNames.add("Aéroport arrivée");
        columnNames.add("Date départ");
        columnNames.add("Type siège");
        columnNames.add("Prix");

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
                return DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(passengerBooking.getDateBooking().getTime());
            case 2:
                return passengerBooking.getFlightID();
            case 3:
                return passengerBooking.getDepartureAirportName();
            case 4:
                return passengerBooking.getArrivalAirportName();
            case 5:
                return DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(passengerBooking.getDepartureDate().getTime());
            case 6:
                return passengerBooking.getSeatType();
            case 7:
                return passengerBooking.getRealPrice();
            default:
                return null;
        }
    }

    public Class getColumClass(int column) {
        Class c;
        switch(column) {
            case 0:
            case 2 :
                c = int.class;
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
