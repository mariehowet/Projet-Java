package View;

import Model.FlightStopover;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;

public class FlightsStopoverModel extends AbstractTableModel {

    private ArrayList<String> columnNames;
    private ArrayList<FlightStopover> contents;

    public FlightsStopoverModel(ArrayList<FlightStopover> contents) {
        columnNames = new ArrayList<>();
        columnNames.add("Numéro vol");
        columnNames.add("Aéroport départ");
        columnNames.add("Aéroport arrivée");
        columnNames.add("Date départ");
        columnNames.add("Date arrivée");
        columnNames.add("Heure départ");
        columnNames.add("Heure d'arrivée");
        columnNames.add("Aéroport d'escale");
        columnNames.add("Durée de l'escale");
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
        FlightStopover  flightStopover = contents.get(rowIndex);

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
            case 7:
                return flightStopover.getStopoverAirportName() ==  null ? "/" : flightStopover.getStopoverAirportName();
            case 8:
                return flightStopover.getDurationStopover() == null ? "/" :  flightStopover.getDurationStopover() ;
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
            case 8:
                c = Integer.class;
                break;
            default:
                c = String.class;
        }
        return c;
    }

}
