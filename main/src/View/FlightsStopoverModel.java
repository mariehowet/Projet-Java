package View;

import Model.FlightWithOrWhithoutStopover;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;

public class FlightsStopoverModel extends AbstractTableModel {

    private ArrayList<String> columnNames;
    private ArrayList<FlightWithOrWhithoutStopover> contents;

    public FlightsStopoverModel(ArrayList<FlightWithOrWhithoutStopover> contents) {
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
        FlightWithOrWhithoutStopover flightStopover = contents.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return flightStopover.getFlightId();
            case 1:
                return flightStopover.getDepartureAirportName();
            case 2:
                return flightStopover.getArrivalAirportName();
            case 3:
                return flightStopover.getDepartureDate().getTime();
            case 4:
                return flightStopover.getArrivalDate().getTime();
            case 5:
                return flightStopover.getDepartureHour();
            case 6:
                return flightStopover.getArrivalHour();
            default:
                return null;
        }
    }

    public Class getColumnClass(int column) {
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
