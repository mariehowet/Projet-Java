package View;

import Model.Flight;


import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;

public class FlightsModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Flight> contents;

    public FlightsModel(ArrayList<Flight> contents) {
        columnNames = new ArrayList<>();
        columnNames.add("Numéro vol");
        columnNames.add("Aéroport départ");
        columnNames.add("Aéroport arrivée");
        columnNames.add("Date départ");
        columnNames.add("Date arrivée");
        columnNames.add("Heure départ");
        columnNames.add("Heure d'arrivée");
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
        Flight flight = contents.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return flight.getFlightId();
            case 1:
                return flight.getDepartureAirportName();
            case 2:
                return flight.getArrivalAirportName();
            case 3:
                return flight.getDepartureDate().getTime();
            case 4:
                return flight.getArrivalDate().getTime();
            case 5:
                return flight.getDepartureHour();
            case 6:
                return flight.getArrivalHour();
            default:
                return null;
        }
    }

    public Class getColumClass(int column) {
        Class c;
        switch(column) {
            case 0:
                c = int.class;
                break;
            case 3:
            case 4:
                c = Date.class;
                break;
            default:
                c = String.class;
        }
        return c;
    }
}
