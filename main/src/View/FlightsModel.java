package View;

import Model.FlightResearch;
import javax.swing.table.AbstractTableModel;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FlightsModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<FlightResearch> contents;

    public FlightsModel(ArrayList<FlightResearch> contents) {
        columnNames = new ArrayList<>();
        columnNames.add("Numéro vol");
        columnNames.add("Aéroport départ");
        columnNames.add("Aéroport arrivée");
        columnNames.add("Date départ");
        columnNames.add("Date arrivée");
        columnNames.add("Heure départ");
        columnNames.add("Heure d'arrivée");
        columnNames.add("Prix du vol (€)");
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
        FlightResearch flightResearch = contents.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return flightResearch.getFlightId();
            case 1:
                return flightResearch.getDepartureAirportName();
            case 2:
                return flightResearch.getArrivalAirportName();
            case 3:
                return flightResearch.getDepartureDate().getTime();
            case 4:
                return flightResearch.getArrivalDate().getTime();
            case 5:
                return flightResearch.getDepartureHour();
            case 6:
                return flightResearch.getArrivalHour();
            case 7:
                return flightResearch.getPrice();
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
            case 7:
                c = Double.class;
                break;
            default:
                c = String.class;
        }
        return c;
    }
}
